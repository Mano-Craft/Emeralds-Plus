package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class EmeraldsItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1725, 8.5f, 0, 2, TagKey.create(Registries.ITEM, Identifier.parse("emeraldsplus:emerald_pickaxe_repair_items")));

	public EmeraldsItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 4.5f, -3f));
	}
}