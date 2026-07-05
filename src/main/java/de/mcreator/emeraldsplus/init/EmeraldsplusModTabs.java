/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package de.mcreator.emeraldsplus.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import de.mcreator.emeraldsplus.EmeraldsplusMod;

public class EmeraldsplusModTabs {
	public static ResourceKey<CreativeModeTab> TAB_EMERALDS_PLUS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "emeralds_plus"));

	public static void load() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_EMERALDS_PLUS,
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.emeraldsplus.emeralds_plus")).icon(() -> new ItemStack(EmeraldsplusModItems.EMERALD_UPGRADE)).displayItems((parameters, tabData) -> {
					tabData.accept(EmeraldsplusModItems.EMERALDSWORD);
					tabData.accept(EmeraldsplusModItems.EMERALDAXE);
					tabData.accept(EmeraldsplusModItems.EMERALD_PICKAXE);
					tabData.accept(EmeraldsplusModItems.EMERALDSHOVEL);
					tabData.accept(EmeraldsplusModItems.EMERALDHOE);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_HELMET);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_CHESTPLATE);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_LEGGINGS);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_BOOTS);
					tabData.accept(EmeraldsplusModItems.EMERALD_UPGRADE);
				}).build());
	}
}