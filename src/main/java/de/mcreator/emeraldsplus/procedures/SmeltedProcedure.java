package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;

import de.mcreator.emeraldsplus.EmeraldsplusMod;

public class SmeltedProcedure {

	private static final List<TagKey<Block>> ORE_FAMILIES = List.of(
			BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.IRON_ORES,
			BlockTags.GOLD_ORES, BlockTags.DIAMOND_ORES, BlockTags.EMERALD_ORES,
			BlockTags.LAPIS_ORES, BlockTags.REDSTONE_ORES
	);

	public static void execute(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		if (world == null || pos == null || blockstate == null || entity == null || itemstack == null)
			return;
		if (!(world instanceof ServerLevel serverLevel))
			return;

		Holder<Enchantment> smelted = world.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT,
						Identifier.parse("emeraldsplus:smelted")));

		int level = EnchantmentHelper.getItemEnchantmentLevel(smelted, itemstack);
		if (level <= 0)
			return;

		if (level == 1 && !isOre(blockstate))
			return;

		BlockPos capturedPos = pos.immutable();

		EmeraldsplusMod.queueServerWork(1, () -> {
			AABB searchArea = new AABB(capturedPos).inflate(1.5);

			for (ItemEntity itemEntity : serverLevel.getEntitiesOfClass(ItemEntity.class, searchArea)) {
				ItemStack drop = itemEntity.getItem();
				SingleRecipeInput input = new SingleRecipeInput(drop);
				Optional<RecipeHolder<SmeltingRecipe>> recipe = serverLevel.recipeAccess()
						.getRecipeFor(RecipeType.SMELTING, input, serverLevel);

				recipe.ifPresent(holder -> {
					ItemStack resultTemplate = holder.value().assemble(input);
					if (resultTemplate.isEmpty())
						return;

					ItemStack smeltedStack = resultTemplate.copy();
					smeltedStack.setCount(resultTemplate.getCount() * drop.getCount());
					itemEntity.setItem(smeltedStack);
				});
			}
		});
	}

	private static boolean isOre(BlockState state) {
		for (TagKey<Block> tag : ORE_FAMILIES)
			if (state.is(tag))
				return true;
		return state.is(Blocks.NETHER_QUARTZ_ORE) || state.is(Blocks.NETHER_GOLD_ORE) || state.is(Blocks.ANCIENT_DEBRIS);
	}
}