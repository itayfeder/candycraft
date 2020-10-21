package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SweetGrassBlock extends FlowerBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public SweetGrassBlock() {
        super(Effects.SATURATION, 0, AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.PINK)
                .doesNotBlockMovement()
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0f, 0f));
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.NONE;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.PUDDING.get();
    }
}
