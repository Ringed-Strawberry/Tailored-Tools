package ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability;

import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ToolAbilities {
    public static HashMap<Identifier, ToolAbility> toolAbilityList = new HashMap<>();

    public static ToolAbility parse(String string){
        return toolAbilityList.get(Identifier.of(string));
    }
}
