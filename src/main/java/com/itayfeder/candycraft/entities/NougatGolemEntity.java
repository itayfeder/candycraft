package com.itayfeder.candycraft.entities;

import com.itayfeder.candycraft.entities.goals.ExplodeGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class NougatGolemEntity extends GolemEntity {

    public NougatGolemEntity(EntityType<NougatGolemEntity> type, World world) {
        super(type, world);
        experienceValue = 0;
        setNoAI(false);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ExplodeGoal(this, 1.2, false));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new SwimGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 0, false, true, null));
    }

    public boolean isTop() {
        return !this.isBeingRidden();
    }

    public boolean isBase() {
        return this.getRidingEntity() == null;
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.hit"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.break"));
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        if (this.isTop() || this.isBase()) {
            return super.getSize(poseIn).scale(0.8F);
        }
        else {
            return super.getSize(poseIn).scale(0.64F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (!this.world.isRemote() && par1DamageSource.isExplosion())
        {
            return false;
        }
        if (!this.world.isRemote() && getHealth() - par2 <= 0)
        {
            if (getRidingEntity() != null && (GetEntityRiding() != null))
            {
                //this.getPassengers().get(0).getRidingEntity() = null;
                GetEntityRiding().setPosition(this.getPosX(), this.getPosY() + 2.0D, this.getPosZ());
                //this.getPassengers().get(0) = null;
                return super.attackEntityFrom(par1DamageSource, par2);
            }
            if (isBase() && (GetEntityRiding() != null))
            {
                //this.getPassengers().get(0).getRidingEntity() = null;
                GetEntityRiding().setPosition(this.getPosX(), this.getPosY() + 2.0D, this.getPosZ());
                return super.attackEntityFrom(par1DamageSource, par2);
            }
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    private Entity GetEntityRiding() {
        if (this.getPassengers().isEmpty()) {
            return null;
        }
        else {
            return this.getPassengers().get(0);
        }
    }

    @Override
    public double getMountedYOffset()
    {
        if (this.isTop() || this.isBase()) {
            return 0.8D;
        }
        else {
            return 0.64D;
        }
    }

    @Override
    public boolean canBeRiddenInWater()
    {
        return true;
    }

    @Override
    public void livingTick() {
        this.recalculateSize();
        super.livingTick();
    }

    @Override
    public void recalculateSize() {
        double d0 = this.getPosX();
        double d1 = this.getPosY();
        double d2 = this.getPosZ();
        super.recalculateSize();
        this.setPosition(d0, d1, d2);
    }

    //func_233666_p_ ---> registerAttributes()
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 25.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.25D)
                .func_233815_a_(Attributes.field_233826_i_, 0.0D)
                .func_233815_a_(Attributes.field_233823_f_, 3.0D);
    }
}