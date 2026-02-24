package ringed_strawberry.github.io.tailored_tools.custom.materials;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.util.ColorUtil;

import java.util.HashMap;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;

public class Materials {
    public static HashMap<Identifier, Material> materialList = new HashMap<>();

    public static Material parse(JsonObject json){
        Identifier materialId = Identifier.of(JsonHelper.getString(json,"id", "tailored_tools:error"));
        LOGGER.info(materialId.toString());
        double speed = JsonHelper.getDouble(json, "speed",0.0);
        float damage = JsonHelper.getFloat(json, "damage",0);
        int breakingPower = JsonHelper.getInt(json,  "breaking_level",0);
        int durability = JsonHelper.getInt(json, "durability",0);

        // Watch me try to guess how to create an item and translation key based on the fabric wiki and
        // Intellj autosuggesiton( please fix it)
        Item item = Registries.ITEM.get(materialId);

        int color = ColorUtil.hexToInt(JsonHelper.getString(json,"color", "000000"));

        return new Material(
                materialId,
                item,
                speed,
                damage,
                breakingPower,
                durability,
                color
        );
    }
}
