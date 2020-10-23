package com.itayfeder.candycraft;

import com.itayfeder.candycraft.entities.ChewingGumBeetleEntity;
import com.itayfeder.candycraft.entities.CranfishEntity;
import com.itayfeder.candycraft.entities.GummyBunnyEntity;
import com.itayfeder.candycraft.entities.NougatGolemEntity;
import com.itayfeder.candycraft.init.ModEntities;
import com.itayfeder.candycraft.util.CarverReflection;
import com.itayfeder.candycraft.util.RegistryHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("candycraft_resugarized")
public class CandyCraft
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "candycraft_resugarized";

    public static final RegistryKey<World> DIMENSION = RegistryKey.func_240903_a_(Registry.field_239699_ae_, new ResourceLocation(MOD_ID, "candyland"));

    public CandyCraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.GUMMY_BUNNY.get(), GummyBunnyEntity.setCustomAttributes().func_233813_a_());
            GlobalEntityTypeAttributes.put(ModEntities.CHEWING_GUM_BEETLE.get(), ChewingGumBeetleEntity.setCustomAttributes().func_233813_a_());
            GlobalEntityTypeAttributes.put(ModEntities.NOUGAT_GOLEM.get(), NougatGolemEntity.setCustomAttributes().func_233813_a_());
            GlobalEntityTypeAttributes.put(ModEntities.CRANFISH.get(), CranfishEntity.setCustomAttributes().func_233813_a_());

        });

        CarverReflection.AddCarverBlocks();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RegistryHandler.clientInit();
    }

    public static final ItemGroup TAB = new ItemGroup("candycraftTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.SUGAR);
        }
    };
}
