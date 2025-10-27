package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.item.ItemStack;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;

import java.util.HashMap;
import java.util.Map;

import static ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents.TAILORED_TOOL;

public class ToolUtil {

    static HashMap<String, Integer> toolNameToIndex = new HashMap<>(Map.of("toolHead",0, "rod",1, "hilt",2, "binding",3));

    public static float getAttackDamage(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            return getToolPartMaterial(stack, "toolHead").toolHeadStats().damage();
        }
        return -1;
    }

    public static int getMaxDurability(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            int durability = 0;
            durability += getToolPartMaterial(stack, "toolHead").toolHeadStats().durability();
            durability += getToolPartMaterial(stack, "toolHead").rodStats().durability();
            durability += getToolPartMaterial(stack, "toolHead").bindingStats().durability();
            return durability;
        }
        return -1;
    }


    public static Material getToolPartMaterial(ItemStack stack, String toolPart){
        return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolNameToIndex.get(toolPart)));
    }
}
