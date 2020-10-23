package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Optional;

public class NougatBlock extends Block {
    public NougatBlock() {
        super(Block.Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(6f, 6f)
                .harvestLevel(2));
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.getItem() instanceof PickaxeItem) {
            if (!worldIn.isRemote) {
                Direction direction = hit.getFace();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                worldIn.setBlockState(pos, ModBlocks.NOUGAT_HEAD.get().getDefaultState().with(NougatHeadBlock.FACING, direction1), 11);
                itemstack.damageItem(1, player, (p_220282_1_) -> {
                    p_220282_1_.sendBreakAnimation(handIn);
                });
            }

            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }
}
