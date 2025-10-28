package ringed_strawberry.github.io.tailored_tools.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.spacelib.block.util.BlockHitUtil;
import ringed_strawberry.github.io.tailored_tools.block.entity.custom.WorkbenchBlockEntity;
import ringed_strawberry.github.io.tailored_tools.block.entity.inventory.WorkbenchUtil;

public class WorkbenchBlock extends BlockWithEntity {
    public WorkbenchBlock(Settings settings) {
        super(settings);
    }

    public static final MapCodec<WorkbenchBlock> CODEC = WorkbenchBlock.createCodec(WorkbenchBlock::new);

    public static void handlePlayerLook(BlockHitResult blockHit, World world) {
        if(BlockHitUtil.isInteractionInRange(blockHit, 0.42f, 0.28f, 2)){
            world.addImportantParticleClient(ParticleTypes.HAPPY_VILLAGER, true, blockHit.getPos().getX(), blockHit.getPos().getY(), blockHit.getPos().getZ(), 0, 0, 0);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof WorkbenchBlockEntity blockEntity && !world.isClient()) {
            if(!player.getStackInHand(player.getActiveHand()).isEmpty()) {
                blockEntity.setStack(WorkbenchUtil.getHandleSlot(hit), player.getStackInHand(player.getActiveHand()).copyWithCount(1));
                player.getStackInHand(player.getActiveHand()).decrementUnlessCreative(1, player);
                return ActionResult.CONSUME;
            } else if (!blockEntity.getStack(WorkbenchUtil.getHandleSlot(hit)).isEmpty()) {
                player.giveOrDropStack(blockEntity.getStack(WorkbenchUtil.getHandleSlot(hit)));
                blockEntity.setStack(WorkbenchUtil.getHandleSlot(hit), ItemStack.EMPTY);
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }



    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WorkbenchBlockEntity(pos, state);
    }
}
