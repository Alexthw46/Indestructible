package alexthw.indestructible.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;


import java.util.concurrent.CompletableFuture;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataGenerator.getPackOutput(), provider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

    }
}
