package ringed_strawberry.github.io.tailored_tools.custom.materials;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public record Material(Identifier id, // Added it for simpler code sake, feel free to delete it if it breaks everything
                       Item item,
                       double speed,
                       float damage,
                       int breakingPower,
                       int durability,
                       int color) {

}
