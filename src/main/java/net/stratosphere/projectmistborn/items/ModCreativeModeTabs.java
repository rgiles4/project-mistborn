package net.stratosphere.projectmistborn.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.stratosphere.projectmistborn.ProjectMistborn;

import java.awt.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectMistborn.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MISTBORN_TAB = CREATIVE_MODE_TABS.register("project_mistborn_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL.get())) // Tab Icon
                    .title(Component.translatable("creativetab.project_mistborn_tab")) // Tab Name
                    .displayItems((pParameters, pOutput) -> { // Items to appear in the creative tab; This can be done with a for-each loop as well. See comment below
                        pOutput.accept(ModItems.STEEL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}

/*
* Use a for-each loop to add every item in the mod to the creative tab instead of individually
* .displayItems((pParameters, pOutput -> {
*   for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
*       pOutput.accept(item.get());
*       }
*   })
*/