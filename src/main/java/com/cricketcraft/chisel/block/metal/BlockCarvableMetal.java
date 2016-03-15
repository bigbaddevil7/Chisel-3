package com.cricketcraft.chisel.block.metal;

import com.cricketcraft.chisel.block.BlockCarvable;
import com.cricketcraft.chisel.init.ChiselTabs;
import net.minecraft.block.material.Material;

public class BlockCarvableMetal extends BlockCarvable {

    public BlockCarvableMetal() {
        super(Material.iron);
        //setStepSound(ChiselSound.soundTypeChiselMetal);
        setCreativeTab(ChiselTabs.tabMetalChiselBlocks);
    }
}
