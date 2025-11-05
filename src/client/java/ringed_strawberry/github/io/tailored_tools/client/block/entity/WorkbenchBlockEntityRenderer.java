package ringed_strawberry.github.io.tailored_tools.client.block.entity;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.text.Text;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.tailored_tools.block.entity.custom.WorkbenchBlockEntity;

public class WorkbenchBlockEntityRenderer implements BlockEntityRenderer<WorkbenchBlockEntity, WorkbenchBlockEntityRenderState> {

    private final ItemModelManager itemModelManager;
    private final TextRenderer textRenderer;
    private int activeHandle = 0;

    public WorkbenchBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.itemModelManager = context.itemModelManager();
        this.textRenderer = context.textRenderer();
    }

    @Override
    public WorkbenchBlockEntityRenderState createRenderState() {
        return new WorkbenchBlockEntityRenderState();
    }

    @Override
    public void render(WorkbenchBlockEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
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
        matrices.push();
        String unfocused = "▫";
        float unfocusedWidth = textRenderer.getWidth(unfocused);

        String focused = "□";
        float focusedWidth = textRenderer.getWidth(unfocused);

        queue.submitText(
                matrices,
                -unfocusedWidth / 2, -4f,
                Text.literal(unfocused).asOrderedText(),
                false,
                TextRenderer.TextLayerType.SEE_THROUGH,
                state.lightmapCoordinates,
                0xffffffff,
                0,
                0
        );
    }

    @Override
    public void updateRenderState(WorkbenchBlockEntity blockEntity, WorkbenchBlockEntityRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        for (int i = 0; i < 8; i++) {
            this.itemModelManager.clearAndUpdate(state.itemRenderState, blockEntity.getStack(i), ItemDisplayContext.GUI, blockEntity.getWorld(), (HeldItemContext)null, 0);
        }
        this.activeHandle = blockEntity.activeHandleSlot;
    }
}
