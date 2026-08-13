package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Optional;

public class LuckJobSiteBreakProcedure {
	public static void execute(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		if (world == null || pos == null || entity == null || itemstack == null)
			return;
		if (!(entity instanceof Player player))
			return;
		if (!(world instanceof ServerLevel serverLevel))
			return;

		Holder<Enchantment> luck = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:luck")));

		int toolLevel = EnchantmentHelper.getItemEnchantmentLevel(luck, itemstack);
		if (toolLevel <= 0)
			return;

		boolean hasArmorPiece = false;
		for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
			if (EnchantmentHelper.getItemEnchantmentLevel(luck, player.getItemBySlot(slot)) > 0) {
				hasArmorPiece = true;
				break;
			}
		}
		if (!hasArmorPiece)
			return;

		boolean connectedToVillager = false;
		for (Villager villager : serverLevel.getEntitiesOfClass(Villager.class, new AABB(pos).inflate(48))) {
			Optional<GlobalPos> jobSite = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE);
			if (jobSite.isPresent() && jobSite.get().pos().equals(pos)
					&& jobSite.get().dimension().equals(serverLevel.dimension())) {
				connectedToVillager = true;
				break;
			}
		}
		if (!connectedToVillager)
			return;

		GlobalPos globalPos = GlobalPos.of(serverLevel.dimension(), pos);
		LuckJobSiteTracker.TRACKED.put(globalPos, new LuckJobSiteTracker.Entry(player.getUUID(), toolLevel));

		LuckJobSiteOpenProcedure.ensureRegistered();
	}
}