package ringed_strawberry.github.io.tailored_tools.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.spacelib.client.SpacelibDataGenerator;
import ringed_strawberry.github.io.spacelib.client.datagen.item.ItemDatagenUtil;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.client.color.tint.ToolTintSource;
import ringed_strawberry.github.io.tailored_tools.item.ModItems;

import java.util.Optional;

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
        ItemDatagenUtil.registerWithFourTints(itemModelGenerator, ModItems.TAILORED_TOOL, new ToolTintSource(0),new ToolTintSource(1),new ToolTintSource(2),new ToolTintSource(3));
    }

	@Override
	public String getName() {
		return "TailoredToolsModelProvider";
	}
}