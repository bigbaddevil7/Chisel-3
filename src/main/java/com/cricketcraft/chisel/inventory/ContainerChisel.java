package com.cricketcraft.chisel.inventory;

import com.cricketcraft.chisel.inventory.slots.SlotChiselInput;
import com.cricketcraft.chisel.inventory.slots.SlotChiselSelection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerChisel extends Container {

    public final InventoryChiselSelection inventory;
    public InventoryPlayer inventoryPlayer;
    int chiselSlot;
    public ItemStack chisel;
    public boolean finished;

    public ContainerChisel(InventoryPlayer inventoryPlayer, InventoryChiselSelection inventory) {
        this.inventory = inventory;
        this.inventoryPlayer = inventoryPlayer;
        this.inventory.container = this;

        int top = 8, left = 62;
        for (int c = 0; c < InventoryChiselSelection.normalSlots; c++) {
            addSlotToContainer(new SlotChiselSelection(this, inventory, inventory, c, left + ((c % 10) * 18), top + ((c / 10) * 18)));
        }

        addSlotToContainer(new SlotChiselInput(this, inventory, InventoryChiselSelection.normalSlots, 24, 24));

        top += 112;
        left += 9;

        for (int c = 0; c < 27; c++) {
            addSlotToContainer(new Slot(inventoryPlayer, c + 9, left + ((c % 9) * 18), top + (c / 9) * 18));
        }

        for (int c = 0; c < 9; c++) {
            addSlotToContainer(new Slot(inventoryPlayer, c, left + c * 18, 178));
        }

        chisel = inventoryPlayer.getCurrentItem();
        if (chisel != null && chisel.getTagCompound() != null) {
            ItemStack stack = ItemStack.loadItemStackFromNBT(chisel.getTagCompound().getCompoundTag("chiselTarget"));
            inventory.setInventorySlotContents(InventoryChiselSelection.normalSlots, stack);
        }
        inventory.updateItems(chisel);
    }

    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
        int clickedSlot = slotId - inventory.getSizeInventory();
        if (clickedSlot == chiselSlot || (mode == 2 && clickedButton == chiselSlot))
            return null;
        return super.slotClick(slotId, clickedButton, mode, playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        inventory.clearItems();
        super.onContainerClosed(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = null;
        Slot slot = (Slot) inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (index > InventoryChiselSelection.normalSlots) {
                if (!mergeItemStack(stack1, InventoryChiselSelection.normalSlots, InventoryChiselSelection.normalSlots + 1, false)) {
                    return null;
                }
            } else {
                if (index < InventoryChiselSelection.normalSlots + 1 && stack1 != null) {
                    player.inventory.setItemStack(stack1.copy());
                    slot.onPickupFromSlot(player, stack1);
                    stack1 = player.inventory.getItemStack();
                    player.inventory.setItemStack(null);
                }

                if (!mergeItemStack(stack1, InventoryChiselSelection.normalSlots + 1, InventoryChiselSelection.normalSlots + 37, false)) {
                    return null;
                }
            }

            slot.onSlotChanged();

            if (stack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (stack1.stackSize == stack.stackSize) {
                return null;
            }
            if (index >= InventoryChiselSelection.normalSlots) {
                slot.onPickupFromSlot(player, stack1);
            }
            if (stack1.stackSize == 0) {
                slot.putStack(null);
                return null;
            }
        }

        return stack;
    }

    public void onChiselSlotChanged() {
        ItemStack stack = inventoryPlayer.mainInventory[chiselSlot];
        if (stack == null || !stack.isItemEqual(chisel)) {
            finished = true;
        }

        if (finished)
            return;

        setChiselTarget(chisel, inventory.getStackInSpecialSlot());
        inventoryPlayer.mainInventory[chiselSlot] = chisel;
    }

    public static void setChiselTarget(ItemStack chisel, ItemStack target) {
        initTag(chisel);
        NBTTagCompound targetTag = new NBTTagCompound();
        if (target != null) {
            target.writeToNBT(targetTag);
        }
        chisel.getTagCompound().setTag("chiselTarget", targetTag);
    }

    private static void initTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
