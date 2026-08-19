package ringed_strawberry.github.io.tailored_tools.custom.materials;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.util.ColorUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;
import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class Materials {
    public static final Material ERROR = new Material(Identifier.of(MOD_ID, "error"), Items.BARRIER, 0, 0,null, 0, 0);
    public static HashMap<Identifier, Material> materialList = new HashMap<>(Map.of(Identifier.of(MOD_ID, "error"), ERROR));

    public static Material parse(JsonObject json){
        Identifier materialId = Identifier.of(JsonHelper.getString(json,"id", "tailored_tools:error"));
        LOGGER.info(materialId.toString());
        double speed = JsonHelper.getDouble(json, "speed",0.0);
        float damage = JsonHelper.getFloat(json, "damage",0);
        int durability = JsonHelper.getInt(json, "durability",0);

        // Watch me try to guess how to create an item and translation key based on the fabric wiki and
        // Intellj autosuggesiton( please fix it)

        // ^ it works silly :3
        Item item = Registries.ITEM.get(materialId);

        ArrayList<TagKey<Block>> breakableTags = new ArrayList<>();
        JsonArray breakableTagsJsonArray = JsonHelper.getArray(json, "cannot.break", new JsonArray());

        for (JsonElement element : breakableTagsJsonArray) {
            TagKey<Block> tag = TagKey.of(RegistryKeys.BLOCK, Identifier.of(element.getAsString()));
            breakableTags.add(tag);
        }

        int color = ColorUtil.hexToInt(JsonHelper.getString(json,"color", "000000"));

        return new Material(
                materialId,
                item,
                speed,
                damage,
                breakableTags,
                durability,
                color
        );
    }
}
