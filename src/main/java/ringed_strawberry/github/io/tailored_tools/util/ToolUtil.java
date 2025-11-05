package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents.TAILORED_TOOL;

public class ToolUtil {

    static HashMap<String, Integer> toolNameToIndex = new HashMap<>(Map.of("head",0, "binding",1, "hilt",2, "rod",3));

    public static float getAttackDamage(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            return getToolPartMaterial(stack, "head").headStats().damage();
        }
        return -1;
    }

    public static int getMaxDurability(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            int durability = 0;
            durability += getToolPartMaterial(stack, "head").headStats().durability();
            durability += getToolPartMaterial(stack, "rod").rodStats().durability();
            durability += getToolPartMaterial(stack, "binding").bindingStats().durability();
            return durability;
        }
        return -1;
    }

    public static double getToolSpeed(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            double durability = 0;
            durability += getToolPartMaterial(stack, "head").headStats().speed();
            durability += getToolPartMaterial(stack, "rod").rodStats().speed();
            durability += getToolPartMaterial(stack, "binding").bindingStats().speed();
            durability += getToolPartMaterial(stack, "hilt").hiltStats().speed();
            return durability;
        }
        return -1;
    }


    public static Material getToolPartMaterial(ItemStack stack, String toolPart){
        return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolNameToIndex.get(toolPart)));
    }

    public static Material getToolPartMaterial(ItemStack stack, int toolPart){
        return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolPart));
    }

    public static int getToolTint(ItemStack stack, int toolPart) {
        return getToolPartMaterial(stack, toolPart).color();
    }

    public static int getToolTint(ItemStack stack, String toolPart) {
        return getToolPartMaterial(stack, toolPart).color();
    }

    public static String getMaterialName(ItemStack stack, String toolPart, boolean Capitalised) {
        String materialName = getToolPartMaterial(stack, toolPart).id().getPath();
        materialName = Pattern.compile("^.").matcher(materialName).replaceFirst(m -> {
            if(Capitalised)
                return m.group().toUpperCase();
            else
                return m.group();
        });


        return materialName;
    }


    public static float getMiningSpeed(ItemStack stack, BlockState state) {
        return (float) getToolSpeed(stack);
    }
}
