package alexthw.indestructible.client;

import alexthw.indestructible.init.Registry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
    public static void setupClient(final FMLClientSetupEvent event) {
        BlockEntityRenderers.register(Registry.CRAFTING_BLOCK.get(), CraftingGemRenderer::new);
    }
}
