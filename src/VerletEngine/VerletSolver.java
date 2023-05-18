package VerletEngine;

import org.joml.*;

import java.util.concurrent.atomic.AtomicReference;

import static VerletEngine.VerletObject.*;

public class VerletSolver {
    Vector2d gravity = new Vector2d(0.0, 0.0);

    Vector2d toObj;
    float dist;
    Vector2d n;
    VerletObject obj1 = new VerletObject();
    VerletObject obj2 = new VerletObject();
    Vector2d collisionAxis;
    float solveDist;
    Vector2d solveN;
    float delta;
   
    public void update(float dt) {
      int subSteps = 4;
      float subdt = dt/ (float) (subSteps);
      
      for(int i = subSteps; i > 0; i--) {
        applyGravity();
        applyConstraints();
        solveCollisions();
        updatePositions(subdt);
      }
    }
    
    private void updatePositions(float dt) {
     VerlPhysObjs.forEach(currentObj ->
       currentObj.updatePosition(dt));
    }
    
    private void applyGravity() {
      VerlPhysObjs.forEach(currentObj ->
          currentObj.accelerate(gravity)
      );
    }
  
    private void applyConstraints() {
        Vector2d pos = new Vector2d(800.0, 450.0);
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

    private void solveCollisions() {
        int objCount = VerlPhysObjs.size();
        for(int i = 0; i < objCount; ++i) {
            obj1 = VerlPhysObj.get(i);
            for(int j = i + 1; j < objCount; ++j) {
                obj2 = VerlPhysObj.get(k);
                collisionAxis = obj1.getPosCurrent().sub(obj2.getPosCurrent());
                solveDist = obj2.distance(collisionAxis);
                if(dist < 100.0f) {
                    solveN = collisionAxis.div(dist);
                    delta = 100.0f - dist;
                    obj1.setPosCurrent(obj1.getPosCurrent().add(0.5f * delta * n));
                    obj2.setPosCurrent(obj2.getPosCurrent().sub(0.5f * delta * n));
               }
            }
        }
    }
}
