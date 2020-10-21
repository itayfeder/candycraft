package com.itayfeder.candycraft.entities.goals;

import com.itayfeder.candycraft.entities.NougatGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplodeGoal extends Goal {
    World worldObj;
    MobEntity attacker;
    /**
     * An amount of decrementing ticks that allows the entity to attack once the
     * tick reaches 0.
     */
    int attackTick;
    /**
     * The speed with which the mob will approach the target
     */
    double speedTowardsTarget;
    /**
     * When true, the mob will continue chasing its target, even if it can't
     * find a path to them right now.
     */
    boolean longMemory;
    /**
     * The PathEntity of our entity.
     */
    Path entityPathEntity;
    Class classTarget;
    private int field_75445_i;
    private double field_151497_i;
    private double field_151495_j;
    private double field_151496_k;

    private int failedPathFindingPenalty;

    public ExplodeGoal(MobEntity par1EntityCreature, Class par2Class, double par3, boolean par5) {
        this(par1EntityCreature, par3, par5);
        classTarget = par2Class;
    }

    public ExplodeGoal(MobEntity par1EntityCreature, double par2, boolean par4) {
        attacker = par1EntityCreature;
        worldObj = par1EntityCreature.world;
        speedTowardsTarget = par2;
        longMemory = par4;
    }

    @Override
    public boolean shouldExecute()
    {
        LivingEntity entitylivingbase = attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isAlive())
        {
            return false;
        }
        else if (classTarget != null && !classTarget.isAssignableFrom(entitylivingbase.getClass()))
        {
            return false;
        }
        else
        {
            if (--field_75445_i <= 0)
            {
                entityPathEntity = attacker.getNavigator().getPathToEntity(entitylivingbase, 1);
                field_75445_i = 4 + attacker.getRNG().nextInt(7);
                return entityPathEntity != null;
            }
            else
            {
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        LivingEntity entitylivingbase = attacker.getAttackTarget();
        return entitylivingbase == null ? false : (!entitylivingbase.isAlive() ? false : (!longMemory ? !attacker.getNavigator().noPath() : true));
    }

    @Override
    public void startExecuting()
    {
        attacker.getNavigator().setPath(entityPathEntity, speedTowardsTarget);
        field_75445_i = 0;
    }

    @Override
    public void resetTask()
    {
        attacker.getNavigator().clearPath();
    }

    @Override
    public void tick()
    {
        LivingEntity entitylivingbase = attacker.getAttackTarget();
        attacker.getLookController().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        double d0 = attacker.getDistanceSq(entitylivingbase.getPosX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getPosZ());
        double d1 = attacker.getWidth() * 2.0F * attacker.getWidth() * 2.0F + entitylivingbase.getWidth();
        --field_75445_i;

        if ((longMemory || attacker.getEntitySenses().canSee(entitylivingbase)) && field_75445_i <= 0 && (field_151497_i == 0.0D && field_151495_j == 0.0D && field_151496_k == 0.0D || entitylivingbase.getDistanceSq(field_151497_i, field_151495_j, field_151496_k) >= 1.0D || attacker.getRNG().nextFloat() < 0.05F))
        {
            field_151497_i = entitylivingbase.getPosX();
            field_151495_j = entitylivingbase.getBoundingBox().minY;
            field_151496_k = entitylivingbase.getPosZ();
            field_75445_i = failedPathFindingPenalty + 4 + attacker.getRNG().nextInt(7);

            if (attacker.getNavigator().getPath() != null)
            {
                PathPoint finalPathPoint = attacker.getNavigator().getPath().getFinalPathPoint();
                if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                {
                    failedPathFindingPenalty = 0;
                }
                else
                {
                    failedPathFindingPenalty += 10;
                }
            }
            else
            {
                failedPathFindingPenalty += 10;
            }

            if (d0 > 1024.0D)
            {
                field_75445_i += 10;
            }
            else if (d0 > 256.0D)
            {
                field_75445_i += 5;
            }

            if (!attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, speedTowardsTarget))
            {
                field_75445_i += 15;
            }
        }

        attackTick = Math.max(attackTick - 1, 0);

        if (d0 <= d1 && attackTick <= 20 && ((NougatGolemEntity) attacker).isBase())
        {
            Explosion.Mode var2 = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldObj, attacker) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;

            attackTick = 20;

            NougatGolemEntity last = (NougatGolemEntity) attacker;
            attacker.world.createExplosion(attacker, last.getPosX(), last.getPosY(), last.getPosZ(), 2, var2);
            while (!last.isTop())
            {
                if (last != attacker)
                {
                    attacker.world.createExplosion(attacker, last.getPosX(), last.getPosY(), last.getPosZ(), 2, var2);
                }
                if (last.isBeingRidden())
                {
                    last = (NougatGolemEntity) last.getPassengers().get(0);
                }
                else
                {
                    break;
                }
            }
            last.remove();
        }
    }

}
