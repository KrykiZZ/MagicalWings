package dev.kirakun.magicalwings;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IDyeableArmorItem;

public class LeatherWings extends Wings implements IDyeableArmorItem
{
    public LeatherWings (String name, IArmorMaterial material, EquipmentSlotType slot)
    {
        super(name, material, slot);
    }
}
