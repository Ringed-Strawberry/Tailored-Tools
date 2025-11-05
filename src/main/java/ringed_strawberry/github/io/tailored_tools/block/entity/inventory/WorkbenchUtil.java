package ringed_strawberry.github.io.tailored_tools.block.entity.inventory;

import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import org.joml.Vector2d;
import ringed_strawberry.github.io.spacelib.block.util.BlockHitUtil;

public class WorkbenchUtil {
    public static int getHandleSlot(BlockHitResult hit){
        if (hit.getSide() == Direction.EAST) {
            if (BlockHitUtil.isInteractionInRange(hit, 0.42f, 0.28f, 1)) {
                return 1;
            }
            if (BlockHitUtil.isInteractionInRange(hit, 0.42f, 0.71f, 1)) {
                return 2;
            }
        }
        if (hit.getSide() == Direction.NORTH) {
            if (BlockHitUtil.isInteractionInRange(hit, 0.70f, 0.42f, 1)) {
                return 3;
            }
            if (BlockHitUtil.isInteractionInRange(hit, 0.27f, 0.42f, 1)) {
                return 4;
            }
        }
        if (hit.getSide() == Direction.SOUTH) {
            if (BlockHitUtil.isInteractionInRange(hit, 0.70f, 0.42f, 1)) {
                return 5;
            }
            if (BlockHitUtil.isInteractionInRange(hit, 0.27f, 0.41f, 1)) {
                return 6;
            }
        }

        if (hit.getSide() == Direction.WEST) {
            if (BlockHitUtil.isInteractionInRange(hit, 0.42f, 0.28f, 1)) {
                return 7;
            }
            if (BlockHitUtil.isInteractionInRange(hit, 0.41f, 0.74f, 1)) {
                return 8;
            }
        }
        return 1;
    }

    public static Vector2d getHandlePosition(int handle){
        switch (handle) {
            default: return new Vector2d(0.42f, 0.28f);
            case 2: return new Vector2d(0.42f, 0.71f);
            case 3, 5: return new Vector2d(0.70f, 0.42f);
            case 4, 6: return new Vector2d(0.27f, 0.42f);
            case 7: return new Vector2d(0.42f, 0.28f);
            case 8: return new Vector2d(0.41f, 0.74f);
        }
    }
}
