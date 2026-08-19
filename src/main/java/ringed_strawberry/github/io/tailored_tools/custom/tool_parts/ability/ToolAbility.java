package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.TailoredTools;

public class ToolAbility {

    public ToolAbility(Identifier id) {
        ToolAbilities.toolAbilityList.put(id, this);
        TailoredTools.LOGGER.info("added new ability: " + id);
    }

    public int onBlockBreak(PlayerEntity user, World world, BlockState minedState, BlockPos pos, ItemStack stack){
        return 1;
    }

    public void onAttack(PlayerEntity user, LivingEntity target, ItemStack stack){

    }

}
