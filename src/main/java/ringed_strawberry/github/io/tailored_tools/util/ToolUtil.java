package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import ringed_strawberry.github.io.tailored_tools.TailoredTools;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Materials;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolPart;
import ringed_strawberry.github.io.tailored_tools.custom.tool_parts.ToolParts;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static double getToolSpeed(ItemStack stack){
        double speed = 0;
        speed += (getToolPartMaterial(stack, "head").speed() * getToolPart(stack, "head").speedMultiplier());
        speed += (getToolPartMaterial(stack, "rod").speed() * getToolPart(stack, "rod").speedMultiplier());
        speed += (getToolPartMaterial(stack, "binding").speed() * getToolPart(stack, "binding").speedMultiplier());
        return speed;
    }


    public static ToolPart getToolPart(ItemStack stack, String toolPart) {
        return getToolPart(stack, toolNameToIndex.get(toolPart));
    }

    public static ToolPart getToolPart(ItemStack stack, int toolPart){
        if(ToolParts.toolPartList.get(stack.get(TAILORED_TOOL).get(toolPart).getFirst()) != null)
            return ToolParts.toolPartList.get(stack.get(TAILORED_TOOL).get(toolPart).getFirst());
        else
            return ToolParts.ERROR;
    }

    public static Material getToolPartMaterial(ItemStack stack, String toolPart){
        return getToolPartMaterial(stack, toolNameToIndex.get(toolPart));
    }

    public static Material getToolPartMaterial(ItemStack stack, int toolPart){
        if(Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolPart).getLast()) != null)
            return Materials.materialList.get(stack.get(TAILORED_TOOL).get(toolPart).getLast());
        else
            return Materials.ERROR;
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
        String[] words = getToolPartMaterial(stack, toolPart).id().getPath().split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                if(Capitalised) {
                    result.append(Character.toUpperCase(word.charAt(0)));
                }
                result.append(word.substring(1));
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static Text getMaterialNameStyled(ItemStack stack, String toolPart, boolean Capitalised){
        String textToStyle = getMaterialName(stack, toolPart, Capitalised);
        Text styledText = Text.of(textToStyle).copy();
        styledText = styledText.getWithStyle(Style.EMPTY.withColor(ToolUtil.getToolTint(stack, toolPart))).getFirst();
        return styledText;
    }

    public static ArrayList<Text> getStyledMaterialStats(ItemStack stack, String toolPart) {
        Material material = getToolPartMaterial(stack, toolPart);
        ArrayList<Text> list = new ArrayList<>();

        //ID
        list.add(Text.of(material.id().toString()).copy()
                .getWithStyle(Style.EMPTY.withColor(Colors.GRAY)).getFirst());
        //Speed
        list.add(Text.translatable("tooltips.tailored_tools.speed")
                .append(Text.of(String.valueOf(material.speed())).copy()
                .getWithStyle(Style.EMPTY.withColor(Colors.LIGHT_GRAY)).getFirst()));
        //Damage
        list.add(Text.translatable("tooltips.tailored_tools.damage")
                .append(Text.of(String.valueOf(material.damage())).copy()
                .getWithStyle(Style.EMPTY.withColor(Colors.LIGHT_GRAY)).getFirst()));
        //Durability
        list.add(Text.translatable("tooltips.tailored_tools.durability")
                .append(Text.of(String.valueOf(material.durability())).copy()
                .getWithStyle(Style.EMPTY.withColor(Colors.LIGHT_GRAY)).getFirst()));
        return list;
    }

    public static String getToolPartName(ItemStack stack, String toolPart, boolean Capitalised) {
        String[] words = getToolPart(stack, toolPart).id().getPath().split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                if(Capitalised) {
                    result.append(Character.toUpperCase(word.charAt(0)));
                }
                result.append(word.substring(1));
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }


    public static float getMiningSpeed(ItemStack stack, BlockState state) {
        if(stack.get(TAILORED_TOOL) != null){
            if(getSpeed(stack, state) == 1){
                return 1;
            } else {
                return (float) (getToolSpeed(stack) * getSpeed(stack, state));
            }
        }
        return 0;
    }

    public static boolean getDrops(ItemStack stack, BlockState state) {
        boolean shouldDrop = !state.isToolRequired();
        if(!shouldDrop){
            shouldDrop = isCorrectBlock(stack, state);
        }
        return shouldDrop;
    }

    public static boolean doesStateMatch(boolean material, BlockState state, ItemStack stack){
        if(material){
            if(getToolPartMaterial(stack, "head").blockBreakingInverseTags() != null) {
                for (TagKey<Block> tag : getToolPartMaterial(stack, "head").blockBreakingInverseTags()) {
                    if (state.isIn(tag)) {
                        return false;
                    }
                }
            }
            return true;
        }

        else {
            if(getToolPart(stack, "head").blockBreakingTags() != null && state.isToolRequired()) {
                for (TagKey<Block> tag : getToolPart(stack, "head").blockBreakingTags()) {
                    if (state.isIn(tag)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static int getSpeed(ItemStack stack, BlockState state){
        int returnValue = 0;
        if(!state.isToolRequired())
            returnValue = 1;

        boolean toolMatches = doesStateMatch(false, state, stack);
        boolean materialMatches = doesStateMatch(true, state, stack);

        if(toolMatches && materialMatches)
            returnValue = 2;

        if((toolMatches && !materialMatches) || (!toolMatches && materialMatches)) {
            returnValue = 1;
        }

        TailoredTools.LOGGER.info(String.valueOf(returnValue));

        return returnValue;
    }

    public static boolean isCorrectBlock(ItemStack stack, BlockState state){
        if(!state.isToolRequired())
            return true;

        boolean toolMatches = doesStateMatch(false, state, stack);
        boolean materialMatches = doesStateMatch(true, state, stack);

        return materialMatches && toolMatches;
    }

    public static int getDurability(ItemStack stack) {
        return stack.getOrDefault(ModItemComponents.DURABILITY, -1);
    }

    public static int getClampedDurability(ItemStack stack) {
        return Math.clamp(getDurability(stack), 1, Integer.MAX_VALUE);
    }

    public static void removeDurability(ItemStack stack, int remove) {
        setDurability(stack, Math.clamp(getDurability(stack)-remove, 1, getMaxDurability(stack)));
    }

    public static void setDurability(ItemStack stack, int newDurability) {
        if(getDurability(stack) != -1){
            stack.set(ModItemComponents.DURABILITY, newDurability);
        }
    }
}
