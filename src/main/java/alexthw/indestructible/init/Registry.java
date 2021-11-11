package alexthw.indestructible.init;

import alexthw.indestructible.enchantment.IndestructibleEnchantment;
import alexthw.indestructible.recipe.ApplyEnchantment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static alexthw.indestructible.IndestructibleMod.MODID;

public class Registry {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final RegistryObject<Enchantment> INDESTRUCTIBLE;

    public static void init(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }

    static{
        INDESTRUCTIBLE = ENCHANTMENTS.register("indestructible_enchant", () -> new IndestructibleEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    }

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> evt) {
        IForgeRegistry<RecipeSerializer<?>> reg = evt.getRegistry();
        reg.register(ApplyEnchantment.SERIALIZER.setRegistryName(new ResourceLocation(MODID,"apply_indestructible_enchant")));
    }
}
