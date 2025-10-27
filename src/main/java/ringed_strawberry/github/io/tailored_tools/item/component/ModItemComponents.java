package ringed_strawberry.github.io.tailored_tools.item.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.spacelib.item.components.DataComponentGen;

import java.util.List;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ModItemComponents {
    public static final ComponentType<List<Identifier>> TAILORED_TOOL = DataComponentGen.registerComponent(
            Identifier.of(MOD_ID, "tailored_tool"),
            ComponentType.<List<Identifier>>builder().codec(Codec.list(Identifier.CODEC, 4,4)).build()
    );

    public static final ComponentType<Integer> MAX_DURABILITY = DataComponentGen.registerComponent(
            Identifier.of(MOD_ID, "max_durability"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<Integer> DURABILITY = DataComponentGen.registerComponent(
            Identifier.of(MOD_ID, "durability"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void registerModComponents(){

    }
}
