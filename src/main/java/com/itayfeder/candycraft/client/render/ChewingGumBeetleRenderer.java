package com.itayfeder.candycraft.client.render;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.client.model.ChewingGumBeetleModel;
import com.itayfeder.candycraft.entities.ChewingGumBeetleEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChewingGumBeetleRenderer extends MobRenderer<ChewingGumBeetleEntity, ChewingGumBeetleModel<ChewingGumBeetleEntity>> {
    private static final ResourceLocation DEFAULT = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/beetle/default.png");
    private static final ResourceLocation RAGE = new ResourceLocation(CandyCraft.MOD_ID, "textures/entity/beetle/rage.png");

    public ChewingGumBeetleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ChewingGumBeetleModel<>(), 1F);
    }

    @Override
    public ResourceLocation getEntityTexture(ChewingGumBeetleEntity entity) {
        return !entity.getAngry() ? DEFAULT : RAGE;
    }
}
