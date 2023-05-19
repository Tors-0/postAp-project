package VerletEngine.Take2;

public class VerlObj {
  
  private Vector2d oldPos;
  private Vector2d currentPos;
  private float dampening;
  
  
  
  
  public static ArrayList<VerlObj> ScenePhysObjs = new ArrayList<VerlObj>();
  
  
  
  public VerlObj(Vector2d spawnPos) {
      currentPos = spawnPos;
    
      ScenePhysObjs.add(this);
  }
  
  
  
  
  
  
  
  
  
  
}
