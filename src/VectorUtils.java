import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector4d;

public class VectorUtils {
    public static Vector4d subV4d(Vector4d a, Vector4d b) {
        return new Vector4d(a.x-b.x,a.y-b.y,a.z-b.z,a.w-b.w);
    }
    public static Vector4d subV4d(Vector4d a, Vector3d b) {
        return new Vector4d(a.x-b.x,a.y-b.y,a.z-b.z,a.w);
    }
    public static Vector4d addV4d(Vector4d a, Vector4d b) {
        return new Vector4d(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
    }
    public static Vector4d addV4d(Vector4d a, Vector3d b) {
        return new Vector4d(a.x+b.x, a.y+b.y, a.z+b.z, a.w);
    }
    public static Vector3d subV3d(Vector3d a, Vector3d b) {
        return new Vector3d(a.x-b.x,a.y-b.y,a.z-b.z);
    }
    public static Vector3d addV3d(Vector3d a, Vector3d b) {
        return new Vector3d(a.x+b.x,a.y+b.y,a.z+b.z);
    }
}
