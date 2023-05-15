public class VerletSolver {
    Vector3d gravity = new Vector3d(0.0, 0.0, 10000.0);
  
    
    public void update(double dt) {
      int subSteps = 4;
      float subdt = dt/ (float) (subSteps);
      
      for(int i = subSteps; i--; ) {
        applyGravity();
        applyConstraint();
        solveCollisions();
        updatePositions(subdt);
      }
    }
    
    private void updatePositions(double dt) {
     VerletObject.VerlPhysObjs.forEach(currentObj -> {
       obj.updatePosition(dt);
     })); 
    }
    
    private void applyGravity() {
      VerletObject.VerlPhysObjs.forEach(currentObj -> {
          obj.accelerate(gravity);
      });
    }
  
    private void applyConstraints() {
      
      
      
    }
}
