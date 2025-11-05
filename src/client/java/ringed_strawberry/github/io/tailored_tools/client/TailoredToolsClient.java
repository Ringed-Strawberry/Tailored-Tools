package ringed_strawberry.github.io.tailored_tools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.block.custom.WorkbenchBlock;
import ringed_strawberry.github.io.tailored_tools.block.entity.ModBlockEntities;
import ringed_strawberry.github.io.tailored_tools.client.block.entity.WorkbenchBlockEntityRenderer;
import ringed_strawberry.github.io.tailored_tools.client.color.tint.ToolTintSource;
import ringed_strawberry.github.io.tailored_tools.item.ModItems;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.util.ColorUtil;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class TailoredToolsClient implements ClientModInitializer {
    MinecraftClient client = MinecraftClient.getInstance();


    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((t) -> {
            HitResult hit = client.crosshairTarget;
            if(hit != null && hit.getType() == HitResult.Type.BLOCK){
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos blockPos = blockHit.getBlockPos();
                BlockState blockState = client.world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                if(block == ModBlocks.WORKBENCH){
                    WorkbenchBlock.handlePlayerLook(blockHit, client.world);
                }
            }
        });



        BlockEntityRendererFactories.register(ModBlockEntities.WORKBENCH, WorkbenchBlockEntityRenderer::new);

        TintSourceTypes.ID_MAPPER.put(Identifier.of(MOD_ID, "tool"), ToolTintSource.CODEC);









        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, list) -> {
            if (!stack.isOf(ModItems.TAILORED_TOOL)) {
                return;
            }
            //Durability
            list.add(
                    Text.translatable("tooltips.tailored_tools.durability")
                            .append(Text.of(": " + stack.getOrDefault(ModItemComponents.DURABILITY, 0)
                                    + "/" + stack.getOrDefault(ModItemComponents.MAX_DURABILITY, 0)))
            );
            //Damage
            list.add(
                    Text.translatable("tooltips.tailored_tools.damage")
                            .append(Text.of(": " + ToolUtil.getAttackDamage(stack)))
            );

            //Materials
            list.add(Text.of(""));
            list.add(Text.translatable("tooltips.tailored_tools.materials")
                    .append(Text.of(": "))
            );
            list.add(
                    ColorUtil.materialTooltip(stack, "head")
            );
            list.add(
                    ColorUtil.materialTooltip(stack, "binding")
            );
            list.add(
                    ColorUtil.materialTooltip(stack, "hilt")
            );
            list.add(
                    ColorUtil.materialTooltip(stack, "rod")
            );
        });
    }
}
