package alexthw.indestructible;

import alexthw.indestructible.client.CraftingGemRenderer;
import alexthw.indestructible.init.Registry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static alexthw.indestructible.init.Registry.INDESTRUCTIBLE_GEM;

@Mod(IndestructibleMod.MODID)
public class IndestructibleMod {
    public static final String MODID = "indestructible";

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    public IndestructibleMod() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        Registry.init(bus);

        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(this::setupClient);
            return new Object();
        });
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTab() == CreativeModeTabRegistry.getTab(CreativeModeTabs.INGREDIENTS.location()))
                event.accept(INDESTRUCTIBLE_GEM.get());
        });
    }

    public void setup(final FMLCommonSetupEvent event) {
        Networking.init();
    }

    @OnlyIn(Dist.CLIENT)
    public void setupClient(final FMLClientSetupEvent event) {
        BlockEntityRenderers.register(Registry.CRAFTING_BLOCK.get(), context -> new CraftingGemRenderer());
    }

}
