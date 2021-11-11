package alexthw.indestructible.mixin;

import alexthw.indestructible.init.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static alexthw.indestructible.init.Registry.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract ItemStack copy();

    @Inject(at = @At(value = "HEAD"), method = "hurt", cancellable = true)
    private void handleIndestructibleEnchantment(int i, Random j, ServerPlayer l, CallbackInfoReturnable<Boolean> cir) {
        if (EnchantmentHelper.getItemEnchantmentLevel(INDESTRUCTIBLE.get(), this.copy()) > 0){
            cir.setReturnValue(false);
        }
    }

}
