package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FastFarmProcedure {
	public static void execute(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		if (world == null || pos == null || blockstate == null || entity == null || itemstack == null)
			return;
		if (!(blockstate.getBlock() instanceof CropBlock cropBlock))
			return;
		if (!cropBlock.isMaxAge(blockstate))
			return;

		Holder<Enchantment> fastFarm = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:fast_farm")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(fastFarm, itemstack);
		if (level <= 0)
			return;

		world.setBlockAndUpdate(pos, cropBlock.getStateForAge(0));

		float chance = switch (level) {
			case 1 -> 0.20f;
			case 2 -> 0.25f;
			case 3 -> 0.35f;
			default -> 0.0f;
		};

		if (chance > 0 && entity.getRandom().nextFloat() < chance)
			Block.popResource(world, pos, new ItemStack(Items.BONE));
	}
}