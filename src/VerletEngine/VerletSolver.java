package VerletEngine;

import org.joml.*;

import java.util.concurrent.atomic.AtomicReference;

public class VerletSolver {
    static Vector2d gravity = new Vector2d(0.0, 0.0);

    static Vector2d toObj;
    static float dist;
    static Vector2d n;
    
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
     VerletObject.VerlPhysObjs.forEach(currentObj ->
       currentObj.updatePosition(dt));
    }
    
    private static void applyGravity() {
      VerletObject.VerlPhysObjs.forEach(currentObj ->
          currentObj.accelerate(gravity)
      );
    }
  
    private static void applyConstraints() {
        Vector2d pos = new Vector2d(800.0, 450.0);
        float radius = 400.0f;
        VerletObject.VerlPhysObjs.forEach(currentObj -> {
            toObj = currentObj.getPosCurrent();
            dist = (float) (currentObj.getPosCurrent().distance(toObj));
            if (dist > radius - 50.0f) {
                n = toObj.div(dist);
                currentObj.setPosCurrent(currentObj.getPosCurrent().add(n.mul(dist - 50.0f)));
            }
        });
    }

    private static void solveCollisions() {

    }
}
