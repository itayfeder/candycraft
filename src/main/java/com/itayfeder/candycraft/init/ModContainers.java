package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.blocks.furnace.SugarFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, CandyCraft.MOD_ID);

    public static final RegistryObject<ContainerType<SugarFurnaceContainer>> SUGAR_FURNACE = CONTAINER_TYPES
            .register("sugar_furnace", () -> IForgeContainerType.create(SugarFurnaceContainer::new));
}
