package com.itayfeder.candycraft.client.model;

import com.google.common.collect.ImmutableList;
import com.itayfeder.candycraft.entities.CranfishEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CranfishModel<T extends CranfishEntity>  extends SegmentedModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer leftFin;
    private final ModelRenderer rightFin;
    private final ModelRenderer tailfin;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;

    public CranfishModel() {
        textureWidth = 32;
        textureHeight = 32;
        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, -3.0F);
        body.setTextureOffset(8, 3).addBox(-1.0F, -5.0F, 1.0F, 2.0F, 5.0F, 6.0F, 0.0F, true);
        body.setTextureOffset(6, 17).addBox(0.0F, -6.0F, 0.0F, 0.0F, 1.0F, 6.0F, 0.0F, true);
        body.setTextureOffset(18, 21).addBox(0.0F, 0.0F, 3.0F, 0.0F, 1.0F, 2.0F, 0.0F, true);
        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 21.5F, -2.0F);
        head.setTextureOffset(0, 0).addBox(-1.0F, -2.5F, -3.0F, 2.0F, 5.0F, 3.0F, 0.0F, true);
        head.setTextureOffset(12, 10).addBox(-1.0F, -0.5F, -4.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        leftFin = new ModelRenderer(this);
        leftFin.setRotationPoint(-1.0F, 23.0F, -3.0F);
        setRotationAngle(leftFin, 0.0F, 0.0F, 0.6109F);
        leftFin.setTextureOffset(24, 14).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
        rightFin = new ModelRenderer(this);
        rightFin.setRotationPoint(1.0F, 23.0F, -3.0F);
        setRotationAngle(rightFin, 0.0F, 0.0F, -0.6109F);
        rightFin.setTextureOffset(24, 17).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
        tailfin = new ModelRenderer(this);
        tailfin.setRotationPoint(0.0F, 24.0F, 4.0F);
        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.5F, -2.0F, 0.0F);
        tailfin.addChild(bone);
        setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
        bone.setTextureOffset(9, 15).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.5F, -2.0F, 0.0F);
        tailfin.addChild(bone2);
        setRotationAngle(bone2, -0.5236F, 0.0F, 0.0F);
        bone2.setTextureOffset(9, 16).addBox(-1.0F, -0.634F, -0.366F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.body, this.head, this.leftFin, this.rightFin, this.tailfin, this.bone, this.bone2);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                       float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftFin.render(matrixStack, buffer, packedLight, packedOverlay);
        rightFin.render(matrixStack, buffer, packedLight, packedOverlay);
        tailfin.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(T e, float f, float f1, float f2, float f3, float f4) {
        float a = 1.0F;
        if (!e.isInWater()) {
            a = 1.5F;
        }
        this.tailfin.rotateAngleY = -a * 0.45F * MathHelper.sin(0.6F * f2);
    }
}
