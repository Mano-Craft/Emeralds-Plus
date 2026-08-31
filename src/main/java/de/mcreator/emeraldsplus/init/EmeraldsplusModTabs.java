/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package de.mcreator.emeraldsplus.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Registry;

import de.mcreator.emeraldsplus.EmeraldsplusMod;

public class EmeraldsplusModTabs {
	public static ResourceKey<CreativeModeTab> TAB_EMERALDS_PLUS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "emeralds_plus"));
	public static ResourceKey<CreativeModeTab> TAB_EMERALDSPLUSENTCHANTS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "emeraldsplusentchants"));

	public static void load() {
		// Erster Tab (Smaragd-Ausrüstung)
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_EMERALDS_PLUS,
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.emeraldsplus.emeralds_plus")).icon(() -> new ItemStack(EmeraldsplusModItems.EMERALD_UPGRADE)).displayItems((parameters, tabData) -> {
					tabData.accept(EmeraldsplusModItems.EMERALDSWORD);
					tabData.accept(EmeraldsplusModItems.EMERALDAXE);
					tabData.accept(EmeraldsplusModItems.EMERALD_PICKAXE);
					tabData.accept(EmeraldsplusModItems.EMERALDSHOVEL);
					tabData.accept(EmeraldsplusModItems.EMERALDHOE);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_HELMET);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_CHESTPLATE);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_LEGGINGS);
					tabData.accept(EmeraldsplusModItems.EMERALDARMOR_BOOTS);
                    tabData.accept(EmeraldsplusModItems.EMERALDSHIELD);
					tabData.accept(EmeraldsplusModItems.EMERALD_UPGRADE);
				}).build());
				
		// Zweiter Tab (Deine Verzauberungen)
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_EMERALDSPLUSENTCHANTS,
				// Nutzen hier das sicher von MCreator registrierte Item als Tab-Icon
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.emeraldsplus.emeraldsplusentchants")).icon(() -> new ItemStack(EmeraldsplusModItems.DUMMYITEM)).displayItems((parameters, tabData) -> {
					
					// --- BUCH 1: GOLEM PROTECTION 4 ---
					ItemStack golemBook1 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "multi_break")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemBook1, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(golemBook1);
					
					ItemStack golemBook2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "multi_break")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemBook2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(golemBook2);

					ItemStack golemBook3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "multi_break")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemBook3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(golemBook3);

					ItemStack golemBook4 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "multi_break")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemBook4, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 4); 
							});
						});
					});
					tabData.accept(golemBook4);

					ItemStack golemBook5 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "multi_break")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemBook5, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 5); 
							});
						});
					});
					tabData.accept(golemBook5);

					ItemStack luck1 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "luck")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(luck1, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(luck1);

					ItemStack luck2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "luck")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(luck2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(luck2);

					ItemStack luck3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "luck")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(luck3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(luck3);

					ItemStack veinminer = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "vein_miner")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(veinminer, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(veinminer);

					ItemStack villageprot1 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protection")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprot1, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(villageprot1);

					ItemStack villageprot2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protection")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprot2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(villageprot2);

					ItemStack villageprot3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protection")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprot3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(villageprot3);

					ItemStack villageprot4 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protection")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprot4, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 4); 
							});
						});
					});
					tabData.accept(villageprot4);

					ItemStack villageprotector1 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protector")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprotector1, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(villageprotector1);

					ItemStack villageprotector2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protector")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprotector2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(villageprotector2);

					ItemStack villageprotector3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "village_protector")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(villageprotector3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(villageprotector3);

					ItemStack fastfarm1 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "fast_farm")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(fastfarm1, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(fastfarm1);

					ItemStack fastfarm2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "fast_farm")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(fastfarm2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(fastfarm2);

                    ItemStack fastfarm3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "fast_farm")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(fastfarm3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(fastfarm3);

					ItemStack smelted = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "smelted")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(smelted, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(smelted);

					ItemStack smelted2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "smelted")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(smelted2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(smelted2);

					ItemStack instatree = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "insta_tree")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(instatree, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(instatree);

					ItemStack instatree2 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "insta_tree")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(instatree2, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 2); 
							});
						});
					});
					tabData.accept(instatree2);

					ItemStack instatree3 = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "insta_tree")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(instatree3, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 3); 
							});
						});
					});
					tabData.accept(instatree3);

					ItemStack powershield = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "power_shield")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(powershield, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(powershield);

					ItemStack golemprot = new ItemStack(Items.ENCHANTED_BOOK);
					parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
						ResourceKey<net.minecraft.world.item.enchantment.Enchantment> customKey = ResourceKey.create(
							Registries.ENCHANTMENT, 
							Identifier.fromNamespaceAndPath(EmeraldsplusMod.MODID, "golem_protection")
						);
						enchantments.get(customKey).ifPresent((customEnchantHolder) -> {
							EnchantmentHelper.updateEnchantments(golemprot, (mutableEnchants) -> {
								mutableEnchants.set(customEnchantHolder, 1); 
							});
						});
					});
					tabData.accept(golemprot);

				}).build());
	}
}
