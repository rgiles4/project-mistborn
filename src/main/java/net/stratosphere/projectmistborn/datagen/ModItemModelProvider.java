package net.stratosphere.projectmistborn.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.stratosphere.projectmistborn.ProjectMistborn;
import net.stratosphere.projectmistborn.items.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ProjectMistborn.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.STEEL);
        simpleItem(ModItems.STEEL_NUGGET);
        simpleItem(ModItems.TIN);
        simpleItem(ModItems.TIN_NUGGET);
        simpleItem(ModItems.RAW_TIN);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ProjectMistborn.MOD_ID, "item/" + item.getId().getPath()));
    }
}
