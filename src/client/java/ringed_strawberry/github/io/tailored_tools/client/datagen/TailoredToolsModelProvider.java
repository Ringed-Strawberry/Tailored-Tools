package ringed_strawberry.github.io.tailored_tools.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.item.ModItems;

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
        itemModelGenerator.register(ModItems.TAILORED_TOOL, Models.GENERATED_THREE_LAYERS);
    }

	@Override
	public String getName() {
		return "TailoredToolsModelProvider";
	}
}