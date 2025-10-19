package ringed_strawberry.github.io.tailored_tools;

import net.fabricmc.api.ModInitializer;

import static ringed_strawberry.github.io.tailored_tools.block.ModBlocks.registerModBlocks;

public class TailoredTools implements ModInitializer {
    public final static String MOD_ID = "tailored_tools";
    @Override
    public void onInitialize() {
        registerModBlocks();
    }
}
