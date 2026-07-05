package de.mcreator.emeraldsplus.client.renderer.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.minecraft.client.resources.model.EquipmentClientInfo;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import de.mcreator.emeraldsplus.init.EmeraldsplusModItems;
import de.mcreator.emeraldsplus.init.EmeraldsplusModArmorModels;

@Environment(EnvType.CLIENT)
public class EmeraldarmorArmor {
	public static void clientLoad() {
		EmeraldsplusModArmorModels.ARMOR_MODELS.put(EmeraldsplusModItems.EMERALDARMOR_HELMET, new EmeraldsplusModArmorModels.ArmorModel() {
			private final Identifier armorTexture = Identifier.parse("emeraldsplus:textures/entity/equipment/humanoid/emerald_layer_1.png");

			@Override
			public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier original) {
				return armorTexture;
			}
		});
		EmeraldsplusModArmorModels.ARMOR_MODELS.put(EmeraldsplusModItems.EMERALDARMOR_CHESTPLATE, new EmeraldsplusModArmorModels.ArmorModel() {
			private final Identifier armorTexture = Identifier.parse("emeraldsplus:textures/entity/equipment/humanoid/emerald_layer_1.png");

			@Override
			public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier original) {
				return armorTexture;
			}
		});
		EmeraldsplusModArmorModels.ARMOR_MODELS.put(EmeraldsplusModItems.EMERALDARMOR_LEGGINGS, new EmeraldsplusModArmorModels.ArmorModel() {
			private final Identifier armorTexture = Identifier.parse("emeraldsplus:textures/entity/equipment/humanoid_leggings/emerald_layer_2.png");

			@Override
			public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier original) {
				return armorTexture;
			}
		});
		EmeraldsplusModArmorModels.ARMOR_MODELS.put(EmeraldsplusModItems.EMERALDARMOR_BOOTS, new EmeraldsplusModArmorModels.ArmorModel() {
			private final Identifier armorTexture = Identifier.parse("emeraldsplus:textures/entity/equipment/humanoid/emerald_layer_1.png");

			@Override
			public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier original) {
				return armorTexture;
			}
		});
	}
}