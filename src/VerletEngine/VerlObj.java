package VerletEngine;

import org.joml.Vector2d;
import java.util.ArrayList;
import Environment.GameSpace;

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

    public VerlObj() {
        currentPos = new Vector2d(GameSpace.winWidth/2.0, GameSpace.winHeight/2.0);

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
        //The velocity is reset each iteration so set velocity to the difference that the object made over the last iteration
        velocity = currentPos.sub(tempPos);

        //Sets the temp pos to the current one because the current is now the next frame and the old frame is now the current frame
        tempPos = currentPos;

        //
        currentPos = tempPos.add(velocity.mul(deltaTime));

        //
        acceleration = new Vector2d(0.0f, 0.0f);
    }
  
     private void gravityIter() {
        scenePhysObjs.forEach(e -> {
          
        });
     }
  
}
