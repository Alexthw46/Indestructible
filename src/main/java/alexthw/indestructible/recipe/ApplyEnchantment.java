package alexthw.indestructible.recipe;

import alexthw.indestructible.init.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ApplyEnchantment extends UpgradeRecipe {

    public ApplyEnchantment(ResourceLocation pId) {
        super(pId, Ingredient.EMPTY , Ingredient.of(Registry.INDESTRUCTIBLE_GEM.get()), ItemStack.EMPTY);
    }

    @Override
    public boolean matches(Container inv, @Nonnull Level world) {
        ItemStack input = inv.getItem(0);
        
        if (!input.isDamageableItem() || !input.isEnchantable()) {
            return false;
        }

        return this.isAdditionIngredient(inv.getItem(1));
    }

    @Nonnull
    @Override
    public ItemStack assemble(Container pInv) {
        ItemStack item = pInv.getItem(0).copy();

        item.enchant(Registry.INDESTRUCTIBLE.get(),1);
        item.getOrCreateTag().putBoolean("QuickRepair",true);

        return item;
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registry.ENCHANT_SERIALIZER.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
