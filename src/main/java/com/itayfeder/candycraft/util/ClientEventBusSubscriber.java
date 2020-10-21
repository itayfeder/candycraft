package com.itayfeder.candycraft.util;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.Items.ModSpawnEggItem;
import com.itayfeder.candycraft.client.render.ChewingGumBeetleRenderer;
import com.itayfeder.candycraft.client.render.CranfishRenderer;
import com.itayfeder.candycraft.client.render.GummyBunnyRenderer;
import com.itayfeder.candycraft.client.render.NougatGolemRenderer;
import com.itayfeder.candycraft.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GUMMY_BUNNY.get(), GummyBunnyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CHEWING_GUM_BEETLE.get(), ChewingGumBeetleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NOUGAT_GOLEM.get(), NougatGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CRANFISH.get(), CranfishRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
