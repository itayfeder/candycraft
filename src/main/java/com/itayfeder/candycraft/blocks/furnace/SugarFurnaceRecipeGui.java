package com.itayfeder.candycraft.blocks.furnace;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.item.Item;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class SugarFurnaceRecipeGui extends AbstractRecipeBookGui {
    private static final ITextComponent field_243409_i = new TranslationTextComponent("gui.recipebook.toggleRecipes.blastable");

    protected ITextComponent func_230479_g_() {
        return field_243409_i;
    }

    protected Set<Item> func_212958_h() {
        return SugarFurnaceTileEntity.BurningTimes.keySet();
    }
}