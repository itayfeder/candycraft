package com.itayfeder.candycraft.init;

import com.itayfeder.candycraft.CandyCraft;
import com.itayfeder.candycraft.Items.BlockItemBase;
import com.itayfeder.candycraft.Items.ModSpawnEggItem;
import com.itayfeder.candycraft.util.enums.ModItemTiers;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CandyCraft.MOD_ID);

    // Block Items
    public static final RegistryObject<Item> FLOUR_BLOCK_ITEM = ITEMS.register("flour_block", () -> new BlockItemBase(ModBlocks.FLOUR_BLOCK.get()));
    public static final RegistryObject<Item> PUDDING_ITEM = ITEMS.register("pudding", () -> new BlockItemBase(ModBlocks.PUDDING.get()));
    public static final RegistryObject<Item> SWEET_GRASS_ITEM = ITEMS.register("sweet_grass", () -> new BlockItemBase(ModBlocks.SWEET_GRASS.get()));
    public static final RegistryObject<Item> CHOCOLATE_COBBLESTONE_ITEM = ITEMS.register("chocolate_cobblestone", () -> new BlockItemBase(ModBlocks.CHOCOLATE_COBBLESTONE.get()));
    public static final RegistryObject<Item> SUGAR_BLOCK_ITEM = ITEMS.register("sugar_block", () -> new BlockItemBase(ModBlocks.SUGAR_BLOCK.get()));
    public static final RegistryObject<Item> CARAMEL_LEAVES_ITEM = ITEMS.register("caramel_leaves", () -> new BlockItemBase(ModBlocks.CARAMEL_LEAVES.get()));
    public static final RegistryObject<Item> CHOCOLATE_LEAVES_ITEM = ITEMS.register("chocolate_leaves", () -> new BlockItemBase(ModBlocks.CHOCOLATE_LEAVES.get()));
    public static final RegistryObject<Item> MARSHMELLOW_LOG_ITEM = ITEMS.register("marshmellow_log", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_LOG.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_LOG_ITEM = ITEMS.register("dark_marshmellow_log", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_LOG.get()));
    public static final RegistryObject<Item> MARSHMELLOW_PLANKS_ITEM = ITEMS.register("marshmellow_planks", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_PLANKS.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_PLANKS_ITEM = ITEMS.register("dark_marshmellow_planks", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_PLANKS.get()));
    public static final RegistryObject<Item> MARSHMELLOW_SLAB_ITEM = ITEMS.register("marshmellow_slab", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_SLAB.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_SLAB_ITEM = ITEMS.register("dark_marshmellow_slab", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_SLAB.get()));
    public static final RegistryObject<Item> MARSHMELLOW_STAIRS_ITEM = ITEMS.register("marshmellow_stairs", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_STAIRS.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_STAIRS_ITEM = ITEMS.register("dark_marshmellow_stairs", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_STAIRS.get()));
    public static final RegistryObject<Item> MARSHMELLOW_FENCE_ITEM = ITEMS.register("marshmellow_fence", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_FENCE.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_FENCE_ITEM = ITEMS.register("dark_marshmellow_fence", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_FENCE.get()));
    public static final RegistryObject<Item> MARSHMELLOW_FENCEGATE_ITEM = ITEMS.register("marshmellow_fence_gate", () -> new BlockItemBase(ModBlocks.MARSHMELLOW_FENCEGATE.get()));
    public static final RegistryObject<Item> DARK_MARSHMELLOW_FENCEGATE_ITEM = ITEMS.register("dark_marshmellow_fence_gate", () -> new BlockItemBase(ModBlocks.DARK_MARSHMELLOW_FENCEGATE.get()));
    public static final RegistryObject<Item> LICORICE_ORE_ITEM = ITEMS.register("licorice_ore", () -> new BlockItemBase(ModBlocks.LICORICE_ORE.get()));
    public static final RegistryObject<Item> NOUGAT_ORE_ITEM = ITEMS.register("nougat_ore", () -> new BlockItemBase(ModBlocks.NOUGAT_ORE.get()));
    public static final RegistryObject<Item> NOUGAT_BLOCK_ITEM = ITEMS.register("nougat_block", () -> new BlockItemBase(ModBlocks.NOUGAT_BLOCK.get()));
    public static final RegistryObject<Item> NOUGAT_HEAD_ITEM = ITEMS.register("nougat_head", () -> new BlockItemBase(ModBlocks.NOUGAT_HEAD.get()));
    public static final RegistryObject<Item> MINT_PLANT_ITEM = ITEMS.register("mint", () -> new BlockItemBase(ModBlocks.MINT_PLANT.get()));
    public static final RegistryObject<Item> RASPBERRY_CHAIN_ITEM = ITEMS.register("raspberry_chain", () -> new BlockItemBase(ModBlocks.RASPBERRY_CHAIN.get()));
    public static final RegistryObject<Item> SUGAR_FURNACE_ITEM = ITEMS.register("sugar_furnace", () -> new BlockItemBase(ModBlocks.SUGAR_FURNACE.get()));

    // Items
    public static final RegistryObject<Item> LICORICE = ITEMS.register("licorice", () -> new Item(new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<Item> CRANFISH_ITEM = ITEMS.register("cranfish_item", () -> new Item(new Item.Properties().group(CandyCraft.TAB).food((new Food.Builder()).hunger(3).saturation(0.15f).build())));

    // Tools
    public static final RegistryObject<Item> LICORICE_SWORD = ITEMS.register("licorice_sword", () ->
            new SwordItem(ModItemTiers.LICORICE, 3, -2.4f, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<PickaxeItem> LICORICE_PICKAXE = ITEMS.register("licorice_pickaxe",
            () -> new PickaxeItem(ModItemTiers.LICORICE, 0, -2.8F, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<ShovelItem> LICORICE_SHOVEL = ITEMS.register("licorice_shovel",
            () -> new ShovelItem(ModItemTiers.LICORICE, 0.5F, -3.0F, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<AxeItem> LICORICE_AXE = ITEMS.register("licorice_axe",
            () -> new AxeItem(ModItemTiers.LICORICE, 5, -3F, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<HoeItem> LICORICE_HOE = ITEMS.register("licorice_hoe",
            () -> new HoeItem(ModItemTiers.LICORICE,0, -1.0F, new Item.Properties().group(CandyCraft.TAB)));


    // Spawn Eggs
    public static final RegistryObject<ModSpawnEggItem> GUMMY_BUNNY_SPAWN_EGG = ITEMS.register("gummy_bunny_spawn_egg",
            () -> new ModSpawnEggItem(ModEntities.GUMMY_BUNNY, -10828465, -16757760, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<ModSpawnEggItem> CHEWING_GUM_BEETLE_SPAWN_EGG = ITEMS.register("chewing_gum_beetle_spawn_egg",
            () -> new ModSpawnEggItem(ModEntities.CHEWING_GUM_BEETLE,  -12899229, -6717361, new Item.Properties().group(CandyCraft.TAB)));
    public static final RegistryObject<ModSpawnEggItem> CRANFISH_SPAWN_EGG = ITEMS.register("cranfish_spawn_egg",
            () -> new ModSpawnEggItem(ModEntities.CRANFISH,  -4294520, -1782884, new Item.Properties().group(CandyCraft.TAB)));
    //
}
