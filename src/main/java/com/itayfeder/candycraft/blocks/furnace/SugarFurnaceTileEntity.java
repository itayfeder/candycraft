package com.itayfeder.candycraft.blocks.furnace;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.itayfeder.candycraft.init.ModItems;
import com.itayfeder.candycraft.init.ModTileEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.BlastFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Map;

public class SugarFurnaceTileEntity extends AbstractFurnaceTileEntity {
    public SugarFurnaceTileEntity() {
        super(ModTileEntities.SUGAR_FURNACE.get(), IRecipeType.SMELTING);
    }

    public static final Map<Item, Integer> BurningTimes = ImmutableMap.of(
            Items.SUGAR, 600,
            ModItems.SUGAR_BLOCK_ITEM.get(), 6000
    );

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.sugar_furnace");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return new SugarFurnaceContainer(id, player, this, this.furnaceData);
    }

    public static boolean isFuel(ItemStack stack) {
        return findBurnTime(stack) > 0;
    }

    protected static int findBurnTime(ItemStack fuel) {
        if (fuel.isEmpty() || !BurningTimes.containsKey(fuel.getItem())) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return BurningTimes.get(item);
        }
    }

    @Override
    protected int getBurnTime(ItemStack fuel) {
        return findBurnTime(fuel);
    }
}