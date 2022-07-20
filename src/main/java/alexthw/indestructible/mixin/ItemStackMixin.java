package alexthw.indestructible.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static alexthw.indestructible.init.Registry.INDESTRUCTIBLE;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract ItemStack copy();

    @Inject(at = @At(value = "HEAD"), method = "isDamageableItem", cancellable = true)
    private void handleIndestructibleEnchantment(CallbackInfoReturnable<Boolean> cir) {
        if (this.copy().getEnchantmentLevel(INDESTRUCTIBLE.get()) > 0){
            cir.setReturnValue(false);
        }
    }

}
