package alexthw.indestructible.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }
}
