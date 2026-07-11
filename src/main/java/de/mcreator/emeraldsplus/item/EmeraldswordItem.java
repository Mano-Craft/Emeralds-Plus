package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class EmeraldswordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1725, 4f, 0, 2, TagKey.create(Registries.ITEM, Identifier.parse("emeraldsplus:emeraldsword_repair_items")));

	public EmeraldswordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 6.5f, -2.4f));
	}
}