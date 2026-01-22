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

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<Item, Item> INDESTRUCTIBLE_GEM;
    public static final DeferredHolder<Block, Block> GEM_BLOCK;

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    static {
        INDESTRUCTIBLE_GEM = ITEMS.register("indestructible_gem", () -> new Gem(new Item.Properties().fireResistant().rarity(Rarity.RARE)));
        GEM_BLOCK = BLOCKS.register("gem_crafting_block", () -> new GemBlock(BlockBehaviour.Properties
                .ofFullCopy(Blocks.AMETHYST_BLOCK)
                .noOcclusion()
                .noCollission()));
    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CraftingBlockEntity>> CRAFTING_BLOCK = BLOCK_ENTITIES.register(
            "crafting_gem", () -> BlockEntityType.Builder
                    .of(CraftingBlockEntity::new, Registry.GEM_BLOCK.get())
                    .build(null)
    );

}
