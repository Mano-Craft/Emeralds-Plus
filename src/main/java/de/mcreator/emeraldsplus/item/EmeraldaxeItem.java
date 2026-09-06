package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

import de.mcreator.emeraldsplus.procedures.MultiBreakProcedure;
import de.mcreator.emeraldsplus.procedures.LuckJobSiteBreakProcedure;
import de.mcreator.emeraldsplus.procedures.SmeltedProcedure;
import de.mcreator.emeraldsplus.procedures.InstaTreeProcedure;

public class EmeraldaxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1725, 8.5f, 0, 15, TagKey.create(Registries.ITEM, Identifier.parse("emeraldsplus:emeraldaxe_repair_items")));

	public EmeraldaxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 8.5f, -3f, properties);
	}
	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		MultiBreakProcedure.execute(world, pos, entity, itemstack);
		LuckJobSiteBreakProcedure.execute(world, pos, blockstate, entity, itemstack);
		SmeltedProcedure.execute(world, pos, blockstate, entity, itemstack);
		InstaTreeProcedure.execute(world, pos, blockstate, entity, itemstack);
		return retval;
	}
}