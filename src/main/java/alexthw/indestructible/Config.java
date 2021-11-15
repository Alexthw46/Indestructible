package alexthw.indestructible;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static class Common {

        public final ForgeConfigSpec.ConfigValue<Boolean> REPAIR_ENCHANT;

        public Common(ForgeConfigSpec.Builder builder) {

            builder.push("Common configs");

            REPAIR_ENCHANT = builder.comment("Set this to false to disable the repair of the item enchanted with Indestructible")
                    .define("Repair when enchanted:",true);

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
