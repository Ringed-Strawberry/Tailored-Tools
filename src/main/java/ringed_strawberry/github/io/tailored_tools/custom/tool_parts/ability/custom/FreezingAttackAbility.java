package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

import java.util.ArrayList;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class FreezingAttackAbility extends ToolAbility {
    public FreezingAttackAbility() {
        super(Identifier.of(MOD_ID, "freezing_attack" + "_ability"));
    }

    @Override
    public void onAttack(PlayerEntity user, LivingEntity target, ItemStack stack) {
        target.setPos(
                target.getBlockPos().toCenterPos().getX(),
                target.getBlockPos().toCenterPos().getY(),
                target.getBlockPos().toCenterPos().getZ()
        );
        ArrayList<BlockPos> blockPositions= new ArrayList<>();
        Box box = target.getDimensions(target.getPose()).getBoxAt(0,0,0);
        for (int i = ((int) box.minX); i <= (int) Math.ceil(box.maxX); i++) {
            for (int j = ((int) box.minY); j <= (int) Math.ceil(box.maxY); j++) {
                for (int k = ((int) box.minZ); k <= (int) Math.ceil(box.maxZ); k++) {
                    blockPositions.add(target.getBlockPos().add(i, j, k));
                }
            }
        }
        for (BlockPos blockPos : blockPositions) {
            user.getWorld().setBlockState(blockPos, Blocks.ICE.getDefaultState());
        }
        target.setFrozenTicks(400);
        target.setMovementSpeed(0);
        super.onAttack(user, target, stack);
    }
}