package net.stratosphere.projectmistborn.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.stratosphere.projectmistborn.ProjectMistborn;
import net.stratosphere.projectmistborn.block.ModBlocks;
import net.stratosphere.projectmistborn.items.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    // Items that produce the same result when smelted should be added to the same list
    private static final List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN.get(), ModBlocks.TIN_ORE.get());

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // SMELTING RECIPES //
        oreSmelting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN.get(), 0.25f, 200, "tin");
        oreBlasting(recipeOutput, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN.get(), 0.25f, 100, "tin");

        // SHAPED CRAFTING RECIPES //
        // Steel Block from Ingots
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEEL.get())
                .unlockedBy(getHasName(ModItems.STEEL.get()), has(ModItems.STEEL.get()))
                .save(recipeOutput);

        // Steel Ingot from Nuggets
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEEL_NUGGET.get())
                .unlockedBy(getHasName(ModItems.STEEL.get()), has(ModItems.STEEL.get()))
                .save(recipeOutput, "project_mistborn:steel_from_nuggets");

        // Tin Block from Ingots
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TIN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.TIN.get())
                .unlockedBy(getHasName(ModItems.TIN.get()), has(ModItems.TIN.get()))
                .save(recipeOutput);

        // Tin Ingot from Nuggets
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIN.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.TIN_NUGGET.get())
                .unlockedBy(getHasName(ModItems.TIN.get()), has(ModItems.TIN.get()))
                .save(recipeOutput, "project_mistborn:tin_from_nuggets");

        // SHAPELESS CRAFTING RECIPES //
        // Steel Ingots from Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.STEEL_BLOCK.get()), has(ModBlocks.STEEL_BLOCK.get()))
                .save(recipeOutput, "project_mistborn:steel_from_block");

        // Steel Nuggets from Ingots
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_NUGGET.get(), 9)
                .requires(ModItems.STEEL.get())
                .unlockedBy(getHasName(ModItems.STEEL.get()), has(ModItems.STEEL.get()))
                .save(recipeOutput);

        // Tin Ingots from Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN.get(), 9)
                .requires(ModBlocks.TIN_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TIN_BLOCK.get()), has(ModBlocks.TIN_BLOCK.get()))
                .save(recipeOutput, "project_mistborn:tin_from_block");

        // Tin Nuggets from Ingots
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_NUGGET.get(), 9)
                .requires(ModItems.TIN.get())
                .unlockedBy(getHasName(ModItems.TIN.get()), has(ModItems.TIN.get()))
                .save(recipeOutput);
    }

    // Method to create a smelting recipe
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    // Method to create a blasting recipe
    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    // DONT TOUCH THIS
    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        Iterator var10 = pIngredients.iterator();

        while(var10.hasNext()) {
            ItemLike itemlike = (ItemLike)var10.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, ProjectMistborn.MOD_ID + ":" + (pResult) + pSuffix + "_" + getItemName(itemlike));
        }

    }
}
