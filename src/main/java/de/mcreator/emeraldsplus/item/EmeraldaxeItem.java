package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class EmeraldaxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1725, 8.5f, 0, 2, TagKey.create(Registries.ITEM, Identifier.parse("emeraldsplus:emeraldaxe_repair_items")));

	public EmeraldaxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 8.5f, -3f, properties);
	}
}