package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DarkMarshmellowLogBlock extends RotatedPillarBlock {
    public DarkMarshmellowLogBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(3f, 3f));
    }
}
