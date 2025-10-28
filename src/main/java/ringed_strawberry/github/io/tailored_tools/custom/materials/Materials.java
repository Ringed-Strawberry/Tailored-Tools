package ringed_strawberry.github.io.tailored_tools.custom.materials;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;

import java.util.HashMap;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;

public class Materials {
    public static HashMap<Identifier, Material> materialList = new HashMap<>();

    public static Material parse(JsonObject json){
        Identifier materialId = Identifier.of(JsonHelper.getString(json,"id"));
        LOGGER.info(materialId.toString());

        ToolHeadStats toolHeadStats = new ToolHeadStats(
                JsonHelper.getDouble(json, "toolhead.speed",0.0),
                JsonHelper.getFloat(json, "toolhead.damage",0),
                JsonHelper.getInt(json,  "toolhead.breaking_level",0),
                JsonHelper.getInt(json, "toolhead.durability",0)

        );

        HiltStats hiltStats = new HiltStats(JsonHelper.getDouble(json,"hilt.speed"));

        RodStats rodStats = new RodStats(
                JsonHelper.getInt(json,"rod.durability"),
                JsonHelper.getFloat(json,"rod.speed")
        );

        BindingStats bindingStats = new BindingStats(
                JsonHelper.getInt(json,"binding.durability"),
                JsonHelper.getFloat(json,"binding.speed")
        );
        // Watch me try to guess how to create an item and translation key based on the fabric wiki and
        // Intellj autosuggesiton( please fix it)
        Item item = Registries.ITEM.get(materialId);

        return new Material(
                materialId,
                item,
                hiltStats,
                toolHeadStats,
                bindingStats,
                rodStats
        );
    }
}
