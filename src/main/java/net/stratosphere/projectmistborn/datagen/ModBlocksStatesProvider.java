package net.stratosphere.projectmistborn.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.stratosphere.projectmistborn.ProjectMistborn;
import net.stratosphere.projectmistborn.block.ModBlocks;

public class ModBlocksStatesProvider extends BlockStateProvider {
    public ModBlocksStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ProjectMistborn.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Blocks //
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.TIN_BLOCK);

        // Ores //
        blockWithItem(ModBlocks.TIN_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
