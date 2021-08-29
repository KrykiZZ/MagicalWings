package dev.kirakun.magicalwings.events;

import dev.kirakun.magicalwings.MagicalWings;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "magicalwings", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventsHandler
{
    @SubscribeEvent
    public static void onItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register(
                (stack, colorIn) -> colorIn < 1 ? -1 : ((IDyeableArmorItem) stack.getItem()).getColor(stack),
                MagicalWings.LEATHER_WINGS.get()
        );
    }
}
