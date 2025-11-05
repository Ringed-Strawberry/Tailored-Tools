package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import ringed_strawberry.github.io.tailored_tools.custom.materials.Material;

public class ColorUtil {
    public static int hexToInt(String hexCode) {
        int r = Integer.valueOf(hexCode.substring(0, 2), 16);
        int g = Integer.valueOf(hexCode.substring(2, 4), 16);
        int b = Integer.valueOf(hexCode.substring(4, 6), 16);

        return ColorHelper.getArgb(r,g,b);
    }

    public static Text textWithMaterialColor(Text text, Material material){
        return text.getWithStyle(Style.EMPTY.withColor(material.color())).getFirst();
    }

    public static Text materialNameWithColor(Material material){
        return textWithMaterialColor(Text.of(material.id().getPath()), material);
    }

    public static Text materialNameWithColor(ItemStack stack, String toolPart){
        Material material = ToolUtil.getToolPartMaterial(stack, toolPart);
        return textWithMaterialColor(Text.of(material.id().getPath()), material);
    }

    public static Text materialTooltip(ItemStack stack, String toolPart){
        return Text.of(" - ").copy().append(Text.translatable("tooltips.tailored_tools.tool_" + toolPart)
                .append(Text.of(": ")).append(ColorUtil.materialNameWithColor(stack, toolPart)));
    }
}
