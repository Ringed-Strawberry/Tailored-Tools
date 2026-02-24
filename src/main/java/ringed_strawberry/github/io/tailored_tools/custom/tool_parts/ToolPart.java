package ringed_strawberry.github.io.tailored_tools.custom.tool_parts;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

import java.util.ArrayList;

public record ToolPart(
        Identifier id,
        int cost,
        byte toolSlot,
        double durabilityMultiplier,
        double speedMultiplier,
        double damageMultiplier,
        ArrayList<ToolAbility> abilities,
        ArrayList<TagKey<Block>> blockBreakingTags
){
}
