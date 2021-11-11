package alexthw.indestructible;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class Blacklist {

    public static Tag.Named<Item> INDESTRUCTIBLE_BLACKLISTED = mod("item_blacklisted");

        private static Tag.Named<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Item> mod(String path) {
            return ItemTags.createOptional(new ResourceLocation(IndestructibleMod.MODID, path));
        }
}
