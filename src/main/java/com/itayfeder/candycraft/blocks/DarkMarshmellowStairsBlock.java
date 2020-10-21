package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.init.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DarkMarshmellowStairsBlock extends StairsBlock {
    public DarkMarshmellowStairsBlock() {
        super(ModBlocks.MARSHMELLOW_PLANKS.get().getDefaultState(),
                AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK)
                        .sound(SoundType.WOOD)
                        .hardnessAndResistance(3f, 4f));
    }
}
