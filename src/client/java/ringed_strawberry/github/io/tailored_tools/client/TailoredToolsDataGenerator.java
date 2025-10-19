package ringed_strawberry.github.io.tailored_tools.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import ringed_strawberry.github.io.tailored_tools.client.datagen.TailoredToolsModelProvider;

public class TailoredToolsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(TailoredToolsModelProvider::new);
    }
}
