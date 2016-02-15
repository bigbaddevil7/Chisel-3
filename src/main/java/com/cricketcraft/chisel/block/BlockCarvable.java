package com.cricketcraft.chisel.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockCarvable extends Block {
    public BlockCarvable(Material material, MapColor mapColor) {
        super(material, mapColor);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    public BlockCarvable(Material material) {
        super(material);
        setHardness(2.0F);
        setResistance(5.0F);
    }
}
