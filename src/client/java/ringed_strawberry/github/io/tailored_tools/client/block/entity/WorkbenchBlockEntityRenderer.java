package ringed_strawberry.github.io.tailored_tools.client.block.entity;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.tailored_tools.block.entity.custom.WorkbenchBlockEntity;

public class WorkbenchBlockEntityRenderer implements BlockEntityRenderer<WorkbenchBlockEntity, WorkbenchBlockEntityRenderState> {

    private final ItemModelManager itemModelManager;

    public WorkbenchBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemModelManager = context.itemModelManager();
    }

    @Override
    public WorkbenchBlockEntityRenderState createRenderState() {
        return new WorkbenchBlockEntityRenderState();
    }

    @Override
    public void render(WorkbenchBlockEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        if(!state.itemRenderState.isEmpty()){
            matrices.push();
            matrices.translate(0,1,0);
            state.itemRenderState.render(matrices, queue, state.lightmapCoordinates, OverlayTexture.DEFAULT_UV, 0);
            matrices.pop();
        }
    }

    @Override
    public void updateRenderState(WorkbenchBlockEntity blockEntity, WorkbenchBlockEntityRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        for (int i = 0; i < 8; i++) {
            this.itemModelManager.clearAndUpdate(state.itemRenderState, blockEntity.getStack(i), ItemDisplayContext.GUI, blockEntity.getWorld(), (HeldItemContext)null, 0);
        }
    }
}
