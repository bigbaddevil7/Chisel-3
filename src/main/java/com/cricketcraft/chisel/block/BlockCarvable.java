package com.cricketcraft.chisel.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockCarvable extends Block {
    public BlockCarvable(Material material, MapColor mapColor) {
        super(material, mapColor);
    }

    public BlockCarvable(Material material) {
        super(material);
    }
}
