package dev.kirakun.magicalwings.events;

import dev.kirakun.magicalwings.MagicalWings;
import dev.kirakun.magicalwings.PlayerWingsData;
import dev.kirakun.magicalwings.Wings;
import dev.kirakun.magicalwings.network.serverbound.UpdateFlying;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EventsHandler
{
    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        Item chestplate = player.inventory.getArmor(2).getItem();

        if (chestplate instanceof Wings)
            player.fallDistance = 0;

        if (!event.player.isLocalPlayer())
            return;

        if (chestplate instanceof Wings && !player.abilities.mayfly)
            player.abilities.flying = true;

        PlayerWingsData pwdata = MagicalWings.TotalWingsData.get(player.getName().getString());
        if (pwdata == null)
        {
            MagicalWings.TotalWingsData.put(player.getName().getString(), new PlayerWingsData(chestplate, player.isCreative(), player.abilities.flying));
            ChestplateChanged(player, chestplate);

            if (event.side == LogicalSide.CLIENT)
                MagicalWings.Network.sendToServer(new UpdateFlying(player.getId(), player.abilities.flying));
        }
        else
        {
            if (chestplate != pwdata.LastChestplate)
            {
                pwdata.LastChestplate = chestplate;
                ChestplateChanged(player, chestplate);
            }

            if (player.isCreative() != pwdata.LastIsCreative)
            {
                pwdata.LastIsCreative = player.isCreative();
                ChestplateChanged(player, chestplate);
            }

            if (pwdata.LastIsFlying != player.abilities.flying)
            {
                pwdata.LastIsFlying = player.abilities.flying;

                if (event.side == LogicalSide.CLIENT)
                    MagicalWings.Network.sendToServer(new UpdateFlying(player.getId(), player.abilities.flying));

                MagicalWings.TotalWingsData.put(player.getName().getString(), pwdata);
                /*MagicalWings.TotalWingsData.remove(player.getName().getString());
                MagicalWings.TotalWingsData.put(player.getName().getString(), pwdata);
                System.out.println("Send packet: " + player.getName().getString() + player.abilities.flying + " | " + pwdata.LastIsFlying);*/
            }
        }
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
    }
}
