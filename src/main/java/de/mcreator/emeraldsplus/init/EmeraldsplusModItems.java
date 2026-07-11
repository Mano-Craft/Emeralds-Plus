/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package de.mcreator.emeraldsplus.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import java.util.function.Function;

import de.mcreator.emeraldsplus.item.*;
import de.mcreator.emeraldsplus.EmeraldsplusMod;

public class EmeraldsplusModItems {
	public static Item EMERALD_PICKAXE;
	public static Item EMERALD_UPGRADE;
	public static Item EMERALDSWORD;
	public static Item EMERALDAXE;
	public static Item EMERALDSHOVEL;
	public static Item EMERALDHOE;
	public static Item EMERALDARMOR_HELMET;
	public static Item EMERALDARMOR_CHESTPLATE;
	public static Item EMERALDARMOR_LEGGINGS;
	public static Item EMERALDARMOR_BOOTS;
	public static Item EMERALDSHIELD;

	public static void load() {
		EMERALD_PICKAXE = register("emerald_pickaxe", EmeraldsItem::new);
		EMERALD_UPGRADE = register("emerald_upgrade", EmeralsUpgradeItem::new);
		EMERALDSWORD = register("emeraldsword", EmeraldswordItem::new);
		EMERALDAXE = register("emeraldaxe", EmeraldaxeItem::new);
		EMERALDSHOVEL = register("emeraldshovel", EmeraldshovelItem::new);
		EMERALDHOE = register("emeraldhoe", EmeraldhoeItem::new);
		EMERALDARMOR_HELMET = register("emeraldarmor_helmet", EmeraldarmorItem.Helmet::new);
		EMERALDARMOR_CHESTPLATE = register("emeraldarmor_chestplate", EmeraldarmorItem.Chestplate::new);
		EMERALDARMOR_LEGGINGS = register("emeraldarmor_leggings", EmeraldarmorItem.Leggings::new);
		EMERALDARMOR_BOOTS = register("emeraldarmor_boots", EmeraldarmorItem.Boots::new);
		EMERALDSHIELD = register("emeraldshield", EmeraldshieldItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> I register(String name, Function<Item.Properties, ? extends I> supplier) {
		return (I) Items.registerItem(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, name)), (Function<Item.Properties, Item>) supplier);
	}
}