package alexthw.indestructible.datagen;

import alexthw.indestructible.IndestructibleMod;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.NonNull;


import static alexthw.indestructible.init.Registry.*;


public class ModModelProvider extends ModelProvider {

    public ModModelProvider(DataGenerator generator) {
        super(generator.getPackOutput(), IndestructibleMod.MODID);
    }

    @Override
    protected void registerModels(@NonNull BlockModelGenerators blockModels, @NonNull ItemModelGenerators itemModels) {
        ITEMS.getEntries()
                .stream()
                .map(DeferredHolder::get)
                .forEach(item -> generatedItem(itemModels, item));

        BLOCKS.getEntries()
                .stream()
                .map(DeferredHolder::get)
                .forEach(block -> basicBlock(blockModels, block));

    }

    private void generatedItem(ItemModelGenerators gen, Item item) {
        gen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
    private void basicBlock(BlockModelGenerators gen, Block block) {
        gen.createTrivialCube(block);
    }

}
