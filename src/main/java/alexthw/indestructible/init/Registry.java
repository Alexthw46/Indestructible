package alexthw.indestructible.init;

import alexthw.indestructible.common.CraftingBlockEntity;
import alexthw.indestructible.common.Gem;
import alexthw.indestructible.common.GemBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static alexthw.indestructible.IndestructibleMod.MODID;
import static net.minecraft.core.registries.BuiltInRegistries.BLOCK_ENTITY_TYPE;

public class Registry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<Item, Item> INDESTRUCTIBLE_GEM;
    public static final DeferredHolder<Block, Block> GEM_BLOCK;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CraftingBlockEntity>> CRAFTING_BLOCK;
    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    static {
        INDESTRUCTIBLE_GEM = ITEMS.registerItem("indestructible_gem", (properties) -> new Gem(properties.fireResistant().rarity(Rarity.RARE)));
        GEM_BLOCK = BLOCKS.registerBlock("gem_crafting_block", (props) -> new GemBlock(props
                .instabreak()
                .noOcclusion()
                .noCollision()
        ));
        CRAFTING_BLOCK = BLOCK_ENTITIES.register("crafting_gem", () -> new BlockEntityType<>(CraftingBlockEntity::new, Registry.GEM_BLOCK.get()));
    }


}
