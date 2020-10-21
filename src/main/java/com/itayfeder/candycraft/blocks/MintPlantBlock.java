package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class MintPlantBlock extends AbstractCoralPlantBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);

    public MintPlantBlock() {
        super(Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).doesNotBlockMovement().sound(SoundType.CORAL)
                .hardnessAndResistance(0f, 0f));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 5;
    }
}
