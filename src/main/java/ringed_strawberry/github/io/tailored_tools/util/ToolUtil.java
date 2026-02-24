package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolPart;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolParts;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents.TAILORED_TOOL;

public class ToolUtil {

    public static final HashMap<String, Integer> toolNameToIndex = new HashMap<>(Map.of("head",0, "binding",1, "rod",2));

    public static float getAttackDamage(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            return getToolPartMaterial(stack, "head").damage();
        }
        return -1;
    }

    public static int getMaxDurability(ItemStack stack){
        if(stack.get(TAILORED_TOOL) != null){
            int durability = 0;
            durability += (int) (getToolPartMaterial(stack, "head").durability() * getToolPart(stack, "head").durabilityMultiplier());
            durability += (int) (getToolPartMaterial(stack, "rod").durability() * getToolPart(stack, "rod").durabilityMultiplier());
            durability += (int) (getToolPartMaterial(stack, "binding").durability() * getToolPart(stack, "binding").durabilityMultiplier());
            return durability;
        }
        return -1;
    }

    public static double getToolSpeed(ItemStack stack, BlockState state){
        if(stack.get(TAILORED_TOOL) != null){
            double speed = 0;
            speed += (getToolPartMaterial(stack, "head").speed() * getToolPart(stack, "head").speedMultiplier());
            speed += (getToolPartMaterial(stack, "rod").speed() * getToolPart(stack, "rod").speedMultiplier());
            speed += (getToolPartMaterial(stack, "binding").speed() * getToolPart(stack, "binding").speedMultiplier());
            boolean correctBlock= false;
            for (int i = 0; i < getToolPart(stack, "head").blockBreakingTags().size(); i++) {
                TagKey<Block> tag = getToolPart(stack, "head").blockBreakingTags().get(i);
                if(state.isIn(tag)){
                    correctBlock = true;
                }
            }

            return speed * (correctBlock ? 2 : 1);
        }
        return -1;
    }


    private static ToolPart getToolPart(ItemStack stack, String toolPart) {
        return getToolPart(stack, toolNameToIndex.get(toolPart));
    }

    public static ToolPart getToolPart(ItemStack stack, int toolPart){
        return ToolParts.toolPartList.get(stack.get(TAILORED_TOOL).get(toolPart).getFirst());
    }

    public static Material getToolPartMaterial(ItemStack stack, String toolPart){
        return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolNameToIndex.get(toolPart)).getLast());
    }

    public static Material getToolPartMaterial(ItemStack stack, int toolPart){
        return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolPart).getLast());
    }

    public static int getToolTint(ItemStack stack, int toolPart) {
        if(getToolPartMaterial(stack, toolPart) != null)
            return getToolPartMaterial(stack, toolPart).color();
        else
            return 0;
    }

    public static int getToolTint(ItemStack stack, String toolPart) {
        return getToolTint(stack, toolNameToIndex.get(toolPart));
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

    public static String getToolPartName(ItemStack stack, String toolPart, boolean Capitalised) {
        String toolPartName = getToolPart(stack, toolPart).id().getPath();
        toolPartName = Pattern.compile("^.").matcher(toolPartName).replaceFirst(m -> {
            if(Capitalised)
                return m.group().toUpperCase();
            else
                return m.group();
        });


        return toolPartName;
    }


    public static float getMiningSpeed(ItemStack stack, BlockState state) {
        return (float) getToolSpeed(stack, state);
    }
}
