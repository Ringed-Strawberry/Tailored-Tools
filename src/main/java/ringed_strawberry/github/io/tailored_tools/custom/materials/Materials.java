package ringed_strawberry.github.io.tailored_tools.custom.materials;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.chars.Char2ObjectLinkedOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Materials {
    public static HashMap<Identifier, Material> materialList;

    public static Material parse(JsonObject json){
        Identifier materialId = Identifier.of(JsonHelper.getString(json,"id"));

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

        Text translationKey = Text.translatable("material."+materialId.getNamespace()+"."+materialId.getPath());

        return new Material(
                materialId,
                item,
                translationKey,
                hiltStats,
                toolHeadStats,
                bindingStats,
                rodStats
        );
    }
}
