package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import de.mcreator.emeraldsplus.procedures.DeletedummyProcedure;

public class DummyItem extends Item {
	public DummyItem(Item.Properties properties) {
		super(properties);
	}
	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		DeletedummyProcedure.execute(entity);
		return ar;
	}
}