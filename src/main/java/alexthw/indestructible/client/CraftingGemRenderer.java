package alexthw.indestructible.client;

import alexthw.indestructible.common.CraftingBlockEntity;
import alexthw.indestructible.init.Registry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CraftingGemRenderer implements BlockEntityRenderer<CraftingBlockEntity> {
    ItemStack gem; //cache

    public CraftingGemRenderer(BlockEntityRendererProvider.Context context) {
        gem = Registry.INDESTRUCTIBLE_GEM.get().getDefaultInstance();
    }

    @Override
    public void render(@NotNull CraftingBlockEntity blockEntity, float partialTicks, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        Minecraft mc = Minecraft.getInstance();
        ItemRenderer ir = mc.getItemRenderer();

        if (mc.level != null) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 0.5, 0.5);
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(3 * (mc.level.getGameTime() % 360 + partialTicks)));
            ir.renderStatic(gem, ItemDisplayContext.GROUND, pPackedLight, pPackedOverlay, matrixStackIn, pBufferSource, mc.level, 1);
            matrixStackIn.popPose();

            if (!blockEntity.stack.isEmpty()) {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5, 0.1, 0.5);
                matrixStackIn.mulPose(Axis.XP.rotationDegrees(90));
                ir.renderStatic(blockEntity.stack, ItemDisplayContext.GROUND, pPackedLight, pPackedOverlay, matrixStackIn, pBufferSource, mc.level, 1);
                matrixStackIn.popPose();

                BlockPos worldPosition = blockEntity.getWorldPosition();

                if (mc.level.getGameTime() % 20 == 0) {
                    for (int i = -1; i <= 1; ++i) {
                        for (int j = -1; j <= 1; ++j) {
                            if (i == 0 && j == 0) continue;
                            BlockPos blockpos = worldPosition.offset(i, 0, j);
                            if (mc.level.random.nextInt() % 5 == 0)
                                mc.level.addParticle(ParticleTypes.ENCHANT, blockpos.getX() + 0.5 - i, blockpos.getY() + 0.6, blockpos.getZ() + 0.5 - j, i / 6f, 0, j / 6f);
                        }
                    }
                }
            }
        }
    }
}
