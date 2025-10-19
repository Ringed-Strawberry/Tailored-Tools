package ringed_strawberry.github.io.tailored_tools.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.TexturedModel;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class TailoredToolsModelProvider extends FabricModelProvider {
	public TailoredToolsModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(ModBlocks.WORKBENCH, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.WORKBENCH, Identifier.of(MOD_ID, "block/workbench"));
	}


	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
	}

	@Override
	public String getName() {
		return "TailoredToolsModelProvider";
	}
}