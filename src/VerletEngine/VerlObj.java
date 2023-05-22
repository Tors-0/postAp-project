package VerletEngine;

import Client.Client2;
import org.joml.Vector2d;

import java.util.ArrayList;

import static Client.Client2.scene;

public class VerlObj {

    protected Vector2d tempPos = new Vector2d(0.0f, 0.0f);
    protected Vector2d currentPos;
    protected float dampening;
    protected Vector2d acceleration = new Vector2d(0.0f, 0.0f);
    protected Vector2d vel;
    public static ArrayList<VerlObj> scenePhysObjs = new ArrayList<>();
    Vector2d facingPos;
    protected int radius = 50;

    Vector2d movement;
    Vector2d acc = new Vector2d(0,-9.8);
    public static final float deltaTime = Client2.tps;
    boolean onGround = false;

    protected float mass = 2.7f; // assume the player is a ping pong ball, mass = 2.7 grams
    protected final float terminalVel = 9.5f; // still assuming the player is a ping pong ball, tV = 9.5 m/s
    // phys should normalize velocity towards tV unless another force is acting (keyPress or onSurface)
    protected final float gravC = -5; // gravitational constant, -9.8 is earth's but is too strong for this

    public VerlObj(Vector2d spawnPos, int rad, float masshh) {
        currentPos = spawnPos;
        radius = rad;
        mass = masshh;

        scenePhysObjs.add(this);
    }

    public VerlObj() {
        currentPos = new Vector2d(scene.width() / 2.0, scene.height() / 2.0);

        scenePhysObjs.add(this);
    }

    public Vector2d getFacingPos() {
        return facingPos;
    }

    public void accelerate(Vector2d accel) {
      acceleration.add(accel);
    }

    /**
     * Simple getter method for the protected variable acceleration
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

    public Vector2d getTempPos() {
        return tempPos;
    }

    public void setPos(Vector2d newPos) {
      currentPos = newPos;
  }

    public void setTempPos(Vector2d newPos) {
        tempPos = newPos;
  }

    public Vector2d getVel() {
        return vel;
  }

    public void setVel(Vector2d newVeloc) {
        vel = newVeloc;
    }

    public int getRadius() {
        return radius;
    }

    public float getMass() {
        return mass;
    }

    public void iterate() {
        //The velocity is reset each iteration so set velocity to the difference that the object made over the last iteration
        vel = (Vector2d) (currentPos.sub(tempPos));

        //Saves the temp pos as the current one because the current is now the next frame and the old frame is now the current frame
        tempPos = currentPos;

        //Invoke the ancient powers of Verlet Integration
        //Actually Pretty simple, its just: (Next Frame) = (Last Frame) + (velocity) * (deltaTime) // Delta time is the time between rendering frames
        // You want to use Delta Time to make sure that your simulation isn't run at different speeds based on your frame rate
        currentPos = tempPos.add(vel.mul(deltaTime));

        //Resets the acceleration for the next iteration
        acceleration = new Vector2d(0.0f, 0.0f);
    }

}
