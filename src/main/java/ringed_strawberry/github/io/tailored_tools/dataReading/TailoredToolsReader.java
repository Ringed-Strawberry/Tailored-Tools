package ringed_strawberry.github.io.tailored_tools.dataReading;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;

import java.io.InputStream;
import java.util.HashMap;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class TailoredToolsReader implements SimpleSynchronousResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    public Identifier getFabricId(){
        return Identifier.of(MOD_ID,"materials");
    }

    //Couldn't Test new Reloader, please switch between Reloaders if the one I made doesn't work

    @Override
    public void reload(ResourceManager manager){
        HashMap<Identifier, Material> tempMaterials = new HashMap<>();

        for (Identifier id: manager.findResources("materials", path -> path.toString().endsWith(".json")).keySet()){
            try (InputStream stream = manager.getResource(id).get().getInputStream()){
                JsonObject json = JsonHelper.deserialize(GSON, stream.toString(), JsonObject.class);

                Material material = Materials.parse(json);

                tempMaterials.put(material.id(),material);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Materials.materialList.clear();
            Materials.materialList.putAll(tempMaterials);
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