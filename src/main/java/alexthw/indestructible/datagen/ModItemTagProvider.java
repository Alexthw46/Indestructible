package alexthw.indestructible.datagen;

import alexthw.indestructible.init.ModTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataGenerator.getPackOutput(), provider, blockTagProvider.contentsGetter(), MODID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        tag(ModTag.Items.BLACKLIST).add(Items.AIR);
        tag(ModTag.Items.CATALYST).add(Items.BLAZE_POWDER);

    }
}
