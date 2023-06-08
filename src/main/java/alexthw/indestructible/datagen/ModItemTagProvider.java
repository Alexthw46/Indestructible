package alexthw.indestructible.datagen;

import alexthw.indestructible.init.ModTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataGenerator.getPackOutput(), provider, blockTagProvider.contentsGetter(), MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(ModTag.Items.BLACKLIST).add(Items.AIR);
    }
}
