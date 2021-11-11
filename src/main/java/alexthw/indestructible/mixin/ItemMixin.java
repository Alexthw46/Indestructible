package alexthw.indestructible.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)

public abstract class ItemMixin {

    @Inject(at = @At(value = "HEAD"), method = "inventoryTick")
    private void inventoryTickMix(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected, CallbackInfo ci){
        if (pStack.isDamaged()){
            CompoundTag nbt = pStack.getOrCreateTag();
            if (nbt.getBoolean("QuickRepair")){
                pStack.setDamageValue(0);
                nbt.remove("QuickRepair");
            }
        }
    }

}
