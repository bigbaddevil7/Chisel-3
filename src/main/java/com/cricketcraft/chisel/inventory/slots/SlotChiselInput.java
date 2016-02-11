package com.cricketcraft.chisel.inventory.slots;

import com.cricketcraft.chisel.inventory.ContainerChisel;
import com.cricketcraft.chisel.inventory.InventoryChiselSelection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChiselInput extends Slot {

    ContainerChisel container;
    InventoryChiselSelection selection;

    public SlotChiselInput(ContainerChisel container, InventoryChiselSelection inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
        selection = inv;
        this.container = container;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (container.finished)
            return false;

        return super.isItemValid(stack);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return !container.finished && super.canTakeStack(player);
    }

    @Override
    public void onSlotChanged() {
        if (container.finished)
            return;
        super.onSlotChanged();
        selection.updateItems(null);
    }
}
