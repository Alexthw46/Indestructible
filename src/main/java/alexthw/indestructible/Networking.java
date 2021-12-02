package alexthw.indestructible;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static alexthw.indestructible.IndestructibleMod.rl;

public class Networking {

    public static SimpleChannel INSTANCE;

    static int id = 0;

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(rl("network"), () -> "1.0", (s) -> true, (s) -> true);
    }

    public static <MSG> void sendToDimension(Level world, MSG msg, ResourceKey<Level> dimension) {
        Networking.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> dimension), msg);
    }

    public static <MSG> void sendToTracking(Level world, BlockPos pos, MSG msg) {
        Networking.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunkAt(pos)), msg);
    }

    public static <MSG> void sendTo(Player entity, MSG msg) {
        Networking.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), msg);
    }

    public static <MSG> void sendToServer(MSG msg) {
            Networking.INSTANCE.sendToServer(msg);
        }

}
