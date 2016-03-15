package com.cricketcraft.chisel.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }
}
