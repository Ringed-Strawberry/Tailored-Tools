package ringed_strawberry.github.io.tailored_tools.util;

import net.minecraft.util.hit.BlockHitResult;
import org.joml.Vector2d;
import org.joml.Vector3d;
import ringed_strawberry.github.io.spacelib.block.util.BlockHitUtil;

public class WorkbenchUtil {
    static float textHeight = 0.4175f;
    public static int getHandleSlot(BlockHitResult hit){
        for (int i = 1; i <= 8; i++) {
            if(BlockHitUtil.isInteractionInRange3D(hit, (float) getHandlePosition3D(i).x, (float) getHandlePosition3D(i).y, (float) getHandlePosition3D(i).z, 1))
                return i;
        }
        return 0;
    }

    public static Vector2d getHandlePosition(int handle){
        switch (handle) {
            case 2: return new Vector2d(0.42f, 0.71f);
            case 3, 5: return new Vector2d(0.70f, 0.42f);
            case 4, 6: return new Vector2d(0.27f, 0.42f);
            case 7: return new Vector2d(0.42f, 0.28f);
            case 8: return new Vector2d(0.41f, 0.74f);
            default: return new Vector2d(0.42f, 0.28f);
        }
    }

    public static Vector3d getHandlePosition3D(int handle){
        switch (handle) {
            case 1: return new Vector3d(0.71f, 0.43f, 0f);
            case 2: return new Vector3d(0.28f, 0.43f, 0f);
            case 3: return new Vector3d(0f, 0.42f, 0.28f);
            case 4: return new Vector3d(0f, 0.43f, 0.71f);
            case 5: return new Vector3d(0.28f, 0.43f, 1f);
            case 6: return new Vector3d(0.72f, 0.43f, 1f);
            case 7: return new Vector3d(1f, 0.43f, 0.71f);
            case 8: return new Vector3d(1f, 0.43f, 0.28f);
        }

        return new Vector3d(0,0,0);
    }

    public static Vector3d getHandleTextPosition3D(int handle){
        switch (handle) {
            case 1: return new Vector3d(0.73f, textHeight, 0f);
            case 2: return new Vector3d(0.29f, textHeight, 0f);
            case 3: return new Vector3d(0f, textHeight, 0.275f);
            case 4: return new Vector3d(0f, textHeight, 0.7125f);
            case 5: return new Vector3d(0.27f, textHeight, 1f);
            case 6: return new Vector3d(0.71f, textHeight, 1f);
            case 7: return new Vector3d(1f, textHeight, 0.72f);
            case 8: return new Vector3d(1f, textHeight, 0.29f);
        }

        return new Vector3d(0,0,0);
    }

    public static float getTextRotation(int handle) {
        switch (handle) {
            case 1,2: return 0;
            case 3,4: return 90;
            case 5,6: return 180;
            case 7,8: return 270;
        }
        return 0;
    }
}
