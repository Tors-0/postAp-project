package VerletEngine;

import org.joml.Vector2d;

import java.util.ArrayList;

public class VerlObj {
  
  private Vector2d tempPos = new Vector2d(0.0f, 0.0f);
  private Vector2d currentPos;
  private float dampening;
  private float radius = 50.0f;
  private Vector2d acceleration;
  private Vector2d velocity;
  private float deltaTime;
 
  public static ArrayList<VerlObj> scenePhysObjs = new ArrayList<VerlObj>();
  
  public VerlObj(Vector2d spawnPos) {
      currentPos = spawnPos;
    
      scenePhysObjs.add(this);
  }
  
  public void accelerate(Vector2d accel) {
      acceleration.add(accel);
  }
  
  public Vector2d getAcceleration() {
      return acceleration; 
  }
  
  public Vector2d getPos() {
      return currentPos; 
  }
  
  public void setPos(Vector2d newPos) {
      currentPos = newPos; 
  }
  
  public void iterate() {
      
    
      currentPos = tempPos.add(velocity.mul(deltaTime));
    
      acceleration = new Vector2d(0.0f, 0.0f);
  }
  
  private void gravityIter() {
      scenePhysObjs.forEach(e -> {
          
      });
  }
  
}
