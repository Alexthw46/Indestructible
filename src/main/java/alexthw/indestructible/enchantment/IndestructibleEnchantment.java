package alexthw.indestructible.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class IndestructibleEnchantment extends Enchantment {
    public IndestructibleEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.BREAKABLE, pApplicableSlots);
    }

    public boolean canEnchant(ItemStack pStack) {
        return (pStack.isDamageableItem() || super.canEnchant(pStack));
    }

    public int getMaxLevel() {
        return 1;
    }

    /**
     * Checks if the enchantment should be considered a treasure enchantment. These enchantments can not be obtained
     * using the enchantment table. The mending enchantment is an example of a treasure enchantment.
     * @return Whether or not the enchantment is a treasure enchantment.
     */
    public boolean isTreasureOnly() {
        return true;
    }

    /**
     * Checks if the enchantment can be sold by villagers in their trades.
     */
    public boolean isTradeable() {
        return false;
    }

    /**
     * Checks if the enchantment can be applied to loot table drops.
     */
    public boolean isDiscoverable() {
        return false;
    }

}
