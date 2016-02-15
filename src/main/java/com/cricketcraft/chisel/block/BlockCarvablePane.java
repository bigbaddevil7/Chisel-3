package com.cricketcraft.chisel.block;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockCarvablePane extends BlockPane {
    protected BlockCarvablePane(Material material) {
        super(material, true);
        setHardness(2.0F);
        setResistance(5.0F);
    }
}
