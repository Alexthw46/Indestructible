package alexthw.indestructible.datagen;

import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import static alexthw.indestructible.IndestructibleMod.MODID;

@EventBusSubscriber(modid = MODID)

public final class ModDataGen {

    private ModDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        var provider = event.getLookupProvider();
        BlockTagsProvider btp = new ModBlockTagProvider(gen, provider);

        gen.addProvider(true, btp);
        gen.addProvider(true, new ModModelProvider(gen));
        gen.addProvider(true, new ModItemTagProvider(gen, provider));
        gen.addProvider(true, new ModRecipeProvider.Runner(gen.getPackOutput(), provider));
    }
}
