package de.mcreator.emeraldsplus.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import de.mcreator.emeraldsplus.procedures.MultiBreakProcedure;


public class EmeraldshovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1725, 8.5f, 0, 12, TagKey.create(Registries.ITEM, Identifier.parse("emeraldsplus:emeraldshovel_repair_items")));

	public EmeraldshovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 5f, -3f, properties);
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		MultiBreakProcedure.execute(world, pos, entity, itemstack);
		return retval;
	}
}