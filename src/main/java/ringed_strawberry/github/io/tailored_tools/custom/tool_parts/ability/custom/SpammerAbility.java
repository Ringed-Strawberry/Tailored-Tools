package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class SpammerAbility extends ToolAbility {
    public SpammerAbility() {
        super(Identifier.of(MOD_ID, "spammer" + "_ability"));
    }

    @Override
    public int onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos, ItemStack stack) {
        user.sendMessage(Text.of("SPAMMM >:P"), false);
        return super.onBlockBreak(user, world, minedState, pos, stack);
    }

    @Override
    public void onAttack(PlayerEntity user, LivingEntity target, ItemStack stack) {
        user.sendMessage(Text.of("SPAMMM >:D"), false);
        target.sendMessage(Text.of("SPAMMM >:D"));
        super.onAttack(user, target, stack);
    }
}
