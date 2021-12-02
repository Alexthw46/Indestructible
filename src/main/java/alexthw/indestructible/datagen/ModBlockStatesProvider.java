package alexthw.indestructible.datagen;

import alexthw.indestructible.IndestructibleMod;
import alexthw.indestructible.init.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, IndestructibleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(Registry.BLOCKS.getEntries());
        blocks.forEach(this::basicBlock);
    }

    private void basicBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get());
    }

}
