package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class ChocolateCobblestoneBlock extends Block {
    public ChocolateCobblestoneBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BROWN)
                .sound(SoundType.STONE)
                .hardnessAndResistance(6f, 2f));
    }
}
