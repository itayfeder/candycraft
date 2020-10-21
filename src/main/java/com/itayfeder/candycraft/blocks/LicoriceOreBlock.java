package com.itayfeder.candycraft.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class LicoriceOreBlock extends OreBlock {
    public LicoriceOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(3f, 3f)
                .harvestLevel(1));
    }
}