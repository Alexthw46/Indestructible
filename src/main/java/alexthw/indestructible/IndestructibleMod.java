package alexthw.indestructible;

import alexthw.indestructible.client.CraftingGemRenderer;
import alexthw.indestructible.init.Registry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
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


    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public IndestructibleMod(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        Registry.init(modEventBus);
        modEventBus.addListener(this::addToCreativeTab);
        if (FMLEnvironment.dist.isClient()) modEventBus.addListener(this::setupClient);
    }


    @OnlyIn(Dist.CLIENT)
    public void setupClient(final FMLClientSetupEvent event) {
        BlockEntityRenderers.register(Registry.CRAFTING_BLOCK.get(), CraftingGemRenderer::new);
    }

    @SubscribeEvent
    public void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            event.accept(Registry.INDESTRUCTIBLE_GEM.get());
    }

}
