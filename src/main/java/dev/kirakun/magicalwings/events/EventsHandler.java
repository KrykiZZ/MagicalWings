package dev.kirakun.magicalwings.events;

import dev.kirakun.magicalwings.Wings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EventsHandler
{
    public static Item LastChestplate = null;
    public static Boolean LastIsCreative = false;
    public static Boolean IsFirstHandle = true;

    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        Item chestplate = player.inventory.getArmor(2).getItem();

        if (IsFirstHandle)
        {
            LastChestplate = chestplate;
            LastIsCreative = player.isCreative();

            IsFirstHandle = false;
            ChestplateChanged(player, chestplate);
        }
        else
        {
            if (chestplate != LastChestplate)
            {
                LastChestplate = chestplate;
                ChestplateChanged(player, chestplate);
            }

            if (player.isCreative() != LastIsCreative)
            {
                LastIsCreative = player.isCreative();
                ChestplateChanged(player, chestplate);
            }
        }

        if (chestplate instanceof Wings)
            player.fallDistance = 0;
    }

    public static void ChestplateChanged(PlayerEntity player, Item newChestplate)
    {
        if (newChestplate instanceof Wings)
        {
            if (!player.isCreative() && !player.abilities.mayfly)
                player.abilities.mayfly = true;
        }
        else
        {
            if (!player.isCreative())
            {
                if (player.abilities.mayfly)
                    player.abilities.mayfly = false;

                if (player.abilities.flying)
                    player.abilities.flying = false;
            }
        }

        player.onUpdateAbilities();
    }
}
