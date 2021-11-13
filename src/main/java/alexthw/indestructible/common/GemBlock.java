package alexthw.indestructible.common;

import alexthw.indestructible.init.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class GemBlock extends BaseEntityBlock {
    public GemBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CraftingBlockEntity(pos,state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level level, BlockPos pos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof CraftingBlockEntity){
            ((CraftingBlockEntity) te).onActivated(pState,pos,pPlayer,pHand);
        }
        return super.use(pState, level, pos, pPlayer, pHand, pHit);
    }
    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        breakBlock(state, world, pos);
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        breakBlock(state, world, pos);
        super.onBlockExploded(state, world, pos, explosion);
    }

    public void breakBlock(BlockState state, BlockGetter level, BlockPos pos) {
        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof CraftingBlockEntity){
            ((CraftingBlockEntity) te).onDestroyed(state, pos);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        return Collections.singletonList(new ItemStack(Registry.INDESTRUCTIBLE_GEM.get(), 1));
    }

}
