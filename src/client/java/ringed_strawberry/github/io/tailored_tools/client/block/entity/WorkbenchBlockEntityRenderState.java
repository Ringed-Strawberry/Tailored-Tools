package ringed_strawberry.github.io.tailored_tools.client.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;

@Environment(EnvType.CLIENT)
public class WorkbenchBlockEntityRenderState  extends BlockEntityRenderState {
    public ItemRenderState itemRenderState = new ItemRenderState();
}
