package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FlourBlock extends Block {
    public FlourBlock() {
        super(AbstractBlock.Properties.create(Material.EARTH)
                .sound(SoundType.GROUND)
                .hardnessAndResistance(0.5f, 0.5f));
    }
}
