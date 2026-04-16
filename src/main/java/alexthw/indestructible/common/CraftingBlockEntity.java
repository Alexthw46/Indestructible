package alexthw.indestructible.common;

import alexthw.indestructible.Config;
import alexthw.indestructible.init.ModTag;
import alexthw.indestructible.init.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static alexthw.indestructible.init.ModTag.Items.BLACKLIST;

public class CraftingBlockEntity extends BlockEntity {
    public ItemStack stack = ItemStack.EMPTY;

    public CraftingBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Registry.CRAFTING_BLOCK.get(), pWorldPosition, pBlockState);
    }

    public void onDestroyed(BlockState state, BlockPos pos) {
        if (!stack.isEmpty() && level != null)
            Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
    }


    public InteractionResult onActivated(BlockState state, BlockPos pos, Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && level != null) {

            ItemStack item = player.getItemInHand(hand);

            if (item.is(ModTag.Items.CATALYST) && !stack.isEmpty()) {
                item.shrink(1);
                stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
                if (Config.COMMON.REPAIR_ENCHANT.get()) stack.setDamageValue(0);
                onDestroyed(state, pos);
                if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                else {
                    level.addParticle(ParticleTypes.EXPLOSION, worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5, 0, 0, 0);
                    this.level.playLocalSound(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.getRandom().nextFloat() - this.level.getRandom().nextFloat()) * 0.2F) * 0.7F, false);
                }
                level.removeBlock(pos, false);
                return InteractionResult.SUCCESS;
            } else if (item.isEmpty() && !stack.isEmpty()) {
                player.addItem(stack);
                stack = ItemStack.EMPTY;
                if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                return InteractionResult.SUCCESS;
            } else if (!item.isEmpty() && stack.isEmpty()) {
                if (item.isDamageableItem() && !item.is(BLACKLIST)) {
                    stack = item.copy();
                    stack.setCount(1);
                    item.shrink(1);
                    if (item.isEmpty()) player.setItemInHand(hand, ItemStack.EMPTY);
                    if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.store("stack", ItemStack.CODEC, stack);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        stack = input.read("stack", ItemStack.CODEC).orElse(ItemStack.EMPTY);
    }

    @Override
    public void onDataPacket(@NonNull Connection net, @NonNull ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
        handleUpdateTag(valueInput);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public BlockPos getWorldPosition() {
        return this.worldPosition;
    }

}
