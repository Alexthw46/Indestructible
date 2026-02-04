package alexthw.indestructible.common;

import alexthw.indestructible.init.Registry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;
import java.util.List;

public class GemBlock extends BaseEntityBlock {
    public GemBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<GemBlock> CODEC = simpleCodec(GemBlock::new);

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CraftingBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof CraftingBlockEntity cbe) {
            return cbe.onActivated(state, pos, player, hand);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @NotNull BlockState playerWillDestroy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        breakBlock(state, world, pos);
        return super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void onBlockExploded(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull Explosion explosion) {
        breakBlock(state, level, pos);
        super.onBlockExploded(state, level, pos, explosion);
    }

    public void breakBlock(BlockState state, BlockGetter level, BlockPos pos) {
        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof CraftingBlockEntity) {
            ((CraftingBlockEntity) te).onDestroyed(state, pos);
        }
    }

    @Override
    protected @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder params) {
        return List.of(Registry.INDESTRUCTIBLE_GEM.get().getDefaultInstance());
    }

}
