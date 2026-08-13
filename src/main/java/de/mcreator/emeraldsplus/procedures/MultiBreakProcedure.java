package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class MultiBreakProcedure {

	// Feste Weltachsen, falls der Blick zu steil ist, um eine sinnvolle horizontale Ausrichtung abzuleiten
	private static final Direction FIXED_AXIS_A = Direction.EAST;
	private static final Direction FIXED_AXIS_B = Direction.SOUTH;

	// Innerhalb dieses Pitch-Bereichs (Grad) gilt der Blick als "ungefähr gerade" (horizontal)
	private static final float HORIZONTAL_LOOK_THRESHOLD = 45.0f;

	public static void execute(Level world, BlockPos pos, LivingEntity entity, ItemStack itemstack) {
		if (world == null || pos == null || entity == null || itemstack == null)
			return;

		Holder<Enchantment> multiBreak = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:multi_break")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(multiBreak, itemstack);
		if (level <= 0)
			return;

		boolean horizontalLook = Math.abs(entity.getXRot()) <= HORIZONTAL_LOOK_THRESHOLD;
		Direction facing = entity.getDirection();
		Direction axisA = horizontalLook ? facing.getClockWise() : FIXED_AXIS_A; // "rechts"
		Direction axisB = horizontalLook ? facing : FIXED_AXIS_B; // "hinten"

		List<BlockPos> targets = new ArrayList<>();

		switch (level) {
			case 1 -> targets.add(offset(pos, axisA, axisB, 0, 1, 0));
			case 2 -> {
				targets.add(offset(pos, axisA, axisB, 0, 1, 0));
				targets.add(offset(pos, axisA, axisB, 1, 0, 0));
				targets.add(offset(pos, axisA, axisB, 1, 1, 0));
			}
			case 3 -> {
				for (int a = -1; a <= 1; a++)
					for (int d = -1; d <= 1; d++) {
						if (a == 0 && d == 0)
							continue;
						targets.add(offset(pos, axisA, axisB, a, d, 0));
					}
			}
			case 4 -> {
				for (int a = 0; a <= 1; a++)
					for (int d = 0; d <= 1; d++)
						for (int b = 0; b <= 1; b++) {
							if (a == 0 && d == 0 && b == 0)
								continue;
							targets.add(offset(pos, axisA, axisB, a, d, b));
						}
			}
			case 5 -> {
				for (int a = -1; a <= 1; a++)
					for (int d = 0; d <= 2; d++)
						for (int b = 0; b <= 1; b++) {
							if (a == 0 && d == 0 && b == 0)
								continue;
							targets.add(offset(pos, axisA, axisB, a, d, b));
						}
			}
			default -> {
				return;
			}
		}

		int destroyedCount = 0;

		for (BlockPos target : targets) {
			BlockState targetState = world.getBlockState(target);
			if (targetState.isAir())
				continue;
			if (targetState.getDestroySpeed(world, target) < 0)
				continue;

			if (world instanceof ServerLevel serverLevel) {
				BlockEntity blockEntity = targetState.hasBlockEntity() ? serverLevel.getBlockEntity(target) : null;
				Block.dropResources(targetState, serverLevel, target, blockEntity, entity, itemstack);
			}

			world.levelEvent(2001, target, Block.getId(targetState));
			world.removeBlock(target, false);
			destroyedCount++;
		}

		if (destroyedCount > 0 && !world.isClientSide()) {
			itemstack.hurtAndBreak(destroyedCount, entity, entity.getEquipmentSlotForItem(itemstack));
		}
	}

	private static BlockPos offset(BlockPos origin, Direction axisA, Direction axisB, int aOffset, int downOffset, int bOffset) {
		return origin.relative(axisA, aOffset).below(downOffset).relative(axisB, bOffset);
	}
}