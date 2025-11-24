package ringed_strawberry.github.io.tailored_tools.client.block.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.block.entity.custom.WorkbenchBlockEntity;
import ringed_strawberry.github.io.tailored_tools.util.WorkbenchUtil;

public class WorkbenchBlockEntityRenderer implements BlockEntityRenderer<WorkbenchBlockEntity>{

    private final TextRenderer textRenderer;
    private int activeHandle = 0;
    String unfocused = "▢";
    float unfocusedWidth;
    float textScale = 48;

    String focused = "▣";
    float focusedWidth;

    public WorkbenchBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.textRenderer = context.getTextRenderer();
        this.unfocusedWidth = textRenderer.getWidth(unfocused);
        this.focusedWidth = textRenderer.getWidth(focused);
    }

    @Override
    public void render(WorkbenchBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        //        if(!state.itemRenderState.isEmpty()){
//            matrices.push();
//            matrices.translate(0,1,0);
//            queue.submitItem(matrices, ItemDisplayContext.FIXED, state.lightmapCoordinates,
//                    OverlayTexture.DEFAULT_UV,
//                    0, new int[0], null,
//                    RenderLayers.getItemLayer(Items.DIAMOND_BLOCK.getDefaultStack()), ItemRenderState.Glint.NONE);
//            state.itemRenderState.render(matrices, queue, state.lightmapCoordinates, OverlayTexture.DEFAULT_UV, 0);
//            matrices.pop();
//        }
        if (entity != null && entity.getWorld().getBlockState(entity.getPos()).isOf(ModBlocks.WORKBENCH)) {
            String focusChecked;
            float focusCheckedWidth;
            for (int i = 1; i <= 8; i++) {
                if (entity.activeHandleSlot == i) {
                    focusChecked = focused;
                    focusCheckedWidth = focusedWidth;
                } else {
                    focusChecked = unfocused;
                    focusCheckedWidth = unfocusedWidth;
                }
                matrices.push();
                matrices.translate(WorkbenchUtil.getHandleTextPosition3D(i).x, WorkbenchUtil.getHandleTextPosition3D(i).y, WorkbenchUtil.getHandleTextPosition3D(i).z);
                matrices.scale(1 / textScale, 1 / textScale, 1 / textScale);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(WorkbenchUtil.getTextRotation(i)));

                if (!entity.getStack(i).isEmpty()) {
                    int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
                    MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(i), ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
                } else {
                    textRenderer.draw(
                            focusChecked,
                            -focusCheckedWidth / 2,
                            -4f,
                            0xffffff,
                            false,
                            matrices.peek().getPositionMatrix(),
                            vertexConsumers,
                            TextRenderer.TextLayerType.SEE_THROUGH,
                            0,
                            light
                    );
                    matrices.pop();
                }
            }
        }
    }
}
