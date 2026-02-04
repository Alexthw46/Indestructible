package alexthw.indestructible.common;

import alexthw.indestructible.init.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class Gem extends Item {
    public Gem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> tooltipAdder, @NonNull TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable("item.indestructible.gem.tooltip"));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        Block block = Registry.GEM_BLOCK.get();

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.PASS;
        }

        if (!level.getBlockState(blockpos).is(Blocks.ANVIL) || !level.isEmptyBlock(blockpos.above())) { // use isEmptyBlock for air check
            return super.useOn(context);
        }else{
            blockpos = blockpos.above();
            level.setBlockAndUpdate(blockpos, block.defaultBlockState());

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
