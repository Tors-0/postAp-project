package VerletEngine;

import org.joml.*;

import java.util.concurrent.atomic.AtomicReference;

import static VerletEngine.VerletObject.*;

public class VerletSolver {
    static Vector2d gravity = new Vector2d(0.0, 0.0);

    static Vector2d toObj = new Vector2d(0,0);
    static float dist;
    static Vector2d n = new Vector2d(0,0);
    static VerletObject obj1;
    static VerletObject obj2;
    static Vector2d collisionAxis;
    static float solveDist;
    static Vector2d solveN;
    static float delta;

    public static void update(float dt) {
        int subSteps = 4;
        float subdt = dt/ (float) (subSteps);

        for(int i = subSteps; i > 0; i--) {
            applyGravity();
            applyConstraints();
            solveCollisions();
            updatePositions(subdt);
        }
    }

    private static void updatePositions(float dt) {
        VerlPhysObjs.forEach(currentObj ->
                currentObj.updatePosition(dt));
    }

    private static void applyGravity() {
        VerlPhysObjs.forEach(currentObj ->
                currentObj.accelerate(gravity)
        );
    }

    private static void applyConstraints() {
        Vector2d pos = new Vector2d(400.0, 250.0);
        float radius = 400.0f;
        VerlPhysObjs.forEach(currentObj -> {
            toObj = currentObj.getPosCurrent();
            dist = (float) (currentObj.getPosCurrent().distance(toObj));
            if (dist > radius - 50.0f) {
                n = toObj.div(dist);
                currentObj.setPosCurrent(currentObj.getPosCurrent().add(n.mul(dist - 50.0f)));
            }
        });
    }

    private static void solveCollisions() {
        int objCount = VerlPhysObjs.size();
        for(int i = 0; i < objCount; ++i) {
            obj1 = VerlPhysObjs.get(i);
            for(int j = i + 1; j < objCount; ++j) {
                obj2 = VerlPhysObjs.get(j);
                collisionAxis = obj1.getPosCurrent().sub(obj2.getPosCurrent());
                solveDist = (float) obj2.getPosCurrent().distance(collisionAxis);
                if(dist < 100.0f) {
                    solveN = collisionAxis.div(dist);
                    delta = 100.0f - dist;
                    obj1.setPosCurrent(obj1.getPosCurrent().add(n.mul(0.5f * delta)));
                    obj2.setPosCurrent(obj2.getPosCurrent().sub(n.mul(0.5f * delta)));
                }
            }
        }
    }
}