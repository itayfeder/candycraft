package com.itayfeder.candycraft.util;

import com.itayfeder.candycraft.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class CarverReflection {
    public static void AddCarverBlocks() {
        Set<Block> blocks = ObfuscationReflectionHelper.getPrivateValue(WorldCarver.class, WorldCarver.CAVE, "carvableBlocks");
        Set<Block> newSet = new HashSet<Block>();
        newSet.addAll(blocks);
        newSet.add(ModBlocks.CHOCOLATE_COBBLESTONE.get());
        newSet.add(ModBlocks.FLOUR_BLOCK.get());
        newSet.add(ModBlocks.PUDDING.get());

        ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CAVE, newSet, "carvableBlocks");
        ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CANYON, newSet, "carvableBlocks");
    }
}
