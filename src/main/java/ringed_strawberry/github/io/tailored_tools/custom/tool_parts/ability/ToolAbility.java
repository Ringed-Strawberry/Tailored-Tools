package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolAbility {

    public ToolAbility(Identifier id) {
        ToolAbilities.toolAbilityList.put(id, this);
    }

    public void onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos){

    }
}
