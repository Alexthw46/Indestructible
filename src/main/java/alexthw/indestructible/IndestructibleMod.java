package alexthw.indestructible;

import alexthw.indestructible.init.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("indestructible")
public class IndestructibleMod
{
    public static final String MODID = "indestructible";
    //private static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation rl(String path){
        return new ResourceLocation(MODID,path);
    }

    public IndestructibleMod() {
        // Register the setup method for modloading
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Registry.init(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
