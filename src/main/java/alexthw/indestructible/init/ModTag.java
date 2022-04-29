package alexthw.indestructible.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static alexthw.indestructible.IndestructibleMod.rl;

public class ModTag {
    public static final class Items {

        public static final TagKey<Item> BLACKLIST = mod("blacklisted");

        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(rl(path));
        }

    }
}
