package ringed_strawberry.github.io.tailored_tools.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ringed_strawberry.github.io.tailored_tools.block.ModBlocks;
import ringed_strawberry.github.io.tailored_tools.block.entity.custom.WorkbenchBlockEntity;

import static ringed_strawberry.github.io.tailored_tools.TailoredTools.MOD_ID;

public class ModBlockEntities {

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<WorkbenchBlockEntity> WORKBENCH = register(
            "workbench",
            FabricBlockEntityTypeBuilder.create(WorkbenchBlockEntity::new, ModBlocks.WORKBENCH).build()
    );

    public static void registerModBlockEntities(){
    }
}
