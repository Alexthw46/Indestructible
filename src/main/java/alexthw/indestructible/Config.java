package alexthw.indestructible;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Common {

        public final ForgeConfigSpec.ConfigValue<Boolean> REPAIR_ENCHANT;
        public final ForgeConfigSpec.ConfigValue<Boolean> ALLOW_ON_BOOKS;
        public final ForgeConfigSpec.ConfigValue<Boolean> ALLOW_TREASURE;
        public final ForgeConfigSpec.ConfigValue<Boolean> ALLOW_TRADING;


        public Common(ForgeConfigSpec.Builder builder) {

            builder.push("Common configs");

            REPAIR_ENCHANT = builder.comment("Set this to false to disable the repair of the item enchanted with Indestructible")
                    .define("Repair when enchanted:",true);

            ALLOW_ON_BOOKS = builder.comment("Set this to true to enable the enchantment on books").define("Allowed on books:", true);
            ALLOW_TREASURE = builder.comment("Set this to true to enable finding the enchantment on loot chests").define("Allowed in chest:", false);
            ALLOW_TRADING = builder.comment("Set this to true to enable finding the enchantment in villager trades").define("Allowed in villager trades:", false);
            builder.pop();

        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
