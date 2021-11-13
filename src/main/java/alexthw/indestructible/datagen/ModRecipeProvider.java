package alexthw.indestructible.datagen;

import alexthw.indestructible.init.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registry.INDESTRUCTIBLE_GEM.get()).define('D', Items.DIAMOND).define('S',Items.NETHERITE_SCRAP).pattern("SSS").pattern("SDS").pattern("SSS").unlockedBy("netherite_discovered", has(Items.NETHERITE_SCRAP)).save(consumer);
    }
}
