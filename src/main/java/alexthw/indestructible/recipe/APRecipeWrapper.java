package alexthw.indestructible.recipe;

import alexthw.indestructible.init.Registry;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static alexthw.indestructible.IndestructibleMod.rl;

public class APRecipeWrapper {

    public static List<UpgradeRecipe> getAll() {

        List<UpgradeRecipe> recipes = new ArrayList<>();

        ForgeRegistries.ITEMS.getValues().stream().filter(Item::canBeDepleted).forEach(item -> {

            ResourceLocation id = rl("jei.applyindestructible." + item.getDescriptionId());
            ItemStack output = new ItemStack(item);
            Ingredient catalyst = Ingredient.of(Blocks.BEDROCK);

            output.enchant(Registry.INDESTRUCTIBLE.get(),1);
            recipes.add(new UpgradeRecipe(id,Ingredient.of(item),catalyst,output));
        });

        return recipes;
    }
}
