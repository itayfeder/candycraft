package com.itayfeder.candycraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class NougatOreBlock extends OreBlock {
    public NougatOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(5f, 5f)
                .harvestLevel(2));
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return 1;
    }
}