package com.itayfeder.candycraft.client.render.layer;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.client.model.GummyBunnyModel;
import com.itayfeder.candycraft.entities.GummyBunnyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GummyBunnyJelloLayer<T extends GummyBunnyEntity> extends LayerRenderer<T, GummyBunnyModel<T>> {
    private final EntityModel<T> model = new GummyBunnyModel<>();

    private static final ResourceLocation GREEN = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/green.png");
    private static final ResourceLocation RED = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/red.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/yellow.png");
    private static final ResourceLocation BLUE = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/blue.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/orange.png");
    private static final ResourceLocation PINK = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/pink.png");

    public GummyBunnyJelloLayer(IEntityRenderer<T, GummyBunnyModel<T>> p_i50923_1_) {
        super(p_i50923_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible()) {
            this.getEntityModel().copyModelAttributesTo(this.model);
            this.model.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(getTexture(entitylivingbaseIn)));
            this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public ResourceLocation getTexture(GummyBunnyEntity entity) {
        switch(entity.getRabbitType()) {
            case 0:
            default:
                return GREEN;
            case 1:
                return RED;
            case 2:
                return YELLOW;
            case 3:
                return BLUE;
            case 4:
                return ORANGE;
            case 5:
                return PINK;
        }
    }
}