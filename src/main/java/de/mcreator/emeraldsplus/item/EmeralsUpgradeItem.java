package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EmeralsUpgradeItem extends Item {
	public EmeralsUpgradeItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}