package alexthw.indestructible.common;

import alexthw.indestructible.init.ModTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import static alexthw.indestructible.Config.COMMON;

public class IndestructibleEnchantment extends Enchantment {
    public IndestructibleEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.BREAKABLE, pApplicableSlots);
    }

    public boolean canEnchant(ItemStack pStack) {
        boolean result = pStack.isDamageableItem() || super.canEnchant(pStack) || (pStack.getItem() == Items.BOOK && isAllowedOnBooks());
        if (pStack.isEnchanted()){
            result = result && pStack.getEnchantmentLevel(this) < 1;
        }
        return result && !pStack.is(ModTag.Items.BLACKLIST);
    }

    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return COMMON.ALLOW_ON_BOOKS.get();
    }

    /**
     * Checks if the enchantment should be considered a treasure enchantment. These enchantments can not be obtained
     * using the enchantment table. The mending enchantment is an example of a treasure enchantment.
     * @return Whether the enchantment is a treasure enchantment.
     */
    public boolean isTreasureOnly() {
        return true;
    }

    /**
     * Checks if the enchantment can be sold by villagers in their trades.
     */
    public boolean isTradeable() {
        return COMMON.ALLOW_TRADING.get();
    }

    /**
     * Checks if the enchantment can be applied to loot table drops.
     */
    public boolean isDiscoverable() {
        return COMMON.ALLOW_TREASURE.get();
    }

}
