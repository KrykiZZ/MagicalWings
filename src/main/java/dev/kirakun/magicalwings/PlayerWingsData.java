package dev.kirakun.magicalwings;

import net.minecraft.item.Item;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerWingsData
{
    public Item LastChestplate = null;
    public Boolean LastIsCreative = false;
    public Boolean LastIsFlying = false;

    public PlayerWingsData(Item chestplate, Boolean lastIsCreative, Boolean lastIsFlying)
    {
        LastChestplate = chestplate;
        LastIsCreative = lastIsCreative;
        LastIsFlying = lastIsFlying;
    }
}
