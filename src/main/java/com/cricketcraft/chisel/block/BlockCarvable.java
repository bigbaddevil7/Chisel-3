package com.cricketcraft.chisel.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }
}
