package alexthw.indestructible.datagen;

import alexthw.indestructible.IndestructibleMod;
import alexthw.indestructible.init.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;


import java.util.HashSet;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen.getPackOutput(), IndestructibleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        var blocks = new HashSet<>(Registry.BLOCKS.getEntries());
        blocks.forEach(this::basicBlock);
    }

    private void basicBlock(DeferredHolder<Block, ? extends Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get());
    }

}
