package com.cricketcraft.chisel.api;

import com.cricketcraft.chisel.util.BlockVariant;
import com.cricketcraft.chisel.util.PropertyVariant;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.cricketcraft.chisel.init.ChiselBlocks.*;
import static com.cricketcraft.chisel.init.ChiselProperties.*;

public class CarvingRegistry {

    private static final CarvingRegistry instance = new CarvingRegistry();
    private static final List<ICarvingRecipe> recipes = Lists.<ICarvingRecipe>newArrayList();

    public static CarvingRegistry getInstance() {
        return instance;
    }

    private CarvingRegistry() {

    }

    public static void preInit() {
        //Load the class
    }

    /**
     * For internal use only
     */
    public static void init() {
        addRecipeForPropVariant(acacia, ACACIA_VARIANTS);
        addRecipeForPropVariant(aluminum, ALUMINUM_VARIANTS);
        addRecipeForPropVariant(andesite, ANDESITE_VARIANTS);
        addRecipeForPropVariant(antiblock, ANTIBLOCK_VARIANTS);
        addRecipeForPropVariant(birch, BIRCH_VARIANTS);
        addRecipeForPropVariant(bronze, BRONZE_VARIANTS);
        addRecipeForPropVariant(bricks, BRICKS_VARIANTS);
        addRecipeForPropVariant(bookshelf, BOOKSHELF_VARIANTS);
        addRecipeForPropVariant(carpet, CARPET_VARIANTS);
        addRecipeForPropVariant(carpet_floor, CARPET_FLOOR_VARIANTS);
        addRecipeForPropVariant(cobblestone, COBBLESTONE_VARIANTS);
        addRecipeForPropVariant(concrete, CONCRETE_VARIANTS);
        addRecipeForPropVariant(copper, COPPER_VARIANTS);
        addRecipeForPropVariant(cloud, CLOUD_VARIANTS);
        addRecipeForPropVariant(dark_oak, DARK_OAK_VARIANTS);
        addRecipeForPropVariant(diamond, DIAMOND_VARIANTS);
        addRecipeForPropVariant(diorite, DIORITE_VARIANTS);
        addRecipeForPropVariant(dirt, DIRT_VARIANTS);
        addRecipeForPropVariant(emerald, EMERALD_VARIANTS);
        addRecipeForPropVariant(energised_voidstone, ENERGISED_VOIDSTONE_VARIANTS);
        addRecipeForPropVariant(energised_voidstone_pillar, ENERGISED_VOIDSTONE_PILLAR_VARIANTS);
        addRecipeForPropVariant(factory, FACTORY_VARIANTS);
        addRecipeForPropVariant(fantasy, FANTASY_VARIANTS);
        addRecipeForPropVariant(futura, FUTURA_VARIANTS);
        addRecipeForPropVariant(glass, GLASS_VARIANTS);
        addRecipeForPropVariant(glass_pane, GLASS_PANE_VARIANTS);
        addRecipeForPropVariant(glowstone, GLOWSTONE_VARIANTS);
        addRecipeForPropVariant(gold, GOLD_VARIANTS);
        addRecipeForPropVariant(gold2, GOLD2_VARIANTS);
        addRecipeForPropVariant(granite, GRANITE_VARIANTS);
        addRecipeForPropVariant(grimstone, GRIMSTONE_VARIANTS);
        addRecipeForPropVariant(hex_plating, HEX_PLATING_VARIANTS);
        addRecipeForPropVariant(holystone, HOLYSTONE_VARIANTS);
        addRecipeForPropVariant(ice, ICE_VARIANTS);
        addRecipeForPropVariant(ice_pillar, ICE_PILLAR_VARIANTS);
        addRecipeForPropVariant(industrial, INDUSTRIAL_VARIANTS);
        addRecipeForPropVariant(iron, IRON_VARIANTS);
        addRecipeForPropVariant(iron_bars, IRON_BARS_VARIANTS);
        addRecipeForPropVariant(jungle, JUNGLE_VARIANTS);
        addRecipeForPropVariant(laboratory, LABORATORY_VARIANTS);
        addRecipeForPropVariant(lapis, LAPIS_VARIANTS);
        addRecipeForPropVariant(large_hex_plating, LARGE_HEX_PLATING_VARIANTS);
        addRecipeForPropVariant(lavastone, LAVASTONE_VARIANTS);
        addRecipeForPropVariant(lead, LEAD_VARIANTS);
        addRecipeForPropVariant(leaf, LEAF_VARIANTS);
        addRecipeForPropVariant(limestone, LIMESTONE_VARIANTS);
        addRecipeForPropVariant(litpumpkin, LITPUMPKIN_VARIANTS);
        addRecipeForPropVariant(marble, MARBLE_VARIANTS);
        addRecipeForPropVariant(mossy_cobblestone, MOSSY_COBBLESTONE_VARIANTS);
        addRecipeForPropVariant(mossy_temple, MOSSY_TEMPLE_VARIANTS);
        addRecipeForPropVariant(netherbrick, NETHERBRICK_VARIANTS);
        addRecipeForPropVariant(netherrack, NETHERRACK_VARIANTS);
        addRecipeForPropVariant(oak, OAK_VARIANTS);
        addRecipeForPropVariant(obsidian, OBSIDIAN_VARIANTS);
        addRecipeForPropVariant(packed_ice, PACKED_ICE_VARIANTS);
        addRecipeForPropVariant(packed_ice_pillar, PACKED_ICE_PILLAR_VARIANTS);
        addRecipeForPropVariant(paperwall, PAPERWALL_VARIANTS);
        addRecipeForPropVariant(paperwall_block, PAPERWALL_BLOCK_VARIANTS);
        addRecipeForPropVariant(pumpkin, PUMPKIN_VARIANTS);
        addRecipeForPropVariant(purpled_fantasy, PURPLED_FANTASY_VARIANTS);
        addRecipeForPropVariant(redstone, REDSTONE_VARIANTS);
        addRecipeForPropVariant(road_line, ROAD_LINE_VARIANTS);
        addRecipeForPropVariant(runic_voidstone, RUNIC_VOIDSTONE_VARIANTS);
        addRecipeForPropVariant(sandstone, SANDSTONE_VARIANTS);
        addRecipeForPropVariant(sandstone_scribbles, SANDSTONE_SCRIBBLES_VARIANTS);
        addRecipeForPropVariant(silver, SILVER_VARIANTS);
        addRecipeForPropVariant(snakestone_sand, SNAKESTONE_SAND_VARIANTS);
        addRecipeForPropVariant(snakestone_stone, SNAKESTONE_STONE_VARIANTS);
        addRecipeForPropVariant(spruce, SPRUCE_VARIANTS);
        addRecipeForPropVariant(stained_glass_black, STAINED_GLASS_BLACK_VARIANTS);
        addRecipeForPropVariant(stained_glass_red, STAINED_GLASS_RED_VARIANTS);
        addRecipeForPropVariant(stained_glass_green, STAINED_GLASS_GREEN_VARIANTS);
        addRecipeForPropVariant(stained_glass_brown, STAINED_GLASS_BROWN_VARIANTS);
        addRecipeForPropVariant(stained_glass_blue, STAINED_GLASS_BLUE_VARIANTS);
        addRecipeForPropVariant(stained_glass_purple, STAINED_GLASS_PURPLE_VARIANTS);
        addRecipeForPropVariant(stained_glass_cyan, STAINED_GLASS_CYAN_VARIANTS);
        addRecipeForPropVariant(stained_glass_light_gray, STAINED_GLASS_LIGHT_GRAY_VARIANTS);
        addRecipeForPropVariant(stained_glass_gray, STAINED_GLASS_GRAY_VARIANTS);
        addRecipeForPropVariant(stained_glass_pink, STAINED_GLASS_PINK_VARIANTS);
        addRecipeForPropVariant(stained_glass_lime, STAINED_GLASS_LIME_VARIANTS);
        addRecipeForPropVariant(stained_glass_yellow, STAINED_GLASS_YELLOW_VARIANTS);
        addRecipeForPropVariant(stained_glass_light_blue, STAINED_GLASS_LIGHT_BLUE_VARIANTS);
        addRecipeForPropVariant(stained_glass_magenta, STAINED_GLASS_MAGENTA_VARIANTS);
        addRecipeForPropVariant(stained_glass_orange, STAINED_GLASS_ORANGE_VARIANTS);
        addRecipeForPropVariant(stained_glass_white, STAINED_GLASS_WHITE_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_black, STAINED_GLASS_PANE_BLACK_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_red, STAINED_GLASS_PANE_RED_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_green, STAINED_GLASS_PANE_GREEN_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_brown, STAINED_GLASS_PANE_BROWN_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_blue, STAINED_GLASS_PANE_BLUE_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_purple, STAINED_GLASS_PANE_PURPLE_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_cyan, STAINED_GLASS_PANE_CYAN_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_light_gray, STAINED_GLASS_PANE_LIGHT_GRAY_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_gray, STAINED_GLASS_PANE_GRAY_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_pink, STAINED_GLASS_PANE_PINK_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_lime, STAINED_GLASS_PANE_LIME_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_yellow, STAINED_GLASS_PANE_YELLOW_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_light_blue, STAINED_GLASS_PANE_LIGHT_BLUE_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_magenta, STAINED_GLASS_PANE_MAGENTA_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_orange, STAINED_GLASS_PANE_ORANGE_VARIANTS);
        addRecipeForPropVariant(stained_glass_pane_white, STAINED_GLASS_PANE_WHITE_VARIANTS);
        addRecipeForPropVariant(steel, STEEL_VARIANTS);
        addRecipeForPropVariant(stone_bricks, STONE_BRICKS_VARIANTS);
        addRecipeForPropVariant(technical, TECHNICAL_VARIANTS);
        addRecipeForPropVariant(temple, TEMPLE_VARIANTS);
        addRecipeForPropVariant(tin, TIN_VARIANTS);
        addRecipeForPropVariant(torch, TORCH_VARIANTS);
        addRecipeForPropVariant(transparent_technical, TRANSPARENT_TECHNICAL_VARIANSTS);
        addRecipeForPropVariant(tyrian, TYRIAN_VARIANTS);
        addRecipeForPropVariant(uranium, URANIUM_VARIANTS);
        addRecipeForPropVariant(valentines, VALENTINES_VARIANTS);
        addRecipeForPropVariant(voidstone, VOIDSTONE_VARIANTS);
        addRecipeForPropVariant(warning, WARNING_VARIANTS);
        addRecipeForPropVariant(waterstone, WATERSTONE_VARIANTS);
        addRecipeForPropVariant(woolen_clay, WOOLEN_CLAY_VARIANTS);
    }

    public static void addRecipe(ItemStack... group) {
        ChiselRecipe recipe = new ChiselRecipe(group);
        recipes.add(recipe);
    }

    private static void addRecipeForPropVariant(Block block, PropertyVariant variants) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        for (BlockVariant variant : variants.getAllowedValues()) {
            list.add(new ItemStack(block, 1, variant.getMeta()));
        }
        ItemStack[] recipe = new ItemStack[list.size()];
        list.toArray(recipe);
        addRecipe(recipe);
    }

    public static ChiselRecipe getRecipeFromItemStack(ItemStack stack) {
        ItemStack stack1 = new ItemStack(stack.getItem(), 1, stack.getItemDamage());
        for (int c = 0; c < recipes.size(); c++) {
            ItemStack[] results = recipes.get(c).getChiselResults();
            for (int d = 0; d < results.length; d++) {
                if (stack1.getIsItemStackEqual(results[d])) {
                    return (ChiselRecipe) recipes.get(c);
                }
            }
        }

        return null;
    }
}
