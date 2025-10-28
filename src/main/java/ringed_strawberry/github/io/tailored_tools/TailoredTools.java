package ringed_strawberry.github.io.tailored_tools;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import ringed_strawberry.github.io.tailored_tools.dataReading.TailoredToolsReader;

import java.util.logging.Logger;

import static ringed_strawberry.github.io.tailored_tools.block.ModBlocks.registerModBlocks;
import static ringed_strawberry.github.io.tailored_tools.block.entity.ModBlockEntities.registerModBlockEntities;
import static ringed_strawberry.github.io.tailored_tools.item.ModItems.registerModItems;
import static ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents.registerModComponents;

public class TailoredTools implements ModInitializer {
    public final static String MOD_ID = "tailored_tools";
    public static final Logger LOGGER = Logger.getLogger("Tailored Tools");
    @Override
    public void onInitialize() {
        registerModBlocks();
        registerModBlockEntities();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new TailoredToolsReader());
        registerModComponents();
        registerModItems();
    }
}
