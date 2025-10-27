package ringed_strawberry.github.io.tailored_tools.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.spacelib.item.ItemGen;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.item.custom.TailoredToolItem;

import java.util.List;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ModItems{

    public static final Item TAILORED_TOOL = ItemGen.createItem(Identifier.of(MOD_ID, "tailored_tool"), TailoredToolItem::new,
            new Item.Settings().component(ModItemComponents.MAX_DURABILITY, 0).component(ModItemComponents.TAILORED_TOOL, List.of(Identifier.of(MOD_ID, "wood"),Identifier.of(MOD_ID, "wood"),Identifier.of(MOD_ID, "wood"))));

    public static void registerModItems(){

    }
}
