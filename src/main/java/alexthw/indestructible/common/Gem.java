package alexthw.indestructible.common;

import alexthw.indestructible.init.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Gem extends Item {
    public Gem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        Block block = Registry.GEM_BLOCK.get();

        if (level.isClientSide) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.PASS;
        }

        if (!level.getBlockState(blockpos).is(Blocks.ANVIL) || !level.getBlockState(blockpos.above()).isAir()) {
            return super.onItemUseFirst(stack, context);
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
