package com.itayfeder.candycraft.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

public class GummyBunnyEntity extends CreatureEntity {
    private static final DataParameter<Integer> BUNNY_TYPE = EntityDataManager.createKey(GummyBunnyEntity.class, DataSerializers.VARINT);
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;


    public GummyBunnyEntity(EntityType<GummyBunnyEntity> type, World world) {
        super(type, world);
        this.jumpController = new GummyBunnyEntity.JumpHelperController(this);
        this.moveController = new GummyBunnyEntity.MoveHelperController(this);
        this.setMovementSpeed(0.0D);
        experienceValue = 0;
        setNoAI(false);
    }

    protected float getJumpUpwardsMotion() {
        if (!this.collidedHorizontally && (!this.moveController.isUpdating() || !(this.moveController.getY() > this.getPosY() + 0.5D))) {
            Path path = this.navigator.getPath();
            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                Vector3d vec3d = path.getPosition(this);
                if (vec3d.y > this.getPosY() + 0.5D) {
                    return 0.6F;
                }
            }

            return this.moveController.getSpeed() <= 0.6D ? 0.6F : 0.6F;
        } else {
            return 0.6F;
        }
    }

    protected void jump() {
        super.jump();
        double d0 = this.moveController.getSpeed();
        if (d0 > 0.0D) {
            double d1 = horizontalMag(this.getMotion());
            if (d1 < 0.01D) {
                this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 1.0D));
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)1);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getJumpCompletion(float p_175521_1_) {
        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed) {
        this.getNavigator().setSpeed(newSpeed);
        this.moveController.setMoveTo(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ(), newSpeed);
    }

    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP;
    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    public void updateAITasks() {
        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            GummyBunnyEntity.JumpHelperController rabbitentity$jumphelpercontroller = (GummyBunnyEntity.JumpHelperController)this.jumpController;
            if (!rabbitentity$jumphelpercontroller.getIsJumping()) {
                if (this.moveController.isUpdating() && this.currentMoveTypeDuration == 0) {
                    Path path = this.navigator.getPath();
                    Vector3d vec3d = new Vector3d(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ());
                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            } else if (!rabbitentity$jumphelpercontroller.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    public void spawnRunningParticles() {
    }

    private void calculateRotationYaw(double x, double z) {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.getPosZ(), x - this.getPosX()) * (double)(180F / (float)Math.PI)) - 90.0F;
    }

    private void enableJumpControl() {
        ((GummyBunnyEntity.JumpHelperController)this.jumpController).setCanJump(true);
    }

    private void disableJumpControl() {
        ((GummyBunnyEntity.JumpHelperController)this.jumpController).setCanJump(false);
    }

    private void updateMoveTypeDuration() {
        if (this.moveController.getSpeed() < 2.2D) {
            this.currentMoveTypeDuration = 10;
        } else {
            this.currentMoveTypeDuration = 1;
        }

    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 1) {
            //this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleStatusUpdate(id);
        }

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new GummyBunnyEntity.PanicGoal(this, 1.2));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new SwimGoal(this));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
    }

    @Override
    public net.minecraft.util.SoundEvent getAmbientSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.rabbit.ambient"));
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.FALL)
            return false;
        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(BUNNY_TYPE, 0);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("GummyType", this.getRabbitType());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setRabbitType(compound.getInt("GummyType"));
    }

    public int getRabbitType() {
        return this.dataManager.get(BUNNY_TYPE);
    }

    public void setRabbitType(int rabbitTypeId) {
        this.dataManager.set(BUNNY_TYPE, rabbitTypeId);
    }

    @Override
    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        Random rnd = new Random();
        int i = rnd.nextInt(6);
        this.setRabbitType(i);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    //func_233666_p_ ---> registerAttributes()
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 10.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.3D)
                .func_233815_a_(Attributes.field_233826_i_, 0.0D)
                .func_233815_a_(Attributes.field_233823_f_, 3.0D);
    }

    public class JumpHelperController extends JumpController {
        private final GummyBunnyEntity rabbit;
        private boolean canJump;

        public JumpHelperController(GummyBunnyEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public boolean getIsJumping() {
            return this.isJumping;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void tick() {
            if (this.isJumping) {
                this.rabbit.startJumping();
                this.isJumping = false;
            }

        }
    }

    static class MoveHelperController extends MovementController {
        private final GummyBunnyEntity rabbit;
        private double nextJumpSpeed;

        public MoveHelperController(GummyBunnyEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public void tick() {
            if (this.rabbit.onGround && !this.rabbit.isJumping && !((GummyBunnyEntity.JumpHelperController)this.rabbit.jumpController).getIsJumping()) {
                this.rabbit.setMovementSpeed(0.0D);
            } else if (this.isUpdating()) {
                this.rabbit.setMovementSpeed(this.nextJumpSpeed);
            }

            super.tick();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn) {
            if (this.rabbit.isInWater()) {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }

    static class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        private final GummyBunnyEntity rabbit;

        public PanicGoal(GummyBunnyEntity rabbit, double speedIn) {
            super(rabbit, speedIn);
            this.rabbit = rabbit;
        }

        public void tick() {
            super.tick();
            this.rabbit.setMovementSpeed(this.speed);
        }
    }
}
