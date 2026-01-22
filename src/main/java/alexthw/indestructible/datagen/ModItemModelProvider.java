package alexthw.indestructible.datagen;

import alexthw.indestructible.IndestructibleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;


import java.util.HashSet;
import java.util.Set;

import static alexthw.indestructible.IndestructibleMod.rl;
import static alexthw.indestructible.init.Registry.ITEMS;


public class ModItemModelProvider extends ItemModelProvider {

    private static final ResourceLocation GENERATED = ResourceLocation.withDefaultNamespace("item/generated");

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), IndestructibleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<DeferredHolder<Item, ? extends Item>> items = new HashSet<>(ITEMS.getEntries());

        items.forEach(this::generatedItem);

    }

    private void generatedItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", rl("item/" + name));
    }

    private void blockGeneratedItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", rl("block/" + name));
    }

    private void blockItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(rl("block/" + name)));
    }

}
