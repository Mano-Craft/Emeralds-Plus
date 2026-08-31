package de.mcreator.emeraldsplus;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.world.item.Items;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

import de.mcreator.emeraldsplus.init.EmeraldsplusModArmorModels;

@Environment(EnvType.CLIENT)
public class EmeraldsplusModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		EmeraldsplusModArmorModels.clientLoad();
		// Start of user code block mod init
		// End of user code block mod init
	}
	// Start of user code block mod methods
	// End of user code block mod methods
}