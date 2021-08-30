package dev.kirakun.magicalwings.network.serverbound;

import dev.kirakun.magicalwings.MagicalWings;
import dev.kirakun.magicalwings.network.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateFlying implements IMessage
{
    public int PlayerId;
    public Boolean IsFlying;

    public UpdateFlying(){}

    public UpdateFlying(int playerId, Boolean isFlying)
    {
        PlayerId = playerId;
        IsFlying = isFlying;
    }


    public static UpdateFlying decode(PacketBuffer buffer)
    {
        UpdateFlying data = new UpdateFlying();

        data.PlayerId = buffer.readVarInt();
        data.IsFlying = buffer.readBoolean();

        return data;
    }

    public static void encode(UpdateFlying data, PacketBuffer buffer)
    {
        buffer.writeVarInt(data.PlayerId);
        buffer.writeBoolean(data.IsFlying);
    }

    public static void handle(UpdateFlying msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            System.out.println("HANDLED as " + ctx.get().getDirection().getReceptionSide());
            if (ctx.get().getDirection().getReceptionSide() == LogicalSide.SERVER)
                handleServer(msg, ctx);
            else handleClient(msg, ctx);
        });

        ctx.get().setPacketHandled(true);
    }

    public static void handleClient(UpdateFlying msg, Supplier<NetworkEvent.Context> ctx)
    {
        System.out.println("Accepted as Client.");
        if (Minecraft.getInstance().player != null && msg.PlayerId == Minecraft.getInstance().player.getId())
            return;

        System.out.println("Applying flying.");
        MagicalWings.FlyingPlayers.put(msg.PlayerId, msg.IsFlying);
    }

    public static void handleServer(UpdateFlying msg, Supplier<NetworkEvent.Context> ctx)
    {
        System.out.println("Sending to all players on server.");
        MagicalWings.Network.sendToAll(msg);
        MagicalWings.FlyingPlayers.put(msg.PlayerId, msg.IsFlying);
    }
}
