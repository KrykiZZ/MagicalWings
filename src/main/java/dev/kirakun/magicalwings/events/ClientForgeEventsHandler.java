package dev.kirakun.magicalwings.events;

import dev.kirakun.magicalwings.layers.WingsLayer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "magicalwings", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEventsHandler
{
    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event) {
        PlayerEntity player = event.getPlayer();
        PlayerRenderer renderer = event.getRenderer();
        renderer.addLayer(new WingsLayer<>(renderer));
    }

    /*@SubscribeEvent
    public static void onRenderLivingEventPre(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event)
    {
        WingsLayer layer = new WingsLayer(event.getRenderer());
        event.getRenderer().addLayer(layer);
    }*/
}
