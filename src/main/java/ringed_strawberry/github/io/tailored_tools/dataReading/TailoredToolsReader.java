package ringed_strawberry.github.io.tailored_tools.dataReading;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolPart;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolParts;

import java.io.InputStreamReader;
import java.util.HashMap;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.LOGGER;
import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class TailoredToolsReader implements SimpleSynchronousResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    public Identifier getFabricId(){
        return Identifier.of(MOD_ID,"materials");
    }

    //Couldn't Test new Reloader, please switch between Reloaders if the one I made doesn't work

    @Override
    public void reload(ResourceManager manager){
        //Materials
        HashMap<Identifier, Material> tempMaterials = new HashMap<>();

        for (Identifier id: manager.findResources("materials", path -> path.toString().endsWith(".json")).keySet()){
            try (InputStreamReader stream = new InputStreamReader(manager.getResource(id).get().getInputStream()) ){
                JsonObject json = JsonHelper.deserialize(GSON, stream, JsonObject.class);

                Material material = Materials.parse(json);

                tempMaterials.put(material.id(),material);
            } catch (Exception e) {
                LOGGER.info("ERROR loading materials    ERROR:   " + e.getMessage());
            }

            Materials.materialList.clear();
            Materials.materialList.putAll(tempMaterials);
        }





        //Tool Parts
        HashMap<Identifier, ToolPart> tempToolParts = new HashMap<>();

        for (Identifier id: manager.findResources("tool_parts", path -> path.toString().endsWith(".json")).keySet()){
            try (InputStreamReader stream = new InputStreamReader(manager.getResource(id).get().getInputStream()) ){
                JsonObject json = JsonHelper.deserialize(GSON, stream, JsonObject.class);

                ToolPart toolPart = ToolParts.parse(json);

                tempToolParts.put(toolPart.id(), toolPart);
            } catch (Exception e) {
                LOGGER.info("ERROR loading tool parts    ERROR:   " + e.getMessage());
            }

            ToolParts.toolPartList.clear();
            ToolParts.toolPartList.putAll(tempToolParts);
        }
    }


    /* Old Reload that I saved in case the new one explodes the game
    @Override
    public void reload(ResourceManager manager){
        for (Identifier id: manager.findResources("materials", path -> path.toString().endsWith(".json")).keySet()){
            try {
                InputStream stream = manager.getResource(id).get().getInputStream();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }*/


}