package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DarkMarshmellowFenceBlock extends FenceBlock {
    public DarkMarshmellowFenceBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(3f, 4f));
    }
}
