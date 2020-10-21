package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;

public class PuddingBlock extends Block {
    public PuddingBlock() {
        super(AbstractBlock.Properties.create(Material.EARTH)
                .sound(SoundType.GROUND)
                .hardnessAndResistance(0.6f, 0.6f));


    }
}