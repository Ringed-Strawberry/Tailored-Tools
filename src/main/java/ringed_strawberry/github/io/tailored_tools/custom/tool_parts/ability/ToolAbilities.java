package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability;

import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom.FreezingAttackAbility;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom.SpammerAbility;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.custom.ThreeByThreeMinerAbility;

import java.util.HashMap;

public class ToolAbilities {
    public static HashMap<Identifier, ToolAbility> toolAbilityList = new HashMap<>();

    public static void registerToolAbilities(){
        new SpammerAbility();
        new ThreeByThreeMinerAbility();

        new FreezingAttackAbility();
    }

    public static ToolAbility parse(String string){
        return toolAbilityList.get(Identifier.of(string));
    }

    public static ToolAbility parse(Identifier id){
        return toolAbilityList.get(id);
    }
}
