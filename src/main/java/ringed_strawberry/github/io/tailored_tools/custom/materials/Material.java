package ringed_strawberry.github.io.tailored_tools.custom.materials;

import net.minecraft.item.Item;
import org.w3c.dom.Text;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.BindingStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.HiltStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.RodStats;
import ringed_strawberry.github.io.tailored_tools.custom.toolparts.ToolHeadStats;

public record Material(Item item,
                       Text translationKey,
                       HiltStats hiltStats,
                       ToolHeadStats toolHeadStats,
                       BindingStats bindingStats,
                       RodStats rodStats) {

}
