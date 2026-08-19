package ringed_strawberry.github.io.tailored_tools.custom.tool_parts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbilities;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ability.ToolAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;
import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;
import static ringed_strawberry.github.io.tailored_tools.util.ToolUtil.toolNameToIndex;

public class ToolParts {
    public static final ToolPart ERROR = new ToolPart(Identifier.of(MOD_ID, "error"), 0, (byte) 0,0, 0, 0, null, null);
    public static HashMap<Identifier, ToolPart> toolPartList = new HashMap<>(Map.of(Identifier.of(MOD_ID, "error"), ERROR));

    public static ToolPart parse(JsonObject json){
        Identifier toolPartId = Identifier.of(JsonHelper.getString(json,"id", "tailored_tools:error"));
        LOGGER.info(toolPartId.toString());
        int materialCost = JsonHelper.getInt(json, "cost", 1);
        double durabilityMultiplier = JsonHelper.getDouble(json, "multiplier.durability", 1);
        double speedMultiplier = JsonHelper.getDouble(json, "multiplier.speed", 1);
        double damageMultiplier = JsonHelper.getDouble(json, "multiplier.damage", 1);

        byte slot = toolNameToIndex.get(JsonHelper.getString(json, "slot", "error")).byteValue();
        ArrayList<ToolAbility> abilities = new ArrayList<>();
        JsonArray abilitiesJsonArray = JsonHelper.getArray(json, "abilities", new JsonArray());

        for (JsonElement element : abilitiesJsonArray) {
            ToolAbility ability = ToolAbilities.parse(element.getAsString());
            abilities.add(ability);
        }

        if(slot == 0){

        }

        ArrayList<TagKey<Block>> breakableTags = new ArrayList<>();
        JsonArray breakableTagsJsonArray = JsonHelper.getArray(json, "can.break", new JsonArray());

        for (JsonElement element : breakableTagsJsonArray) {
            TagKey<Block> tag = TagKey.of(RegistryKeys.BLOCK, Identifier.of(element.getAsString()));
            breakableTags.add(tag);
        }
        return new ToolPart(
                toolPartId,
                materialCost,
                slot,
                durabilityMultiplier,
                speedMultiplier,
                damageMultiplier,
                abilities,
                breakableTags
        );
    }
}
