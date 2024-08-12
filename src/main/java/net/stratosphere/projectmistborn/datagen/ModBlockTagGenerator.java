package net.stratosphere.projectmistborn.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.stratosphere.projectmistborn.ProjectMistborn;
import net.stratosphere.projectmistborn.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ProjectMistborn.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // TOOLS //
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TIN_BLOCK.get(),
                        ModBlocks.TIN_ORE.get(),
                        ModBlocks.STEEL_BLOCK.get()
                );

        // TOOL TIERS//
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_BLOCK.get(),
                        ModBlocks.TIN_ORE.get()
                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
    }
}
