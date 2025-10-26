package ringed_strawberry.github.io.tailored_tools;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import ringed_strawberry.github.io.tailored_tools.dataReading.TailoredToolsReader;

import static ringed_strawberry.github.io.tailored_tools.block.ModBlocks.registerModBlocks;
import static ringed_strawberry.github.io.tailored_tools.block.entity.ModBlockEntities.registerModBlockEntities;

public class TailoredTools implements ModInitializer {
    public final static String MOD_ID = "tailored_tools";
    @Override
    public void onInitialize() {
        registerModBlocks();
        registerModBlockEntities();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new TailoredToolsReader());

    }
}
