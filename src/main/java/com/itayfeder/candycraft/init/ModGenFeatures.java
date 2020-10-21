package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.world.gen.features.MintFeature;
import com.itayfeder.candycraft.world.gen.features.RaspberryChainFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModGenFeatures {
    public static DeferredRegister<Feature<?>> GEN_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, CandyCraft.MOD_ID);

    public static final RegistryObject<RaspberryChainFeature> RASPBERRY_CHAIN_FEATURE = GEN_FEATURES.register("raspberry_chain", () -> new RaspberryChainFeature(ProbabilityConfig.field_236576_b_));
    public static final RegistryObject<MintFeature> MINT_FEATURE = GEN_FEATURES.register("mint", () -> new MintFeature(ProbabilityConfig.field_236576_b_));

}
