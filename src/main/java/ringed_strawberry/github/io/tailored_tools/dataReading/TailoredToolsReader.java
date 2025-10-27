package ringed_strawberry.github.io.tailored_tools.dataReading;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class TailoredToolsReader implements SimpleSynchronousResourceReloadListener {
    @Override
    public Identifier getFabricId(){
        return Identifier.of(MOD_ID,"tailored_tools_materials");
    }

    @Override
    public void reload(ResourceManager manager){
        for (Identifier id: manager.findResources("tailored_tools_materials", path -> path.toString().endsWith(".json")).keySet()){
            try {
                InputStream stream = manager.getResource(id).get().getInputStream();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
