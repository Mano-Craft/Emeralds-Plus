/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package de.mcreator.emeraldsplus.init;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.Identifier;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.HumanoidModel;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.Map;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

import de.mcreator.emeraldsplus.client.renderer.item.EmeraldarmorArmor;

@Environment(EnvType.CLIENT)
public class EmeraldsplusModArmorModels {
	public static Map<Item, ArmorModel> ARMOR_MODELS = new Reference2ObjectOpenHashMap<>();

	public static interface ArmorModel {
		default Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
			return original;
		}

		@Nullable
		default Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier _default) {
			return null;
		}

		default Model getGenericArmorModel(ItemStack itemStack, EquipmentClientInfo.LayerType layerType, Model original) {
			Model replacement = getHumanoidArmorModel(itemStack, layerType, original);
			if (replacement != original) {
				if (original instanceof HumanoidModel<?> originalHumanoid && replacement instanceof HumanoidModel<?> replacementHumanoid) {
					originalHumanoid.copyTransforms(replacement);
					replacementHumanoid.head.visible = originalHumanoid.head.visible;
					replacementHumanoid.hat.visible = originalHumanoid.hat.visible;
					replacementHumanoid.body.visible = originalHumanoid.body.visible;
					replacementHumanoid.rightArm.visible = originalHumanoid.rightArm.visible;
					replacementHumanoid.leftArm.visible = originalHumanoid.leftArm.visible;
					replacementHumanoid.rightLeg.visible = originalHumanoid.rightLeg.visible;
					replacementHumanoid.leftLeg.visible = originalHumanoid.leftLeg.visible;
				}
				return replacement;
			}
			return original;
		}
	}

	public static void clientLoad() {
		EmeraldarmorArmor.clientLoad();
	}
}