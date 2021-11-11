package alexthw.indestructible.recipe;

import alexthw.indestructible.init.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

import static alexthw.indestructible.IndestructibleMod.rl;

public class APRecipeWrapper {

    public static List<UpgradeRecipe> getAll() {

        List<UpgradeRecipe> recipes = new ArrayList<>();

        ForgeRegistries.ITEMS.getValues().stream().filter(Item::canBeDepleted).forEach(item -> {

            ResourceLocation id = rl("jei.applyindestructible." + item.getDescriptionId());
            ItemStack output = new ItemStack(item);
            Ingredient catalyst = Ingredient.of(Registry.INDESTRUCTIBLE_GEM.get());

            output.enchant(Registry.INDESTRUCTIBLE.get(),1);
            recipes.add(new UpgradeRecipe(id,Ingredient.of(item),catalyst,output));
        });

        return recipes;
    }
}
