package com.itayfeder.candycraft.client.render;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.client.model.NougatGolemModel;
import com.itayfeder.candycraft.entities.NougatGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NougatGolemRenderer extends MobRenderer<NougatGolemEntity, NougatGolemModel<NougatGolemEntity>> {
    private static final ResourceLocation HEAD = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/nougat_golem/head.png");
    private static final ResourceLocation BODY = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/nougat_golem/body.png");


    public NougatGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NougatGolemModel<>(), 0.6F);
    }

    @Override
    protected void preRenderCallback(NougatGolemEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        if (entitylivingbaseIn.isTop() || entitylivingbaseIn.isBase()) {
            matrixStackIn.scale(0.8F, 0.8F, 0.8F);
        }
        else {
            matrixStackIn.scale(0.64F, 0.64F, 0.64F);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(NougatGolemEntity entitylivingbaseIn) {
        if (entitylivingbaseIn.isTop()) {
            return HEAD;
        }
        else {
            return BODY;
        }
    }

}