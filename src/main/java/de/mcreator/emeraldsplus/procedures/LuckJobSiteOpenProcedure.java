package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;

import java.util.Optional;

public class LuckJobSiteOpenProcedure {

	private static boolean registered = false;

	/** Registriert den Rechtsklick-Listener genau einmal, egal wie oft aufgerufen. */
	public static void ensureRegistered() {
		if (registered)
			return;
		registered = true;

		UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
			execute(level, player, entity);
			return InteractionResult.PASS;
		});
	}

	public static void execute(Level world, Entity playerEntity, Entity target) {
		if (!(playerEntity instanceof Player p))
			return;
		if (!(target instanceof Villager villager))
			return;
		if (!(world instanceof ServerLevel serverLevel))
			return;

		Optional<GlobalPos> jobSite = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE);
		if (jobSite.isEmpty())
			return;

		LuckJobSiteTracker.Entry tracked = LuckJobSiteTracker.TRACKED.remove(jobSite.get());
		if (tracked == null)
			return;
		if (!tracked.playerId().equals(p.getUUID()))
			return;

		Holder<Enchantment> luck = serverLevel.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:luck")));

		int armorPieces = 0;
		int totalDurationTicks = 0;

		for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
			int pieceLevel = EnchantmentHelper.getItemEnchantmentLevel(luck, p.getItemBySlot(slot));
			if (pieceLevel <= 0)
				continue;

			armorPieces++;

			int minTicks;
			int maxTicks;
			switch (pieceLevel) {
				case 1 -> { minTicks = 20; maxTicks = 300; }   // 1s - 15s
				case 2 -> { minTicks = 300; maxTicks = 600; }  // 15s - 30s
				default -> { minTicks = 600; maxTicks = 900; } // 30s - 45s (Level 3+)
			}

			int pieceDuration = minTicks + p.getRandom().nextInt(maxTicks - minTicks + 1);
			totalDurationTicks += pieceDuration;
		}

		if (armorPieces <= 0)
			return;

		float chance = armorPieces * 0.05f; // dein Testwert
		float roll = p.getRandom().nextFloat();
		if (roll >= chance)
			return;

		int durationTicks = totalDurationTicks;
		int amplifier = Math.max(0, tracked.toolLevel() - 1);

		p.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, durationTicks, amplifier));
	}
}