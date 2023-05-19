package VerletEngine;

import org.joml.Vector2d;
import java.util.ArrayList;
import Environment.GameSpace;

public class VerlObj {

    private Vector2d tempPos = new Vector2d(0.0f, 0.0f);
    private Vector2d currentPos;
    private float dampening;
    private Vector2d acceleration = new Vector2d(0.0f, 0.0f);
    private Vector2d velocity;
    private float deltaTime;
    public static ArrayList<VerlObj> scenePhysObjs = new ArrayList<>();

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

    /**
     * Simple getter method for the private variable acceleration
     * @return returns the acceleration as a Vector2d
     */
    public Vector2d getAcceleration() {
      return acceleration; 
  }

    /**
     * Simple getter method for the current position of the object
     * @return The position of an object as a Vector2d
     */
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

        //Invoke the ancient powers of Verlet Integration
         //Actually Pretty simple, its just: (Next Frame) = (Last Frame) + (velocity) * (deltaTime) // Delta time is the time between rendering frames
         // You want to use Delta Time to make sure that your simulation isn't run at different speeds based on your frame rate
        currentPos = tempPos.add(velocity.mul(deltaTime));

        //Resets the acceleration for the next iteration
        acceleration = new Vector2d(0.0f, 0.0f);
    }


    /**
     *  Gravity Iter is a separate iteration that is also called on every tick of the simulation that simulates gravity on every object with physics
     *  It does so by performing the law of universal gravitation and it's formula on each physics object
     */
    private void gravityIter() {
        /* law of universal gravitation = G * ( (m1 * m2) / r*r )
        G = 6.6743 * Math.pow(10,-11) // in units of m^3 * kg^-1 * s^-2
        m1 = mass of object 1
        m2 = mass of object 2
        r = distance between centers of masses
        we could abitrarily place a very massive object quite far from the render window to create constant downwards gravity
         */
        scenePhysObjs.forEach(e -> {

        });
    }
  
}
