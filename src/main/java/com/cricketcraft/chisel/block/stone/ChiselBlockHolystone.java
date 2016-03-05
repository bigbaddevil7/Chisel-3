package com.cricketcraft.chisel.block.stone;

import com.cricketcraft.chisel.Chisel;
import com.cricketcraft.chisel.block.BlockCarvable;
import com.cricketcraft.chisel.entity.fx.EntityHolystoneFX;
import com.cricketcraft.chisel.init.ChiselProperties;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.cricketcraft.chisel.util.BlockVariant;
import com.cricketcraft.chisel.util.IChiselBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ChiselBlockHolystone extends BlockCarvable implements IChiselBlock {

    public ChiselBlockHolystone() {
        super(Material.rock);
        setCreativeTab(ChiselTabs.tabStoneChiselBlocks);
        setStepSound(Chisel.soundTypeHolyStone);
        setDefaultState(this.getBlockState().getBaseState().withProperty(ChiselProperties.HOLYSTONE_VARIANTS, ChiselProperties.HOLYSTONE_VARIANTS.fromMeta(0)));
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (BlockVariant variant : ChiselProperties.HOLYSTONE_VARIANTS.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, variant.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ChiselProperties.HOLYSTONE_VARIANTS, ChiselProperties.HOLYSTONE_VARIANTS.fromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockVariant) state.getValue(ChiselProperties.HOLYSTONE_VARIANTS)).getMeta();
    }

    @Override
    public String getSubtypeUnlocalizedName(ItemStack stack) {
        return ChiselProperties.HOLYSTONE_VARIANTS.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, ChiselProperties.HOLYSTONE_VARIANTS);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (Minecraft.getMinecraft().gameSettings.particleSetting != 0)
            return;

        Block block = state.getBlock();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        float f = 0.15F;
        double x1 = x + rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - f * 2.0F) + f + block.getBlockBoundsMinX();
        double y1 = y + rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - f * 2.0F) + f + block.getBlockBoundsMinY();
        double z1 = z + rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - f * 2.0F) + f + block.getBlockBoundsMinZ();

        switch (rand.nextInt(6)) {
            case 0:
                y1 = y + block.getBlockBoundsMinY() - f;
                y--;
                break;
            case 1:
                y1 = y + block.getBlockBoundsMaxY() + f;
                y++;
                break;
            case 2:
                z1 = z + block.getBlockBoundsMinZ() - f;
                z--;
                break;
            case 3:
                z1 = z + block.getBlockBoundsMaxZ() + f;
                z++;
                break;
            case 4:
                x1 = x + block.getBlockBoundsMinX() - f;
                x--;
                break;
            case 5:
                x1 = x + block.getBlockBoundsMaxX() + f;
                x++;
                break;
        }

        if (world.getBlockState(pos).getBlock().isOpaqueCube())
            return;

        EntityHolystoneFX res = new EntityHolystoneFX(world, this, x1, y1, z1);
        Minecraft.getMinecraft().effectRenderer.addEffect(res);
    }
}
