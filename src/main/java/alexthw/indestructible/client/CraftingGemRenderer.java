package alexthw.indestructible.client;

import alexthw.indestructible.common.CraftingBlockEntity;
import alexthw.indestructible.init.Registry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class CraftingGemRenderer implements BlockEntityRenderer<CraftingBlockEntity> {
    ItemStack gem;

    public CraftingGemRenderer(BlockEntityRendererProvider.Context context) {
        gem = new ItemStack(Registry.INDESTRUCTIBLE_GEM.get(),1);
    }

    @Override
    public void render(CraftingBlockEntity pBlockEntity, float partialTicks, PoseStack matrixStackIn, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        Minecraft mc = Minecraft.getInstance();
        ItemRenderer ir = mc.getItemRenderer();

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5,0.7, 0.5);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(3 * (mc.level.getGameTime() % 360 + partialTicks)));
        ir.renderStatic(gem, ItemTransforms.TransformType.GROUND,pPackedLight,pPackedOverlay,matrixStackIn,pBufferSource,1);
        matrixStackIn.popPose();

        if (!pBlockEntity.stack.isEmpty() && mc.level != null) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5,0.1, 0.5);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90));
            ir.renderStatic(pBlockEntity.stack, ItemTransforms.TransformType.GROUND,pPackedLight,pPackedOverlay,matrixStackIn,pBufferSource,1);
            matrixStackIn.popPose();
        }
    }
}
