package alexthw.indestructible.datagen;

import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import static alexthw.indestructible.IndestructibleMod.MODID;

@EventBusSubscriber(modid = MODID)

public final class ModDataGen {

    private ModDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var provider = event.getLookupProvider();
        BlockTagsProvider btp = new ModBlockTagProvider(gen, existingFileHelper, provider);

        gen.addProvider(event.includeServer(), btp);
        gen.addProvider(event.includeClient(), new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModBlockStatesProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemTagProvider(gen, btp, existingFileHelper, provider));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen, provider));
    }
}
