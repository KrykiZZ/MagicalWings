package dev.kirakun.magicalwings.network;

import dev.kirakun.magicalwings.network.serverbound.UpdateFlying;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class NetworkHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("magicalwings", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public void init()
    {
        INSTANCE.registerMessage(1, UpdateFlying.class, UpdateFlying::encode, UpdateFlying::decode, UpdateFlying::handle);
    }

    public void sendToServer(IMessage message)
    {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), message);
    }

    public void sendToAll(IMessage message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public void sendToPlayer(ServerPlayerEntity player, IMessage message)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
