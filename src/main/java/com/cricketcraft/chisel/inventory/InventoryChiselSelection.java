package com.cricketcraft.chisel.inventory;

import com.cricketcraft.chisel.api.CarvingRegistry;
import com.cricketcraft.chisel.item.chisel.ItemChisel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IChatComponent;

public class InventoryChiselSelection implements IInventory {
    ItemStack chisel = null;
    public static final int normalSlots = 60;
    public int activeVariations = 0;
    ContainerChisel container;
    ItemStack[] inventory;

    public InventoryChiselSelection(ItemStack c) {
        super();
        inventory = new ItemStack[normalSlots + 1];
        chisel = c;
    }

    public void onInventoryUpdate(int slot) {

    }

    @Override
    public int getSizeInventory() {
        return normalSlots + 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    public void updateInventoryState(int slot) {
        onInventoryUpdate(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (inventory[slot] != null) {
            ItemStack stack;
            if (inventory[slot].stackSize <= amount) {
                stack = inventory[slot];
                inventory[slot] = null;
                updateInventoryState(slot);
                return stack;
            } else {
                stack = inventory[slot].splitStack(amount);
                if (inventory[slot].stackSize == 0)
                    inventory[slot] = null;
                updateInventoryState(slot);
                return stack;
            }
        } else
            return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public String getName() {
        return "container.chisel";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    public void clearItems() {
        activeVariations = 0;
        for (int c = 0; c < normalSlots; c++) {
            inventory[c] = null;
        }
    }

    public ItemStack getStackInSpecialSlot() {
        return inventory[normalSlots];
    }

    public void updateItems(ItemStack stackInChiselSlot) {
        ItemStack chiseledItem = inventory[normalSlots];
        clearItems();

        if (chiseledItem == null) {
            container.onChiselSlotChanged();
            return;
        }

        Item item = chiseledItem.getItem();
        if (item == null)
            return;

        if (Block.getBlockFromItem(item) == null)
            return;
        ItemStack[] stacks;
        if (CarvingRegistry.getRecipeFromFirstItem(stackInChiselSlot) != null) {
            stacks = CarvingRegistry.getRecipeFromFirstItem(stackInChiselSlot).getChiselResults();

            activeVariations = 0;
            while (activeVariations < normalSlots && activeVariations < stacks.length) {
                if (Block.blockRegistry.getNameForObject(Block.getBlockFromItem(stacks[activeVariations].getItem())) != null) {
                    inventory[activeVariations] = stacks[activeVariations];
                    activeVariations++;
                }
            }
        }

        container.onChiselSlotChanged();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (stack.getItem() instanceof ItemTool) {
            return false;
        }

        return !(stack != null && (stack.getItem() instanceof ItemChisel)) && slot == normalSlots;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
