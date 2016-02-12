package com.cricketcraft.chisel.inventory.slots;

import com.cricketcraft.chisel.inventory.ContainerChisel;
import com.cricketcraft.chisel.inventory.InventoryChiselSelection;
import net.minecraft.inventory.Slot;

public class SlotChiselInput extends Slot {

    ContainerChisel container;
    InventoryChiselSelection selection;

    public SlotChiselInput(ContainerChisel container, InventoryChiselSelection inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
        selection = inv;
        this.container = container;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        selection.updateItems(selection.getStackInSlot(InventoryChiselSelection.normalSlots));
    }
}
