package com.cricketcraft.chisel.api;

import com.cricketcraft.chisel.inventory.ContainerChisel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICarvingRecipe {
    /**
     * Used to check if the first item matches the current inventory
     */
    boolean matches(ContainerChisel inv, World world);

    /**
     * Returns a list of items that are the result of the input
     */
    ItemStack[] getChiselResults();
}
