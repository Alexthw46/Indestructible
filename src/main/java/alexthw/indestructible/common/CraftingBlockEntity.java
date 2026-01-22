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
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

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


    public ItemInteractionResult onActivated(BlockState state, BlockPos pos, Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && level != null) {

            ItemStack item = player.getItemInHand(hand);

            if (item.is(ModTag.Items.CATALYST) && !stack.isEmpty()) {
                item.shrink(1);
                stack.set(DataComponents.UNBREAKABLE, new Unbreakable(true));
                if (Config.COMMON.REPAIR_ENCHANT.get()) stack.setDamageValue(0);
                onDestroyed(state, pos);
                if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                else {
                    level.addParticle(ParticleTypes.EXPLOSION, worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5, 0, 0, 0);
                    this.level.playLocalSound(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
                }
                level.removeBlock(pos, false);
                return ItemInteractionResult.SUCCESS;
            } else if (item.isEmpty() && !stack.isEmpty()) {
                player.addItem(stack);
                stack = ItemStack.EMPTY;
                if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                return ItemInteractionResult.SUCCESS;
            } else if (!item.isEmpty() && stack.isEmpty()) {
                if (item.isDamageableItem() && !item.is(BLACKLIST)) {
                    stack = item.copy();
                    stack.setCount(1);
                    item.shrink(1);
                    if (item.isEmpty()) player.setItemInHand(hand, ItemStack.EMPTY);
                    if (!level.isClientSide()) level.sendBlockUpdated(pos, state, state, 2);
                    return ItemInteractionResult.CONSUME;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.stack = ItemStack.parseOptional(registries, tag.getCompound("stack"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("stack", stack.saveOptional(registries));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        handleUpdateTag(pkt.getTag(), lookupProvider);
    }

    public BlockPos getWorldPosition() {
        return this.worldPosition;
    }

}
