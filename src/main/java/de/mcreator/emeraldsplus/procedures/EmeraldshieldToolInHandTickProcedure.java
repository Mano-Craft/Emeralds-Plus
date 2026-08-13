package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EmeraldshieldToolInHandTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity livingEntity))
			return;
		if (!livingEntity.isBlocking())
			return;

		ItemStack itemstack = livingEntity.getUseItem();
		if (itemstack.isEmpty())
			return;

		Holder<Enchantment> powerShield = entity.level().registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:power_shield")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(powerShield, itemstack);
		if (level <= 0)
			return;

		entity.fallDistance = 0;
	}
}