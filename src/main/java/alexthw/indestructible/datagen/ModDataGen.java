package alexthw.indestructible.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static alexthw.indestructible.IndestructibleMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public final class ModDataGen {

    private ModDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        BlockTagsProvider btp = new ModBlockTagProvider(gen,existingFileHelper, lookup);

        gen.addProvider(event.includeServer(), btp);
        gen.addProvider(event.includeClient(), new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(event.includeClient(), new ModBlockStatesProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemTagProvider(gen, btp,existingFileHelper, lookup));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen));
    }
}
