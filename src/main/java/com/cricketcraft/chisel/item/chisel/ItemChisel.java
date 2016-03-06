package com.cricketcraft.chisel.item.chisel;

import com.cricketcraft.chisel.Chisel;
import com.cricketcraft.chisel.api.IChiselItem;
import com.cricketcraft.chisel.api.IChiselMode;
import com.cricketcraft.chisel.config.Configurations;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;

public class ItemChisel extends Item implements IChiselItem {
    public enum ChiselType {
        IRON(Configurations.ironChiselMaxDamage, Configurations.ironChiselAttackDamage),
        DIAMOND(Configurations.diamondChiselMaxDamage, Configurations.diamondChiselAttackDamage),
        OBSIDIAN(Configurations.obsidianChiselMaxDamage, Configurations.obsidianChiselAttackDamage);

        final int maxDamage, attackDamage;

        ChiselType(int maxDamage, int attackDamage) {
            this.maxDamage = maxDamage;
            this.attackDamage = attackDamage;
        }
    }

    private ChiselType type;

    public ItemChisel(ChiselType type) {
        super();
        this.type = type;
        setMaxStackSize(1);
        setCreativeTab(ChiselTabs.tabChisel);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (Configurations.allowChiselDamage) {
            return type.maxDamage;
        }

        return 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack held, World world, EntityPlayer player) {
        if (!world.isRemote && canOpenGui(world, player, held)) {
            player.openGui(Chisel.instance, 0, world, 0, 0, 0);
        }
        return held;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.chisel_" + type.name().toLowerCase();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {
        String base = "item.chisel.desc.";
        String gui = StatCollector.translateToLocal(base + "gui");
        String lc1 = StatCollector.translateToLocal(base + "lc1");
        String lc2 = StatCollector.translateToLocal(base + "lc2");
        String modes = StatCollector.translateToLocal(base + "modes");
        list.add(gui);
        if (type == ChiselType.DIAMOND || type == ChiselType.OBSIDIAN || Configurations.ironChiselCanLeftClick) {
            list.add(lc1);
            list.add(lc2);
        }

        if (type == ChiselType.DIAMOND || type == ChiselType.OBSIDIAN || Configurations.ironChiselHasModes) {
            list.add("");
            list.add(modes);
        }
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multiMap = HashMultimap.create();
        multiMap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Chisel Damage", type.attackDamage, 0));
        return multiMap;
    }

    @Override
    public boolean canOpenGui(World world, EntityPlayer player, ItemStack chisel) {
        return true;
    }

    @Override
    public boolean hasModes(ItemStack chisel) {
        return type == ChiselType.DIAMOND || type == ChiselType.OBSIDIAN || Configurations.ironChiselHasModes;
    }

    @Override
    public IChiselMode getMode(ItemStack chisel, String name) {
        return ChiselController.getMode(chisel);
    }
}
