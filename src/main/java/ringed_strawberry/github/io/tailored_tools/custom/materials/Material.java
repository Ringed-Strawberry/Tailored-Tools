package ringed_strawberry.github.io.tailored_tools.custom.materials;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;

public record Material(Identifier id, // Added it for simpler code sake, feel free to delete it if it breaks everything
                       Item item,
                       Text translationKey,
                       HiltStats hiltStats,
                       ToolHeadStats toolHeadStats,
                       BindingStats bindingStats,
                       RodStats rodStats) {

}
