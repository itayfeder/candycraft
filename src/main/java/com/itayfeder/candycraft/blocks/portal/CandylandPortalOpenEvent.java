package com.itayfeder.candycraft.blocks.portal;

import com.itayfeder.candycraft.CandyCraft;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = CandyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CandylandPortalOpenEvent {

    @SubscribeEvent
    public static void PlaceBlockEvent(BlockEvent.EntityPlaceEvent event) {
        if (BlockTags.field_232872_am_.func_230236_b_().contains(event.getPlacedBlock().getBlock())) {
            if (compareWorlds((World) event.getBlockSnapshot().getWorld())) {
                Optional<CandylandPortalSize> optional = CandylandPortalSize.createOptional(event.getBlockSnapshot().getWorld(), event.getBlockSnapshot().getPos(), Direction.Axis.X);
                if (optional.isPresent()) {
                    optional.get().placePortalBlocks();
                    return;
                }
            }
        }

    }

    private static boolean compareWorlds(World p_242649_0_) {
        return p_242649_0_.func_234923_W_() == World.field_234918_g_ || p_242649_0_.func_234923_W_() == CandyCraft.DIMENSION;
    }

}
