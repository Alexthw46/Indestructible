package alexthw.indestructible.init;

import alexthw.indestructible.enchantment.IndestructibleEnchantment;
import alexthw.indestructible.recipe.ApplyEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class Registry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,MODID);

    public static final RegistryObject<Enchantment> INDESTRUCTIBLE;
    public static final RegistryObject<Item> INDESTRUCTIBLE_GEM;
    public static final RegistryObject<RecipeSerializer<?>> ENCHANT_SERIALIZER;

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        ENCHANTMENTS.register(bus);
        SERIALIZERS.register(bus);
    }

    static{
        INDESTRUCTIBLE = ENCHANTMENTS.register("indestructible_enchant", () -> new IndestructibleEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
        INDESTRUCTIBLE_GEM = ITEMS.register("indestructible_gem",() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).fireResistant().rarity(Rarity.RARE)));
        ENCHANT_SERIALIZER = SERIALIZERS.register("apply_indestructible", () -> new SimpleRecipeSerializer<>(ApplyEnchantment::new));
    }

}
