package ringed_strawberry.github.io.tailored_tools.custom.materials;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public record Material(Identifier id, // Added it for simpler code sake, feel free to delete it if it breaks everything
                       Item item,
                       double speed,
                       float damage,
                       ArrayList<TagKey<Block>> blockBreakingInverseTags,
                       int durability,
                       int color) {

}
