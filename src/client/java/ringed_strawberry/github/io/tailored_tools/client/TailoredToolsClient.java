package ringed_strawberry.github.io.tailored_tools.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import ringed_strawberry.github.io.spacelib.block.util.BlockHitUtil;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.block.entity.ModBlockEntities;
import ringed_strawberry.github.io.tailored_tools.client.block.entity.WorkbenchBlockEntityRenderer;

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
                    if(BlockHitUtil.isInteractionInRange(blockHit, 0.42f, 0.28f, 2)){
                        client.world.addImportantParticleClient(ParticleTypes.HAPPY_VILLAGER, true, blockHit.getPos().getX(), blockHit.getPos().getY(), blockHit.getPos().getZ(), 0, 0, 0);
                    }
                }
            }
        });


        BlockEntityRendererFactories.register(ModBlockEntities.WORKBENCH, WorkbenchBlockEntityRenderer::new);
    }
}
