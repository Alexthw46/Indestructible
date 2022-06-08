package alexthw.indestructible.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import static alexthw.indestructible.IndestructibleMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public final class ModDataGen {

    private ModDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        BlockTagsProvider btp = new ModBlockTagProvider(gen,existingFileHelper);

        gen.addProvider(event.includeServer(), btp);
        gen.addProvider(event.includeServer(), new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModBlockStatesProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemTagProvider(gen, btp,existingFileHelper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen));
    }
}
