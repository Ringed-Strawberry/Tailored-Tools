package ringed_strawberry.github.io.tailored_tools.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.spacelib.item.ItemGen;
import ringed_strawberry.github.io.tailored_tools.item.custom.TailoredToolItem;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ModItems{

    public final Item TAILORED_TOOL = ItemGen.createItem(Identifier.of(MOD_ID, "tailored_tool"), TailoredToolItem::new, new Item.Settings());

    public static void registerModItems(){

    }
}
