package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.blocks.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CandyCraft.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> FLOUR_BLOCK = BLOCKS.register("flour_block", FlourBlock::new);
    public static final RegistryObject<Block> PUDDING = BLOCKS.register("pudding", PuddingBlock::new);
    public static final RegistryObject<Block> SWEET_GRASS = BLOCKS.register("sweet_grass", SweetGrassBlock::new);
    public static final RegistryObject<Block> CHOCOLATE_COBBLESTONE = BLOCKS.register("chocolate_cobblestone", ChocolateCobblestoneBlock::new);
    public static final RegistryObject<Block> CARAMEL_LEAVES = BLOCKS.register("caramel_leaves", CaramelLeavesBlock::new);
    public static final RegistryObject<Block> CHOCOLATE_LEAVES = BLOCKS.register("chocolate_leaves", ChocolateLeavesBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_LOG = BLOCKS.register("marshmellow_log", MarshmellowLogBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_LOG = BLOCKS.register("dark_marshmellow_log", DarkMarshmellowLogBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_PLANKS = BLOCKS.register("marshmellow_planks", MarshmellowPlanksBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_PLANKS = BLOCKS.register("dark_marshmellow_planks", DarkMarshmellowPlanksBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_SLAB = BLOCKS.register("marshmellow_slab", MarshmellowSlabBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_SLAB = BLOCKS.register("dark_marshmellow_slab", DarkMarshmellowSlabBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_STAIRS = BLOCKS.register("marshmellow_stairs", MarshmellowStairsBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_STAIRS = BLOCKS.register("dark_marshmellow_stairs", DarkMarshmellowStairsBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_FENCE = BLOCKS.register("marshmellow_fence", MarshmellowFenceBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_FENCE = BLOCKS.register("dark_marshmellow_fence", DarkMarshmellowFenceBlock::new);
    public static final RegistryObject<Block> MARSHMELLOW_FENCEGATE = BLOCKS.register("marshmellow_fence_gate", MarshmellowFenceGateBlock::new);
    public static final RegistryObject<Block> DARK_MARSHMELLOW_FENCEGATE = BLOCKS.register("dark_marshmellow_fence_gate", DarkMarshmellowFenceGateBlock::new);
    public static final RegistryObject<Block> LICORICE_ORE = BLOCKS.register("licorice_ore", LicoriceOreBlock::new);
    public static final RegistryObject<Block> NOUGAT_ORE = BLOCKS.register("nougat_ore", NougatOreBlock::new);
    public static final RegistryObject<Block> NOUGAT_BLOCK = BLOCKS.register("nougat_block", NougatBlock::new);
    public static final RegistryObject<Block> NOUGAT_HEAD = BLOCKS.register("nougat_head", NougatHeadBlock::new);
    public static final RegistryObject<Block> MINT_PLANT = BLOCKS.register("mint", MintPlantBlock::new);
    public static final RegistryObject<Block> RASPBERRY_CHAIN = BLOCKS.register("raspberry_chain", RaspberryChainBlock::new);
    public static final RegistryObject<Block> TALL_RASPBERRY_CHAIN = BLOCKS.register("tall_raspberry_chain", TallRaspberryChainBlock::new);

    //
}
