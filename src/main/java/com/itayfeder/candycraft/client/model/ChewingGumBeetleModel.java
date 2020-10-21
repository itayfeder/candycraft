package com.itayfeder.candycraft.client.model;

import com.google.common.collect.ImmutableList;
import com.itayfeder.candycraft.entities.ChewingGumBeetleEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ChewingGumBeetleModel<T extends ChewingGumBeetleEntity> extends AgeableModel<T> {
    private final ModelRenderer belly;
    private final ModelRenderer body;
    private final ModelRenderer neck;
    private final ModelRenderer head;
    private final ModelRenderer wings;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer leg5;

    public ChewingGumBeetleModel() {
        textureWidth = 96;
        textureHeight = 96;

        belly = new ModelRenderer(this);
        belly.setRotationPoint(0.0F, 24.0F, 0.0F);
        belly.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, -6.5F, 8.0F, 3.0F, 13.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 16).addBox(-4.0F, -12.3F, -6.5F, 8.0F, 6.0F, 13.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, 24.0F, 0.0F);
        neck.setTextureOffset(0, 35).addBox(-4.0F, -10.5F, -7.5F, 8.0F, 7.0F, 1.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 17.0F, -7.0F);
        head.setTextureOffset(18, 35).addBox(-3.5F, -3.0F, -8.0F, 7.0F, 6.0F, 8.0F, 0.0F, false);

        wings = new ModelRenderer(this);
        wings.setRotationPoint(0.0F, 24.0F, 0.0F);
        wings.setTextureOffset(0, 49).addBox(-4.5F, -12.5F, -7.0F, 9.0F, 7.0F, 14.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(-4.0F, 19.0F, -5.0F);
        leg0.setTextureOffset(42, 0).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-4.0F, 19.0F, 0.0F);
        leg1.setTextureOffset(42, 0).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(-4.0F, 19.0F, 5.0F);
        leg2.setTextureOffset(42, 0).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(4.0F, 19.0F, 5.0F);
        leg3.setTextureOffset(42, 8).addBox(0.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(4.0F, 19.0F, 0.0F);
        leg4.setTextureOffset(42, 8).addBox(0.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        leg5 = new ModelRenderer(this);
        leg5.setRotationPoint(4.0F, 19.0F, -5.0F);
        leg5.setTextureOffset(42, 8).addBox(0.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.head, this.leg0, this.leg1, this.leg2, this.leg3, this.leg4, this.leg5, this.belly, this.body, this.neck, this.wings);
    }

    /*@Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
                       float green, float blue, float alpha) {
        belly.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        wings.render(matrixStack, buffer, packedLight, packedOverlay);
        leg0.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
        leg4.render(matrixStack, buffer, packedLight, packedOverlay);
        leg5.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }*/

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.leg5.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
    }
}
