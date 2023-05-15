public class VerletObject {
  private Vector3d posCurrent;
  private Vector3d posOld;
  private Vector3d acceleration;
  
  private Vector3d velocity;
  
  private static int objCount;
  
  public ArrayList<VerletObject> VerlPhysObjs = new ArrayList<VerletObject>();
  
  public updatePosition(float dt) {
    //Compute last velocity
    velocity = posCurrent - posOld;
    
    //save current pos 
    posOld = posCurrent;
    
    //now perform Verlet integration
    posCurrent = posdCurrent + velocity + acceleration * dt * dt;
    
    //reset acceleration
    acceleration = {};
  }
  
  public void accelerate(Vector2d acc) {
    acceleration += acc;
  }
  
  {
    objCount++;
  }
  
  public int getCount() {
    return objCount;
  }
  
  public VerletObject(Vector3d pos) {
    posCurrent = pos;
    
    VerlPhysObjs.add(this);
  }
}
