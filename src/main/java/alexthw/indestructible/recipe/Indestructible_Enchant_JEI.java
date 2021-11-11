package alexthw.indestructible.recipe;

import alexthw.indestructible.IndestructibleMod;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class Indestructible_Enchant_JEI implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(IndestructibleMod.MODID, "main");

    @Nonnull
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(APRecipeWrapper.getAll(), VanillaRecipeCategoryUid.SMITHING);
    }
}
