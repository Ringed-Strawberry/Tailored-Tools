package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

public class ThreeByThreeMinerAbility extends ToolAbility {
    public ThreeByThreeMinerAbility(Identifier id) {
        super(id);
    }

    @Override
    public void onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos) {
        for (int i = -1; i < 2; i++) {
            if(world.canPlayerModifyAt(user, pos.add(user.getFacing().getOffsetX()*i,user.getFacing().getOffsetY()*i,user.getFacing().getOffsetZ()*i)))
                world.breakBlock(pos.add(user.getFacing().getOffsetX()*i,user.getFacing().getOffsetY()*i,user.getFacing().getOffsetZ()*i), true, user);
        }
        super.onBlockBreak(user, world, minedState, pos);
    }
}
