package alexthw.indestructible.datagen;

import alexthw.indestructible.IndestructibleMod;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static alexthw.indestructible.IndestructibleMod.rl;
import static alexthw.indestructible.init.Registry.ITEMS;


public class ModItemModelProvider extends ItemModelProvider {

    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, IndestructibleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());

        items.forEach(this::generatedItem);

    }

    private void generatedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", rl("item/" + name));
    }

    private void blockGeneratedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", rl("block/" + name));
    }

    private void blockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(rl("block/" + name)));
    }

}
