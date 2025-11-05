package ringed_strawberry.github.io.tailored_tools.client.color.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.render.item.tint.DyeTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

public record ToolTintSource(int toolPart) implements TintSource {
    public static final MapCodec<ToolTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(ToolTintSource::toolPart)).apply(instance, ToolTintSource::new));

    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        return ToolUtil.getToolTint(stack, toolPart);
    }

    public MapCodec<ToolTintSource> getCodec() {
        return CODEC;
    }
}