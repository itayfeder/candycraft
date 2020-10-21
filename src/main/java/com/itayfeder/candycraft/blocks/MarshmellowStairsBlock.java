package com.itayfeder.candycraft.blocks;

import com.itayfeder.candycraft.init.ModBlocks;
import com.itayfeder.candycraft.util.RegistryHandler;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class MarshmellowStairsBlock extends StairsBlock {
    public MarshmellowStairsBlock() {
        super(ModBlocks.MARSHMELLOW_PLANKS.get().getDefaultState(),
                AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(2f, 3f));
    }
}
