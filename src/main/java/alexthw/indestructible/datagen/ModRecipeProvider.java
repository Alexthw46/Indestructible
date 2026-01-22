package alexthw.indestructible.datagen;

import alexthw.indestructible.init.Registry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> provider) {
        super(pGenerator.getPackOutput(), provider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput p_recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registry.INDESTRUCTIBLE_GEM.get()).define('D', Items.DIAMOND).define('S', Items.NETHERITE_SCRAP).pattern("SSS").pattern("SDS").pattern("SSS").unlockedBy("netherite_discovered", has(Items.NETHERITE_SCRAP)).save(p_recipeOutput);
    }
}
