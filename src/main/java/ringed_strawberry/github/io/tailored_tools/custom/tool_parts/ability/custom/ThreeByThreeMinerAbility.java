package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ThreeByThreeMinerAbility extends ToolAbility {
    public ThreeByThreeMinerAbility() {
        super(Identifier.of(MOD_ID, "3x3" + "_ability"));
    }

    @Override
    public int onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos, ItemStack stack) {
        int durabilityUsed = 0;
        if(!user.isSneaking()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    for (int k = -1; k < 2; k++) {
                        world.breakBlock(pos.add(i, j, k), true, user);
                        durabilityUsed++;
                    }
                }
            }
        } else {
            durabilityUsed++;
        }
        super.onBlockBreak(user, world, minedState, pos, stack);
        return durabilityUsed;
    }
}
