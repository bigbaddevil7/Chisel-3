package com.cricketcraft.chisel.api;

import com.cricketcraft.chisel.inventory.ContainerChisel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ChiselRecipe implements ICarvingRecipe {
    public final ItemStack[] group;

    /**
     * The output will also be used as the input;
     *
     * @param group
     */
    public ChiselRecipe(ItemStack[] group) {
        this.group = group;
    }

    @Override
    public boolean matches(ContainerChisel inv, World world) {
        for (int c = 0; c < group.length; c++) {
            if (group[c].getIsItemStackEqual(inv.inventory.getStackInSlot(0))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStack[] getChiselResults() {
        return group;
    }
}
