package alexthw.indestructible.datagen;

import alexthw.indestructible.init.ModTag;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        tag(ModTag.Items.BLACKLIST).add(Items.AIR);

    }
}
