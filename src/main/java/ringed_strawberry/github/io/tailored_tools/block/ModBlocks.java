package ringed_strawberry.github.io.tailored_tools.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.block.custom.WorkbenchBlock;

import static ringed_strawberry.github.io.spacelib.block.BlockGen.createBlock;
import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ModBlocks {
    public static final Block WORKBENCH = createBlock(WorkbenchBlock::new, Identifier.of(MOD_ID, "workbench"), AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE));

    public static void registerModBlocks(){

    }
}
