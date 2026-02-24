package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

public class SpammerAbility extends ToolAbility {
    public SpammerAbility(Identifier id) {
        super(id);
    }

    @Override
    public void onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos) {
        user.sendMessage(Text.of("SPAMMM >:D"), false);
        super.onBlockBreak(user, world, minedState, pos);
    }
}
