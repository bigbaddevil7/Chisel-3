package com.cricketcraft.chisel.block.other;

import com.cricketcraft.chisel.block.BlockCarvablePane;
import com.cricketcraft.chisel.init.ChiselProperties;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.cricketcraft.chisel.util.BlockVariant;
import com.cricketcraft.chisel.util.IChiselBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ChiselBlockGlassPane extends BlockCarvablePane implements IChiselBlock {

    public ChiselBlockGlassPane() {
        super(Material.glass);
        setCreativeTab(ChiselTabs.tabOtherChiselBlocks);
        setDefaultState(getBlockState().getBaseState().withProperty(ChiselProperties.GLASS_PANE_VARIANTS, ChiselProperties.GLASS_PANE_VARIANTS.fromMeta(0))
                .withProperty(NORTH, false).withProperty(SOUTH, false)
                .withProperty(EAST, false).withProperty(WEST, false));
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (BlockVariant variant : ChiselProperties.GLASS_PANE_VARIANTS.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, variant.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ChiselProperties.GLASS_PANE_VARIANTS, ChiselProperties.GLASS_PANE_VARIANTS.fromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockVariant) state.getValue(ChiselProperties.GLASS_PANE_VARIANTS)).getMeta();
    }

    @Override
    public String getSubtypeUnlocalizedName(ItemStack stack) {
        return ChiselProperties.GLASS_PANE_VARIANTS.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, ChiselProperties.GLASS_PANE_VARIANTS, NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(ChiselProperties.GLASS_PANE_VARIANTS, ChiselProperties.GLASS_PANE_VARIANTS.fromMeta(world.getBlockState(pos).getBlock().getMetaFromState(state)))
                .withProperty(NORTH, canPaneConnectTo(world, pos, EnumFacing.NORTH))
                .withProperty(SOUTH, canPaneConnectTo(world, pos, EnumFacing.SOUTH))
                .withProperty(EAST, canPaneConnectTo(world, pos, EnumFacing.EAST))
                .withProperty(WEST, canPaneConnectTo(world, pos, EnumFacing.WEST));
    }
}
