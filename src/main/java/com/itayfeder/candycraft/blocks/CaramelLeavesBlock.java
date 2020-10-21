package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class CaramelLeavesBlock extends LeavesBlock {
    public CaramelLeavesBlock() {
        super(AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.BROWN)
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2f, 0.2f)
                .notSolid()
                .tickRandomly());
    }
}
