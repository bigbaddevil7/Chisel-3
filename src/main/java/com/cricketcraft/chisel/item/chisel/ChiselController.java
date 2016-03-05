package com.cricketcraft.chisel.item.chisel;

import com.cricketcraft.chisel.api.CarvingRegistry;
import com.cricketcraft.chisel.api.ChiselRecipe;
import com.cricketcraft.chisel.api.IChiselItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class ChiselController {
    public static final ChiselController INSTANCE = new ChiselController();

    private long lastTickClick = 0;

    private ChiselController() {

    }

    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack held = player.getCurrentEquippedItem();
        int slot = player.inventory.currentItem;

        if (held == null || !(held.getItem() instanceof IChiselItem)) {
            return;
        }

        IChiselItem chisel = (IChiselItem) held.getItem();


        if(event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            if(player.capabilities.isCreativeMode) {
                event.setCanceled(true);
            }

            IBlockState state = event.world.getBlockState(event.pos);
            Block block = state.getBlock();
            int meta = block.getMetaFromState(state);

            ItemStack stack = new ItemStack(block, 1, meta);

            chiselBlockInWorld(CarvingRegistry.getRecipeFromItemStack(stack), stack, event.pos, event.world, player.isSneaking());
        }
    }

    /**
     * @param recipe
     * @param stack The block that is being chiseled in the world.
     */
    private void chiselBlockInWorld(ChiselRecipe recipe, ItemStack stack, BlockPos pos, World world, boolean isShifting) {
        int var = 0;
        for(int c = 0; c < recipe.group.length; c++) {
            if(recipe.group[c].getIsItemStackEqual(stack)) {
                var = c;
            }
        }
        ItemStack out;
        if(isShifting) {
            if(var - 1 < 0)
                var = recipe.group.length;
            out = recipe.group[var - 1];
        } else {
            out = recipe.group[var + 1 > recipe.group.length - 1 ? 0 : var + 1];
        }

        Block block = Block.getBlockFromItem(out.getItem());
        IBlockState state = block.getStateFromMeta(out.getItemDamage());

        if(block.getMaterial() == Material.wood)
            world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "chisel:chisel.wood", 1, 1);
        else
            world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "chisel:chisel.fallback", 1, 1);

        world.setBlockState(pos, state);
    }
}
