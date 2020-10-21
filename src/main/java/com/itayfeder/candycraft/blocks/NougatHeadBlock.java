package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.entities.NougatGolemEntity;
import com.itayfeder.candycraft.init.ModBlocks;
import com.itayfeder.candycraft.init.ModEntities;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NougatHeadBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public NougatHeadBlock() {
        super(Block.Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(6f, 6f)
                .harvestLevel(2));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            this.trySpawnGolem(worldIn, pos);
        }
    }

    private void trySpawnGolem(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos.down()).getBlock() != ModBlocks.NOUGAT_BLOCK.get() || worldIn.getBlockState(pos.down(2)).getBlock() != ModBlocks.NOUGAT_BLOCK.get())
        {
            return;
        }
        int lenght = 0;
        BlockPos pos2 = pos;
        worldIn.setBlockState(pos2, Blocks.AIR.getDefaultState(), 1);
        worldIn.notifyBlockUpdate(pos2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), 2);
        lenght++;
        pos2 = pos2.down(1);
        while (worldIn.getBlockState(pos2) == ModBlocks.NOUGAT_BLOCK.get().getDefaultState() && pos2.getY() > 0)
        {
            worldIn.setBlockState(pos2, Blocks.AIR.getDefaultState(), 1);
            worldIn.notifyBlockUpdate(pos2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), 2);
            pos2 = pos2.down(1);
            lenght++;
        }

        NougatGolemEntity[] golemList = new NougatGolemEntity[lenght];

        for (int j = 0; j < lenght + 1; j++)
        {
            NougatGolemEntity golem = new NougatGolemEntity(ModEntities.NOUGAT_GOLEM.get(), worldIn);
            golem.setPosition(pos.getX() + 0.5D, pos.getY() - lenght + 1, pos.getZ() + 0.5D);

            golemList[j] = golem;

            if (j != 0)
            {
                golem.startRiding(golemList[j - 1]);
            }
            worldIn.addEntity(golem);
        }


    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }



}
