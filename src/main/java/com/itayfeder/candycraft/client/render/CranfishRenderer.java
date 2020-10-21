package com.itayfeder.candycraft.client.render;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.client.model.CranfishModel;
import com.itayfeder.candycraft.entities.CranfishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CranfishRenderer extends MobRenderer<CranfishEntity, CranfishModel<CranfishEntity>> {
    public CranfishRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CranfishModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(CranfishEntity entity) {
        return new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/cranfish.png");
    }

    @Override
    protected void applyRotations(CranfishEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
