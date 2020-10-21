package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class TallRaspberryChainBlock extends DoublePlantBlock implements ILiquidContainer {
    public static final EnumProperty<DoubleBlockHalf> field_208065_c = DoublePlantBlock.HALF;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public TallRaspberryChainBlock() {
        super(AbstractBlock.Properties.create(Material.SEA_GRASS)
                .sound(SoundType.WET_GRASS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0f, 0f));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isSolidSide(worldIn, pos, Direction.UP) && !state.isIn(Blocks.MAGMA_BLOCK);
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.RASPBERRY_CHAIN_ITEM.get());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        if (blockstate != null) {
            FluidState fluidstate = context.getWorld().getFluidState(context.getPos().up());
            if (fluidstate.isTagged(FluidTags.WATER) && fluidstate.getLevel() == 8) {
                return blockstate;
            }
        }

        return null;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if (state.get(field_208065_c) == DoubleBlockHalf.UPPER) {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            return blockstate.isIn(this) && blockstate.get(field_208065_c) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidstate = worldIn.getFluidState(pos);
            return super.isValidPosition(state, worldIn, pos) && fluidstate.isTagged(FluidTags.WATER) && fluidstate.getLevel() == 8;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getStillFluidState(false);
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return false;
    }
}