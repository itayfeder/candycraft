package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.entities.ChewingGumBeetleEntity;
import com.itayfeder.candycraft.entities.CranfishEntity;
import com.itayfeder.candycraft.entities.GummyBunnyEntity;
import com.itayfeder.candycraft.entities.NougatGolemEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CandyCraft.MOD_ID);

    // Entity Types
    public static final RegistryObject<EntityType<GummyBunnyEntity>> GUMMY_BUNNY = ENTITY_TYPES.register("gummy_bunny",
            () -> EntityType.Builder.create(GummyBunnyEntity::new, EntityClassification.CREATURE)
                    .size(0.5f, 0.5f)
                    .build(new ResourceLocation(CandyCraft.MOD_ID, "bummy_bunny").toString()));
    public static final RegistryObject<EntityType<ChewingGumBeetleEntity>> CHEWING_GUM_BEETLE = ENTITY_TYPES.register("chewing_gum_beetle",
            () -> EntityType.Builder.create(ChewingGumBeetleEntity::new, EntityClassification.MONSTER)
                    .size(0.8f, 0.8f)
                    .build(new ResourceLocation(CandyCraft.MOD_ID, "chewing_gum_beetle").toString()));
    public static final RegistryObject<EntityType<NougatGolemEntity>> NOUGAT_GOLEM = ENTITY_TYPES.register("nougat_golem",
            () -> EntityType.Builder.create(NougatGolemEntity::new, EntityClassification.MONSTER)
                    .size(1f, 1f)
                    .build(new ResourceLocation(CandyCraft.MOD_ID, "nougat_golem").toString()));
    public static final RegistryObject<EntityType<CranfishEntity>> CRANFISH = ENTITY_TYPES.register("cranfish",
            () -> EntityType.Builder.create(CranfishEntity::new, EntityClassification.WATER_AMBIENT)
                    .size(0.5f, 0.3f)
                    .build(new ResourceLocation(CandyCraft.MOD_ID, "cranfish").toString()));
    //
}
