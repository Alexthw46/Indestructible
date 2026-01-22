package alexthw.indestructible.init;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static alexthw.indestructible.IndestructibleMod.rl;

public class ModTag {
    public static final class Items {

        public static final TagKey<Item> BLACKLIST = mod("blacklisted");
        public static final TagKey<Item> CATALYST = mod("catalyst");

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(rl(path));
        }

    }
}
