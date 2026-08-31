package de.mcreator.emeraldsplus.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import de.mcreator.emeraldsplus.init.EmeraldsplusModItems;

public class DeletedummyProcedure {
	public static boolean eventResult = true;

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			ItemStack _stktoremove = new ItemStack(EmeraldsplusModItems.DUMMYITEM);
			_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
		}
		if (entity instanceof ServerPlayer _player)
			_player.sendSystemMessage(Component.literal("§cYou got an item which is not made to use , because of that it got removed out of you inventory"), false);
	}
}