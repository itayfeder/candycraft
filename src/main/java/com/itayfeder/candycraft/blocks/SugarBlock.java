package com.itayfeder.candycraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class SugarBlock extends Block {
    public SugarBlock() {
        super(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.QUARTZ)
                .sound(SoundType.SAND)
                .hardnessAndResistance(1.5f, 6f)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE));
    }
}
