package ringed_strawberry.github.io.tailored_tools.custom.materials;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;
import ringed_strawberry.github.io.tailored_tools.util.ColorUtil;

import java.util.HashMap;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;

public class Materials {
    public static HashMap<Identifier, Material> materialList = new HashMap<>();

    public static Material parse(JsonObject json){
        Identifier materialId = Identifier.of(JsonHelper.getString(json,"id", "tailored_tools:error"));
        LOGGER.info(materialId.toString());

        ToolHeadStats toolHeadStats = new ToolHeadStats(
                JsonHelper.getDouble(json, "toolhead.speed",0.0),
                JsonHelper.getFloat(json, "toolhead.damage",0),
                JsonHelper.getInt(json,  "toolhead.breaking_level",0),
                JsonHelper.getInt(json, "toolhead.durability",0)

        );

        HiltStats hiltStats = new HiltStats(JsonHelper.getDouble(json,"hilt.speed", 0));

        RodStats rodStats = new RodStats(
                JsonHelper.getInt(json,"rod.durability", 0),
                JsonHelper.getFloat(json,"rod.speed", 0)
        );

        BindingStats bindingStats = new BindingStats(
                JsonHelper.getInt(json,"binding.durability", 0),
                JsonHelper.getFloat(json,"binding.speed", 0)
        );
        // Watch me try to guess how to create an item and translation key based on the fabric wiki and
        // Intellj autosuggesiton( please fix it)
        Item item = Registries.ITEM.get(materialId);

        int color = ColorUtil.hexToInt(JsonHelper.getString(json,"color", "000000"));

        return new Material(
                materialId,
                item,
                hiltStats,
                toolHeadStats,
                bindingStats,
                rodStats,
                color
        );
    }
}
