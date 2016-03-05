package com.cricketcraft.chisel.inventory;

import com.cricketcraft.chisel.inventory.slots.SlotChiselSelection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChisel extends Container {

    /**
     * Slot IDs:
     * 0-59: Chisel GUI
     * 60: Chisel Input
     * 61-87: Player Inventory
     * 88-96: Hotbar
     */
    public ContainerChisel(IInventory player, InventoryChiselSelection chiselSelection) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(player, j + i * 9 + 62, 16 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            addSlotToContainer(new Slot(player, k, 8 + k * 18, 142));
        }

        for(int c = 0; c < 60; c++) {
            addSlotToContainer(new SlotChiselSelection(chiselSelection, c, 62 + ((c % 10) * 18), 8 + ((c / 10) * 18)));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack previous = null;
        Slot slot = inventorySlots.get(index);

        if(slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if(index < 62) {
                //From Chisel
                if(!mergeItemStack(current, 62, 98, true))
                    return null;
            } else {
                if(!mergeItemStack(current, 0, 0, false))
                    return null;
            }

            if(current.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if(current.stackSize == previous.stackSize)
                return null;

            slot.onPickupFromSlot(player, current);
        }

        return previous;
    }
}
