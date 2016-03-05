package com.cricketcraft.chisel.block.other;

import com.cricketcraft.chisel.block.BlockCarvable;
import com.cricketcraft.chisel.init.ChiselProperties;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.cricketcraft.chisel.util.BlockVariant;
import com.cricketcraft.chisel.util.IChiselBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ChiselBlockCarvableChest extends BlockCarvable implements IChiselBlock {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public ChiselBlockCarvableChest() {
        super(Material.wood);
        setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        setCreativeTab(ChiselTabs.tabOtherChiselBlocks);
        setDefaultState(this.getBlockState().getBaseState().withProperty(ChiselProperties.CHEST_VARIANTS, ChiselProperties.CHEST_VARIANTS.fromMeta(0)));
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 2;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos) {
        if(world.getBlockState(pos.north()).getBlock() == this) {
            setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        } else if(world.getBlockState(pos.south()).getBlock() == this) {
            setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        } else if(world.getBlockState(pos.west()).getBlock() == this) {
            setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        } else if(world.getBlockState(pos.east()).getBlock() == this) {
            setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        } else {
            setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        checkForSurroundingPresents(world, pos, state);

        for(EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
            BlockPos pos1 = pos.offset(facing);
            IBlockState state1 = world.getBlockState(pos1);

            if(state1.getBlock() == state) {
                checkForSurroundingPresents(world, pos1, state1);
            }
        }
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing facing = EnumFacing.getHorizontal(MathHelper.floor_double((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, facing);
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockPos eastPos = pos.east();
        boolean northFlag = state == world.getBlockState(northPos);
        boolean southFlag = state == world.getBlockState(southPos);
        boolean westFlag = state == world.getBlockState(westPos);


    }

    public IBlockState checkForSurroundingPresents(World world, BlockPos pos, IBlockState state) {
        if(world.isRemote) {
            return state;
        } else {
            IBlockState northState = world.getBlockState(pos.north());
            IBlockState southState = world.getBlockState(pos.south());
            IBlockState westState = world.getBlockState(pos.west());
            IBlockState eastState = world.getBlockState(pos.east());
            EnumFacing facing = state.getValue(FACING);
            Block northBlock = northState.getBlock();
            Block southBlock = southState.getBlock();
            Block westBlock = westState.getBlock();
            Block eastBlock = eastState.getBlock();

            if(northState != state && southState != state) {
                boolean northIsFull = northBlock.isFullBlock();
                boolean southIsFull = southBlock.isFullBlock();

                if(westState == state || eastState == state) {
                    BlockPos pos1 = westBlock == state.getBlock() ? pos.west() : pos.east();
                    IBlockState northState1 = world.getBlockState(pos1.north());
                    IBlockState southState1 = world.getBlockState(pos1.south());
                    facing = EnumFacing.SOUTH;
                    EnumFacing facing1;

                    if(westState == state) {
                        facing1 = westState.getValue(FACING);
                    } else {
                        facing1 = eastState.getValue(FACING);
                    }

                    if(facing1 == EnumFacing.NORTH) {
                        facing = EnumFacing.NORTH;
                    }

                    Block northBlock1 = northState1.getBlock();
                    Block southBlock1 = southState1.getBlock();

                    if((northIsFull || northBlock1.isFullBlock()) && !southIsFull && !southBlock1.isFullBlock()) {
                        facing = EnumFacing.SOUTH;
                    }

                    if((southIsFull || southBlock1.isFullBlock()) && !northIsFull && !northBlock1.isFullBlock()) {
                        facing = EnumFacing.NORTH;
                    }
                }
            } else {
                BlockPos pos1 = northBlock == this ? pos.north() : pos.south();
                IBlockState westState1 = world.getBlockState(pos1.west());
                IBlockState eastState1 = world.getBlockState(pos1.east());
                facing = EnumFacing.EAST;
                EnumFacing facing1;

                if(northState == state) {
                    facing1 = northState.getValue(FACING);
                } else {
                    facing1 = southState.getValue(FACING);
                }

                if(facing1 == EnumFacing.WEST) {
                    facing = EnumFacing.WEST;
                }

                Block westBlock1 = westState1.getBlock();
                Block eastBlock1 = eastState1.getBlock();

                if((westBlock.isFullBlock() || westBlock1.isFullBlock()) && !eastBlock.isFullBlock() && !eastBlock1.isFullBlock()) {
                    facing = EnumFacing.EAST;
                }

                if((eastBlock.isFullBlock() || eastBlock1.isFullBlock()) && !westBlock.isFullBlock() && westBlock1.isFullBlock()) {
                    facing = EnumFacing.WEST;
                }
            }

            state = state.withProperty(FACING, facing).withProperty(ChiselProperties.CHEST_VARIANTS, ChiselProperties.CHEST_VARIANTS.fromMeta(state.getBlock().getMetaFromState(state)));
            world.setBlockState(pos, state, 3);
            return state;
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (BlockVariant variant : ChiselProperties.CHEST_VARIANTS.getAllowedValues()) {
            list.add(new ItemStack(itemIn, 1, variant.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ChiselProperties.CHEST_VARIANTS, ChiselProperties.CHEST_VARIANTS.fromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockVariant) state.getValue(ChiselProperties.CHEST_VARIANTS)).getMeta();
    }

    @Override
    public String getSubtypeUnlocalizedName(ItemStack stack) {
        return ChiselProperties.CHEST_VARIANTS.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, ChiselProperties.CHEST_VARIANTS);
    }
}
