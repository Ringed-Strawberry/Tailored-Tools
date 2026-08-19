package ringed_strawberry.github.io.tailored_tools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.block.custom.WorkbenchBlock;
import ringed_strawberry.github.io.tailored_tools.block.entity.ModBlockEntities;
import ringed_strawberry.github.io.tailored_tools.client.block.entity.WorkbenchBlockEntityRenderer;
import ringed_strawberry.github.io.tailored_tools.item.ModItems;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

public class TailoredToolsClient implements ClientModInitializer {
    public final static String MOD_ID = "tailored_tools";
    MinecraftClient client = MinecraftClient.getInstance();


    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((t) -> {
            HitResult hit = client.crosshairTarget;
            if(hit != null && hit.getType() == HitResult.Type.BLOCK){
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos blockPos = blockHit.getBlockPos();
                if(client.world != null) {
                    BlockState blockState = client.world.getBlockState(blockPos);
                    Block block = blockState.getBlock();
                    if (block == ModBlocks.WORKBENCH) {
                        WorkbenchBlock.handlePlayerLook(blockHit, client.world, client.player);
                    }
                }
            }
        });



        BlockEntityRendererFactories.register(ModBlockEntities.WORKBENCH, WorkbenchBlockEntityRenderer::new);


        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if(ToolUtil.getToolTint(stack, tintIndex) != 0) {
                return ToolUtil.getToolTint(stack, tintIndex);
            }
            return 0;
        }, ModItems.TAILORED_TOOL);

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

            //Speed
            list.add(
                    Text.translatable("tooltips.tailored_tools.speed")
                            .append(Text.of(": " + ToolUtil.getToolSpeed(stack)))
            );

            //Tool Part List
            list.add(Text.translatable("tooltips.tailored_tools.tool_list").append(Text.of(":")));
            //Head
            list.add(Text.of(" - ").copy()
                    .append(Text.translatable("tooltips.tailored_tools.tool_head")
                            .append(Text.of(": "))
                            .append(Text.of(ToolUtil.getMaterialNameStyled(stack, "head", true)))
                    ));

            if (client.options.sneakKey.isPressed()) {
                list.addAll(ToolUtil.getStyledMaterialStats(stack, "head"));
            }

            //Binding
            list.add(Text.of(" - ").copy()
                    .append(Text.translatable("tooltips.tailored_tools.tool_binding")
                            .append(Text.of(": "))
                            .append(Text.of(ToolUtil.getMaterialNameStyled(stack, "binding", true)))
                    ));

            if (client.options.sneakKey.isPressed()) {
                list.addAll(ToolUtil.getStyledMaterialStats(stack, "binding"));
            }

            //Rod
            list.add(Text.of(" - ").copy()
                    .append(Text.translatable("tooltips.tailored_tools.tool_rod")
                            .append(Text.of(": "))
                            .append(Text.of(ToolUtil.getMaterialNameStyled(stack, "rod", true)))
                    ));

            if (client.options.sneakKey.isPressed()) {
                list.addAll(ToolUtil.getStyledMaterialStats(stack, "rod"));
            }
        });
    }
}