package de.mcreator.emeraldsplus.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class EmeralsUpgradeItem extends Item {
	public EmeralsUpgradeItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_0"));
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_1"));
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_2"));
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_3"));
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_4"));
		componentConsumer.accept(Component.translatable("item.emeraldsplus.emerald_upgrade.description_5"));
	}
}