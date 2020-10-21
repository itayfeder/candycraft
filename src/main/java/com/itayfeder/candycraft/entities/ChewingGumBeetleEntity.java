package com.itayfeder.candycraft.entities;

import com.itayfeder.candycraft.init.ModEntities;
import com.itayfeder.candycraft.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChewingGumBeetleEntity extends AnimalEntity {
    private static final DataParameter<Boolean> ANGRY = EntityDataManager.createKey(ChewingGumBeetleEntity.class, DataSerializers.BOOLEAN);

    public void setAngry(boolean par1)
    {
        if (par1)
        {
            this.getAttribute(Attributes.field_233821_d_).setBaseValue(0.5);
            this.getAttribute(Attributes.field_233823_f_).setBaseValue(6);
            if (this.isChild())
            {
                this.getAttribute(Attributes.field_233823_f_).setBaseValue(4);
            }
        }
        this.dataManager.set(ANGRY, par1);
    }

    public boolean getAngry()
    {
        return this.dataManager.get(ANGRY).booleanValue();
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        if (this.isChild() && par1DamageSource.getTrueSource() != null && par1DamageSource.getTrueSource() instanceof PlayerEntity)
        {
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(32.0D, 32.0D, 32.0D));

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = (Entity) list.get(i);

                if (entity1 instanceof ChewingGumBeetleEntity)
                {
                    ChewingGumBeetleEntity entitybeetle = (ChewingGumBeetleEntity) entity1;
                    entitybeetle.setAngry(true);
                }
            }
        }
        super.onDeath(par1DamageSource);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ANGRY, false);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("IsAngry", this.getAngry());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setAngry(compound.getBoolean("IsAngry"));
    }

    public ChewingGumBeetleEntity(EntityType<ChewingGumBeetleEntity> type, World world) {
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
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(ModItems.LICORICE.get(), 1).getItem()), false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new SwimGoal(this));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
    }

    @Override
    public net.minecraft.util.SoundEvent getAmbientSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.silverfish.ambient"));
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.silverfish.hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.skeleton.death"));
    }

    //func_233666_p_ ---> registerAttributes()
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 25D)
                .func_233815_a_(Attributes.field_233821_d_, 0.35D)
                .func_233815_a_(Attributes.field_233826_i_, 0.0D)
                .func_233815_a_(Attributes.field_233823_f_, 3.0D);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (stack == null)
            return false;
        if (new ItemStack(ModItems.LICORICE.get(), (int) (1)).getItem() == stack.getItem())
            return true;
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return (ChewingGumBeetleEntity) ModEntities.CHEWING_GUM_BEETLE.get().create(p_241840_1_);
    }

    @Override
    //@Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        Random rand = new Random();
        if (rand.nextInt(10) == 0)
        {
            ChewingGumBeetleEntity child = (ChewingGumBeetleEntity) this.func_241840_a(worldIn.getWorld(), this);
            child.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
            this.world.addEntity(child);
            child.startRiding(this);
            child.setGrowingAge(-24000);
            child.getAttribute(Attributes.field_233823_f_).setBaseValue(1.5D);
            child.getAttribute(Attributes.field_233818_a_).setBaseValue(12);
            //child.setAngry(false);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

}
