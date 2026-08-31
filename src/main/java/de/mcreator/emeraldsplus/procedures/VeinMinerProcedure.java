package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VeinMinerProcedure {

	private static final int MAX_BLOCKS = 64;

	private static final List<TagKey<Block>> ORE_FAMILIES = List.of(
			BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.IRON_ORES,
			BlockTags.GOLD_ORES, BlockTags.DIAMOND_ORES, BlockTags.EMERALD_ORES,
			BlockTags.LAPIS_ORES, BlockTags.REDSTONE_ORES
	);

	public static void execute(Level world, BlockPos originPos, BlockState originState, LivingEntity entity, ItemStack itemstack) {
		if (world == null || originPos == null || originState == null || entity == null || itemstack == null)
			return;
		if (!(world instanceof ServerLevel serverLevel))
			return;

		Holder<Enchantment> veinMiner = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:vein_miner")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(veinMiner, itemstack);
		if (level <= 0)
			return;

		TagKey<Block> family = getOreFamily(originState);
		if (family == null && !isSingletonOre(originState))
			return; // kein Erz -> Vein Miner tut nichts

		Set<BlockPos> visited = new HashSet<>();
		visited.add(originPos);
		Deque<BlockPos> queue = new ArrayDeque<>();
		queue.add(originPos);

		List<BlockPos> extraTargets = new ArrayList<>();

		while (!queue.isEmpty() && visited.size() <= MAX_BLOCKS) {
			BlockPos current = queue.poll();

			for (int dx = -1; dx <= 1; dx++)
				for (int dy = -1; dy <= 1; dy++)
					for (int dz = -1; dz <= 1; dz++) {
						if (dx == 0 && dy == 0 && dz == 0)
							continue;

						BlockPos neighbor = current.offset(dx, dy, dz);
						if (visited.contains(neighbor))
							continue;

						BlockState neighborState = world.getBlockState(neighbor);
						if (!matches(originState, family, neighborState))
							continue;

						visited.add(neighbor);
						queue.add(neighbor);
						extraTargets.add(neighbor);
					}
		}

		int destroyedCount = 0;

		for (BlockPos target : extraTargets) {
			BlockState targetState = world.getBlockState(target);
			if (targetState.isAir())
				continue;

			BlockEntity blockEntity = targetState.hasBlockEntity() ? serverLevel.getBlockEntity(target) : null;
			Block.dropResources(targetState, serverLevel, target, blockEntity, entity, itemstack);

			world.levelEvent(2001, target, Block.getId(targetState));
			world.removeBlock(target, false);
			destroyedCount++;
		}

		if (destroyedCount > 0) {
			itemstack.hurtAndBreak(destroyedCount, entity, entity.getEquipmentSlotForItem(itemstack));
		}
	}

	private static boolean matches(BlockState originState, TagKey<Block> family, BlockState candidate) {
		if (candidate.isAir())
			return false;
		if (family != null)
			return candidate.is(family);
		return candidate.is(originState.getBlock());
	}

	private static TagKey<Block> getOreFamily(BlockState state) {
		for (TagKey<Block> tag : ORE_FAMILIES) {
			if (state.is(tag))
				return tag;
		}
		return null;
	}

	private static boolean isSingletonOre(BlockState state) {
		return state.is(Blocks.NETHER_QUARTZ_ORE) || state.is(Blocks.NETHER_GOLD_ORE) || state.is(Blocks.ANCIENT_DEBRIS);
	}
}