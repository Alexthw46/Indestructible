package alexthw.indestructible;

import alexthw.indestructible.client.ClientEventHandler;
import alexthw.indestructible.client.CraftingGemRenderer;
import alexthw.indestructible.init.Registry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(IndestructibleMod.MODID)
public class IndestructibleMod {
    public static final String MODID = "indestructible";


    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public IndestructibleMod(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        Registry.init(modEventBus);
        modEventBus.addListener(this::addToCreativeTab);
        if (FMLEnvironment.getDist().isClient()) modEventBus.addListener(ClientEventHandler::setupClient);
    }

    @SubscribeEvent
    public void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            event.accept(Registry.INDESTRUCTIBLE_GEM.get());
    }

}
