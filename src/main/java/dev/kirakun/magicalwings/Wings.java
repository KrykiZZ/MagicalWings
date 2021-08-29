package dev.kirakun.magicalwings;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public class Wings extends ArmorItem
{
    private final String Name;

    public Wings (String name, IArmorMaterial material, EquipmentSlotType slot)
    {
        super(material, slot, new Properties().tab(ItemGroup.TAB_COMBAT));
        this.Name = name;
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlotType slot, String layer)
    {
        if (entity instanceof PlayerEntity && slot == EquipmentSlotType.CHEST)
            return "magicalwings:textures/armor/empty.png";

        if(layer == null)
        {
            return "magicalwings:textures/armor/" + this.Name + ".png";
        }
        else return "magicalwings:textures/armor/" + this.Name + "_overlay.png";
    }
}
