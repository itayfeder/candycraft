package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.blocks.furnace.SugarFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CandyCraft.MOD_ID);

    public static final RegistryObject<TileEntityType<SugarFurnaceTileEntity>> SUGAR_FURNACE = TILE_ENTITY_TYPES.register("sugar_furnace",
            () -> TileEntityType.Builder.create(SugarFurnaceTileEntity::new, ModBlocks.SUGAR_FURNACE.get()).build(null));
}
