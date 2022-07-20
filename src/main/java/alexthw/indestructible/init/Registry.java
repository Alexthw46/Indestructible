package alexthw.indestructible.init;

import alexthw.indestructible.common.CraftingBlockEntity;
import alexthw.indestructible.common.Gem;
import alexthw.indestructible.common.GemBlock;
import alexthw.indestructible.common.IndestructibleEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class Registry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<Enchantment> INDESTRUCTIBLE;
    public static final RegistryObject<Item> INDESTRUCTIBLE_GEM;
    public static final RegistryObject<Block> GEM_BLOCK;

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
        ENCHANTMENTS.register(bus);
    }

    static {
        INDESTRUCTIBLE = ENCHANTMENTS.register("indestructible_enchant", () -> new IndestructibleEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
        INDESTRUCTIBLE_GEM = ITEMS.register("indestructible_gem", () -> new Gem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).fireResistant().rarity(Rarity.RARE)));
        GEM_BLOCK = BLOCKS.register("gem_crafting_block", ()-> new GemBlock(BlockBehaviour.Properties
                .of(Material.AMETHYST)
                .noOcclusion()
                .noCollission()));
    }

    public static final RegistryObject<BlockEntityType<CraftingBlockEntity>> CRAFTING_BLOCK = BLOCK_ENTITIES.register(
            "crafting_gem", () -> BlockEntityType.Builder
                    .of(CraftingBlockEntity::new, Registry.GEM_BLOCK.get())
                    .build(null)
    );

}
