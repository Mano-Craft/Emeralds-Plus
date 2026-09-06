package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InstaTreeProcedure {

	private static final int MAX_TREE_BLOCKS = 128;
	private static final int PROXIMITY = 3; // Chebyshev-Abstand, wie weit ein Ast vom letzten Chop abweichen darf

	private record ChopTracker(UUID playerId, Block logType, BlockPos lastPos, int count) {}

	private static final java.util.Map<UUID, ChopTracker> TRACKERS = new ConcurrentHashMap<>();

	public static void execute(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		if (world == null || pos == null || blockstate == null || entity == null || itemstack == null)
			return;
		if (!(world instanceof ServerLevel serverLevel))
			return;
		if (!blockstate.is(BlockTags.LOGS))
			return;

		Holder<Enchantment> instaTree = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:insta_tree")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(instaTree, itemstack);
		if (level <= 0)
			return;

		int requiredChops = switch (level) {
			case 1 -> 3;
			case 2 -> 2;
			default -> 1;
		};

		Block logType = blockstate.getBlock();
		Block leafType = deriveLeafType(logType);
		UUID playerId = entity.getUUID();
		BlockPos capturedPos = pos.immutable();

		ChopTracker previous = TRACKERS.get(playerId);
		int newCount;
		if (previous != null && previous.logType() == logType && isNearby(previous.lastPos(), capturedPos)) {
			newCount = previous.count() + 1;
		} else {
			newCount = 1;
		}

		if (newCount < requiredChops) {
			TRACKERS.put(playerId, new ChopTracker(playerId, logType, capturedPos, newCount));
			return;
		}

		TRACKERS.remove(playerId);

		if (leafType == null)
			return; // dieser Log-Typ hat gar keine bekannten Blätter -> nie als "Baum" behandeln

		// Phase 1: nur Stämme fluten
		Set<BlockPos> logsOnly = new HashSet<>();
		Deque<BlockPos> logQueue = new ArrayDeque<>();
		logsOnly.add(capturedPos);
		logQueue.add(capturedPos);

		while (!logQueue.isEmpty() && logsOnly.size() <= MAX_TREE_BLOCKS) {
			BlockPos current = logQueue.poll();

			for (int dx = -1; dx <= 1; dx++)
				for (int dy = -1; dy <= 1; dy++)
					for (int dz = -1; dz <= 1; dz++) {
						if (dx == 0 && dy == 0 && dz == 0)
							continue;

						BlockPos neighbor = current.offset(dx, dy, dz);
						if (logsOnly.contains(neighbor))
							continue;
						if (!world.getBlockState(neighbor).is(logType))
							continue;

						logsOnly.add(neighbor);
						logQueue.add(neighbor);
					}
		}

		// Phase 2: hat dieser Stamm-Verbund überhaupt Blätter direkt angrenzend? -> sonst kein Baum, z.B. ein Haus
		boolean hasAdjacentLeaves = false;
		outer:
		for (BlockPos logPos : logsOnly) {
			for (int dx = -1; dx <= 1; dx++)
				for (int dy = -1; dy <= 1; dy++)
					for (int dz = -1; dz <= 1; dz++) {
						if (dx == 0 && dy == 0 && dz == 0)
							continue;
						BlockPos neighbor = logPos.offset(dx, dy, dz);
						if (world.getBlockState(neighbor).is(leafType)) {
							hasAdjacentLeaves = true;
							break outer;
						}
					}
		}

		if (!hasAdjacentLeaves)
			return; // kein Blatt in der Nähe -> vermutlich Bauwerk, nicht anfassen

		// Phase 3: jetzt regulär Stamm + Blätter zusammen fluten und abbauen
		Set<BlockPos> visited = new HashSet<>();
		Deque<BlockPos> queue = new ArrayDeque<>();
		visited.add(capturedPos);
		queue.add(capturedPos);
		List<BlockPos> targets = new ArrayList<>();

		while (!queue.isEmpty() && visited.size() <= MAX_TREE_BLOCKS) {
			BlockPos current = queue.poll();

			for (int dx = -1; dx <= 1; dx++)
				for (int dy = -1; dy <= 1; dy++)
					for (int dz = -1; dz <= 1; dz++) {
						if (dx == 0 && dy == 0 && dz == 0)
							continue;

						BlockPos neighbor = current.offset(dx, dy, dz);
						if (visited.contains(neighbor))
							continue;
						if (!matches(world, neighbor, logType, leafType))
							continue;

						visited.add(neighbor);
						queue.add(neighbor);
						targets.add(neighbor);
					}
		}

		int destroyedLogCount = 0;

		for (BlockPos target : targets) {
			BlockState targetState = world.getBlockState(target);
			if (targetState.isAir())
				continue;

			boolean isLog = targetState.is(logType);

			BlockEntity blockEntity = targetState.hasBlockEntity() ? serverLevel.getBlockEntity(target) : null;
			Block.dropResources(targetState, serverLevel, target, blockEntity, entity, itemstack);

			world.levelEvent(2001, target, Block.getId(targetState));
			world.removeBlock(target, false);

			if (isLog)
				destroyedLogCount++;
		}

		destroyedLogCount *= 4;

		if (destroyedLogCount > 0) {
			itemstack.hurtAndBreak(destroyedLogCount, entity, entity.getEquipmentSlotForItem(itemstack));
		}
	}

	private static boolean matches(Level world, BlockPos pos, Block logType, Block leafType) {
		BlockState state = world.getBlockState(pos);
		if (state.is(logType))
			return true;
		return state.is(leafType);
	}

	private static Block deriveLeafType(Block logBlock) {
		Identifier logId = BuiltInRegistries.BLOCK.getKey(logBlock);
		String path = logId.getPath();
		if (!path.endsWith("_log"))
			return null;

		String leafPath = path.substring(0, path.length() - "_log".length()) + "_leaves";
		Identifier leafId = Identifier.fromNamespaceAndPath(logId.getNamespace(), leafPath);

		Optional<Block> leafBlock = BuiltInRegistries.BLOCK.getOptional(leafId);
		return leafBlock.orElse(null);
	}

	private static boolean isNearby(BlockPos a, BlockPos b) {
		return Math.abs(a.getX() - b.getX()) <= PROXIMITY
				&& Math.abs(a.getY() - b.getY()) <= PROXIMITY
				&& Math.abs(a.getZ() - b.getZ()) <= PROXIMITY;
	}
}