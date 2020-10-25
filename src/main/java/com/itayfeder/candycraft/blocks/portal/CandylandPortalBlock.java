package com.itayfeder.candycraft.blocks.portal;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.init.ModBlocks;
import com.itayfeder.candycraft.init.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Random;

public class CandylandPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public CandylandPortalBlock() {
        super(AbstractBlock.Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).func_235838_a_((p_235463_0_) -> {
            return 11;
        }));
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch((Direction.Axis)state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.func_230315_m_().func_236043_f_() && worldIn.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < worldIn.getDifficulty().getId()) {
            while(worldIn.getBlockState(pos).isIn(this)) {
                pos = pos.down();
            }
        }

    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && !facingState.isIn(this) && !(new CandylandPortalSize(worldIn, currentPos, direction$axis1)).func_208508_f() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        Direction.Axis direction$axis1 = state.get(AXIS);
        if(!worldIn.isRemote){
            CandylandPortalTeleporter tp;
            if (entityIn.getEntityWorld() == ((ServerWorld)worldIn).getServer().getWorld(CandyCraft.DIMENSION)) {
                tp = new CandylandPortalTeleporter(((ServerWorld)worldIn).getServer().getWorld(World.field_234918_g_));
                entityIn.changeDimension(((ServerWorld)worldIn).getServer().getWorld(World.field_234918_g_), tp);

            }
            else {
                tp = new CandylandPortalTeleporter(((ServerWorld)worldIn).getServer().getWorld(CandyCraft.DIMENSION));
                entityIn.changeDimension(((ServerWorld)worldIn).getServer().getWorld(CandyCraft.DIMENSION), tp);
            }

            if (!DoesHavePortal((int)Math.round(entityIn.getPosX()), (int)Math.round(entityIn.getPosY()), (int)Math.round(entityIn.getPosZ()), entityIn.getEntityWorld())) {
                tp.buildPortal(new BlockPos(entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ()), direction$axis1, entityIn.getEntityWorld());
                BlockPos p = LookForPortal((int)Math.round(entityIn.getPosX()), (int)Math.round(entityIn.getPosY()), (int)Math.round(entityIn.getPosZ()), entityIn.getEntityWorld());
                TeleportToPortal(p, entityIn, direction$axis1);
            }
            else {
                BlockPos p = LookForPortal((int)Math.round(entityIn.getPosX()), (int)Math.round(entityIn.getPosY()), (int)Math.round(entityIn.getPosZ()), entityIn.getEntityWorld());
                Direction.Axis axis = entityIn.getEntityWorld().getBlockState(p).get(CandylandPortalBlock.AXIS);
                TeleportToPortal(p, entityIn, axis);
            }
        }
    }

    public void TeleportToPortal(BlockPos p, Entity entityIn, Direction.Axis axis) {
        if (axis == Direction.Axis.X) {
            BlockPos b = new BlockPos(p.getX(), p.getY(), p.getZ() + 1);
            BlockPos b2 = new BlockPos(p.getX(), p.getY(), p.getZ() - 1);
            boolean DoesBlockMovement = !((boolean)ObfuscationReflectionHelper.getPrivateValue(AbstractBlock.class, entityIn.getEntityWorld().getBlockState(b).getBlock(), "field_235688_at_")) || entityIn.getEntityWorld().isAirBlock(b);
            boolean DoesBlockMovement2 = !((boolean)ObfuscationReflectionHelper.getPrivateValue(AbstractBlock.class, entityIn.getEntityWorld().getBlockState(b2).getBlock(), "field_235688_at_")) || entityIn.getEntityWorld().isAirBlock(b2);
            if (DoesBlockMovement && DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() + 1, p.getY(), p.getZ() + 1.5);

            }
            else if (DoesBlockMovement && !DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() + 1, p.getY(), p.getZ() + 1.5);

            }
            else if (!DoesBlockMovement && DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() + 1, p.getY(), p.getZ() - 0.5);

            } else {
                entityIn.getEntityWorld().setBlockState(new BlockPos(p.getX() + 1, p.getY(), p.getZ() + 1.5), Blocks.AIR.getDefaultState());
                entityIn.getEntityWorld().setBlockState(new BlockPos(p.getX() + 1, p.getY() + 1, p.getZ() + 1.5), Blocks.AIR.getDefaultState());
                entityIn.setPositionAndUpdate(p.getX(), p.getY(), p.getZ() + 1.5);
            }
        }
        else {
            BlockPos b = new BlockPos(p.getX() + 1, p.getY(), p.getZ());
            BlockPos b2 = new BlockPos(p.getX() - 1, p.getY(), p.getZ());
            boolean DoesBlockMovement = !((boolean)ObfuscationReflectionHelper.getPrivateValue(AbstractBlock.class, entityIn.getEntityWorld().getBlockState(b).getBlock(), "field_235688_at_")) || entityIn.getEntityWorld().isAirBlock(b);
            boolean DoesBlockMovement2 = !((boolean)ObfuscationReflectionHelper.getPrivateValue(AbstractBlock.class, entityIn.getEntityWorld().getBlockState(b2).getBlock(), "field_235688_at_")) || entityIn.getEntityWorld().isAirBlock(b2);
            if (DoesBlockMovement && DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() -0.5, p.getY(), p.getZ() + 1);
            }
            else if (!DoesBlockMovement && DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() -0.5, p.getY(), p.getZ() + 1);

            }
            else if (DoesBlockMovement && !DoesBlockMovement2) {
                entityIn.setPositionAndUpdate(p.getX() + 1.5, p.getY(), p.getZ() + 1);

            } else {
                entityIn.getEntityWorld().setBlockState(new BlockPos(p.getX() + 1.5, p.getY(), p.getZ() + 1), Blocks.AIR.getDefaultState());
                entityIn.getEntityWorld().setBlockState(new BlockPos(p.getX() + 1.5, p.getY() + 1, p.getZ() + 1), Blocks.AIR.getDefaultState());
                entityIn.setPositionAndUpdate(p.getX() + 1.5, p.getY(), p.getZ());

            }
        }
    }

    public BlockPos LookForPortal(int posX, int posY, int posZ, World worldIn) {
        BlockPos startPos = new BlockPos(posX - 24, 0, posZ - 24);
        BlockPos endPos = new BlockPos(posX + 24, 256, posZ + 24);
        Iterable<BlockPos> poses = BlockPos.getAllInBoxMutable(startPos, endPos);

        for (BlockPos pos : poses) {
            if (worldIn.getBlockState(pos).getBlock() == ModBlocks.CANDYLAND_PORTAL.get()) {
                return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            }
        }
        System.out.println("Found no portal!");
        return new BlockPos(posX, posY, posZ);
    }

    public boolean DoesHavePortal(int posX, int posY, int posZ, World worldIn) {
        BlockPos startPos = new BlockPos(posX - 24, 0, posZ - 24);
        BlockPos endPos = new BlockPos(posX + 24, 256, posZ + 24);
        Iterable<BlockPos> poses = BlockPos.getAllInBoxMutable(startPos, endPos);

        for (BlockPos pos : poses) {
            if (worldIn.getBlockState(pos).getBlock() == ModBlocks.CANDYLAND_PORTAL.get()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
     * of whether the block can receive random update ticks
     */
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (!worldIn.getBlockState(pos.west()).isIn(this) && !worldIn.getBlockState(pos.east()).isIn(this)) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            worldIn.addParticle(ModParticles.CARAMEL_PORTAL.get(), d0, d1, d2, d3, d4, d5);
        }

    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch((Direction.Axis)state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}