package com.itayfeder.candycraft.util;

import com.itayfeder.candycraft.init.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {
    public static void init() {
        ModEntities.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModGenFeatures.GEN_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModTileEntities.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModContainers.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }

    public static void clientInit() {
        RenderTypeLookup.setRenderLayer(ModBlocks.SWEET_GRASS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MINT_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RASPBERRY_CHAIN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_RASPBERRY_CHAIN.get(), RenderType.getCutout());
    }

}
