package alexthw.indestructible.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

import static alexthw.indestructible.IndestructibleMod.rl;

public class ModTag {
    public static final class Items {

        public static final Tag.Named<Item> BLACKLIST = mod("blacklisted");

        private static Tag.Named<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Item> mod(String path) {
            return ItemTags.bind(rl(path).toString());
        }

    }
}
