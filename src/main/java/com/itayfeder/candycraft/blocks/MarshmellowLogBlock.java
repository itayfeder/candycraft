package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class MarshmellowLogBlock extends RotatedPillarBlock {
    public MarshmellowLogBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(2f, 2f));
    }
}
