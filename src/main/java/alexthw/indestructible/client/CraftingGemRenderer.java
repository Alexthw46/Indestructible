package alexthw.indestructible.client;

import alexthw.indestructible.common.CraftingBlockEntity;
import alexthw.indestructible.init.Registry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CraftingGemRenderer implements BlockEntityRenderer<CraftingBlockEntity, StackRenderState> {
    static ItemStack gem; //cached
    private final ItemModelResolver itemModelResolver;

    public CraftingGemRenderer(BlockEntityRendererProvider.Context context) {
        gem = Registry.INDESTRUCTIBLE_GEM.get().getDefaultInstance();
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(CraftingBlockEntity blockEntity, StackRenderState renderState, float partialTick, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        renderState.stack = blockEntity.stack;
        renderState.level = blockEntity.getLevel();
        renderState.pos = blockEntity.getBlockPos();
        itemModelResolver.updateForTopItem(renderState.gemRenderState, gem, ItemDisplayContext.GROUND, renderState.level, null, 0);
        itemModelResolver.updateForTopItem(renderState.stackRenderState, renderState.stack, ItemDisplayContext.GROUND, renderState.level, null, 0);
    }

    @Override
    public void submit(StackRenderState renderState, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector nodeCollector, @NonNull CameraRenderState cameraRenderState) {

        Level level = renderState.level;
        if (level == null) return;

        // draw the spinning gem
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(
                3 * (level.getGameTime() % 360)));
        renderState.gemRenderState.submit(poseStack, nodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();

        // draw blockEntity.stack if present
        ItemStack stack = renderState.stack;
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0.1, 0.5);
            poseStack.mulPose(Axis.XP.rotationDegrees(90));

            renderState.stackRenderState.submit(poseStack, nodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
        }

        // particles remain the same
        if (level.getGameTime() % 20 == 0) {
            BlockPos pos = renderState.pos;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    if (level.random.nextInt(5) == 0) {
                        BlockPos off = pos.offset(i, 0, j);
                        level.addParticle(ParticleTypes.ENCHANT,
                                off.getX() + 0.5 - i,
                                off.getY() + 0.6,
                                off.getZ() + 0.5 - j,
                                i / 6f, 0, j / 6f);
                    }
                }
            }
        }
    }

    @Override
    public StackRenderState createRenderState() {
        return new StackRenderState();
    }

}
