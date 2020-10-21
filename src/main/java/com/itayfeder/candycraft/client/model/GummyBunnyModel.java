package com.itayfeder.candycraft.client.model;

import com.itayfeder.candycraft.entities.GummyBunnyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GummyBunnyModel<T extends GummyBunnyEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer bone;
    private final ModelRenderer ear0;
    private final ModelRenderer ear1;

    public GummyBunnyModel() {
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-3.0F, -8.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);
        body.setTextureOffset(16, 1).addBox(-2.0F, -7.0F, 4.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(2.0F, 22.0F, 4.0F);
        leg0.setTextureOffset(20, 8).addBox(-1.0F, -0.01F, -3.0F, 2.0F, 1.99F, 3.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-2.0F, 22.0F, 4.0F);
        leg1.setTextureOffset(20, 8).addBox(-1.0F, -0.01F, -3.0F, 2.0F, 1.99F, 3.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(2.0F, 22.0F, -1.0F);
        leg2.setTextureOffset(21, 9).addBox(-1.0F, -0.01F, -2.0F, 2.0F, 1.99F, 2.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(-2.0F, 22.0F, -1.0F);
        leg3.setTextureOffset(21, 9).addBox(-1.0F, -0.01F, -2.0F, 2.0F, 1.99F, 2.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 19.0F, -4.0F);
        bone.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        bone.setTextureOffset(0, 8).addBox(-3.5F, -1.0F, -3.0F, 7.0F, 3.0F, 0.0F, 0.0F, false);

        ear0 = new ModelRenderer(this);
        ear0.setRotationPoint(2.0F, -2.0F, -2.0F);
        bone.addChild(ear0);
        setRotationAngle(ear0, 0.0F, 0.0F, 0.0873F);
        ear0.setTextureOffset(0, 11).addBox(-1.0F, -2.9F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        ear1 = new ModelRenderer(this);
        ear1.setRotationPoint(-2.0F, -2.0F, -2.0F);
        bone.addChild(ear1);
        setRotationAngle(ear1, 0.0F, 0.0F, -0.0873F);
        ear1.setTextureOffset(0, 11).addBox(0.0F, -2.9F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
                       float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leg0.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.bone.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.bone.rotateAngleX = headPitch / (180F / (float) Math.PI);
    }
}
