package com.itayfeder.candycraft.client.render;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.client.model.GummyBunnyModel;
import com.itayfeder.candycraft.client.render.layer.GummyBunnyJelloLayer;
import com.itayfeder.candycraft.entities.GummyBunnyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GummyBunnyRenderer extends MobRenderer<GummyBunnyEntity, GummyBunnyModel<GummyBunnyEntity>> {
    private static final ResourceLocation BASE = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/gummy_bunny/base.png");

    public GummyBunnyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GummyBunnyModel<>(), 0.6F);
        this.addLayer(new GummyBunnyJelloLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GummyBunnyEntity entity) {
        return BASE;

    }
}
