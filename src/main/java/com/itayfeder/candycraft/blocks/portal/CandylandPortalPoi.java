package com.itayfeder.candycraft.blocks.portal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.init.ModBlocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CandylandPortalPoi {
    public static PointOfInterestType poi = null;

    @SubscribeEvent
    public void registerPointOfInterest(RegistryEvent.Register<PointOfInterestType> event) {
        poi = new PointOfInterestType( "candy_land_portal",
                Sets.newHashSet(ImmutableSet.copyOf(ModBlocks.CANDYLAND_PORTAL.get().getStateContainer().getValidStates())), 0, 1);
        event.getRegistry().register(poi);
    }

}
