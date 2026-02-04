package alexthw.indestructible.client;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StackRenderState extends BlockEntityRenderState {
    public ItemStack stack;
    public Level level;
    public BlockPos pos;
    public ItemStackRenderState stackRenderState = new ItemStackRenderState();
    public ItemStackRenderState gemRenderState = new ItemStackRenderState();
}
