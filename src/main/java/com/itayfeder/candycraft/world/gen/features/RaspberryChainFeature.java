package com.itayfeder.candycraft.world.gen.features;

import com.itayfeder.candycraft.blocks.TallRaspberryChainBlock;
import com.itayfeder.candycraft.init.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class RaspberryChainFeature extends Feature<ProbabilityConfig> {
    public RaspberryChainFeature(Codec<ProbabilityConfig> p_i231988_1_) {
        super(p_i231988_1_);
    }

    public boolean func_241855_a(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, ProbabilityConfig p_241855_5_) {
        boolean flag = false;
        int i = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
        int j = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
        int k = p_241855_1_.getHeight(Heightmap.Type.OCEAN_FLOOR, p_241855_4_.getX() + i, p_241855_4_.getZ() + j);
        BlockPos blockpos = new BlockPos(p_241855_4_.getX() + i, k, p_241855_4_.getZ() + j);
        if (p_241855_1_.getBlockState(blockpos).isIn(Blocks.WATER)) {
            boolean flag1 = p_241855_3_.nextDouble() < (double)p_241855_5_.probability;
            BlockState blockstate = flag1 ? ModBlocks.TALL_RASPBERRY_CHAIN.get().getDefaultState() : ModBlocks.RASPBERRY_CHAIN.get().getDefaultState();
            if (blockstate.isValidPosition(p_241855_1_, blockpos)) {
                if (flag1) {
                    BlockState blockstate1 = blockstate.with(TallRaspberryChainBlock.field_208065_c, DoubleBlockHalf.UPPER);
                    BlockPos blockpos1 = blockpos.up();
                    if (p_241855_1_.getBlockState(blockpos1).isIn(Blocks.WATER)) {
                        p_241855_1_.setBlockState(blockpos, blockstate, 2);
                        p_241855_1_.setBlockState(blockpos1, blockstate1, 2);
                    }
                } else {
                    p_241855_1_.setBlockState(blockpos, blockstate, 2);
                }

                flag = true;
            }
        }

        return flag;
    }
}
