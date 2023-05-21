package VerletEngine;

import Client.Client2;
import Engine.VectorC2d;
import org.joml.Vector2d;

import java.util.ArrayList;

import static Client.Client2.scene;

public class VerlObj {

    private VectorC2d tempPos = new VectorC2d(0.0f, 0.0f);
    private VectorC2d currentPos;
    private float dampening;
    private VectorC2d acceleration = new VectorC2d(0.0f, 0.0f);
    private VectorC2d vel;
    public static ArrayList<VerlObj> scenePhysObjs = new ArrayList<>();
    VectorC2d facingPos;

    VectorC2d movement;
    VectorC2d acc = new VectorC2d(0,-9.8);
    float deltaTime = Client2.tps;
    boolean onGround = false;

    private float mass = 2.7f; // assume the player is a ping pong ball, mass = 2.7 grams
    private final float terminalVel = 9.5f; // still assuming the player is a ping pong ball, tV = 9.5 m/s
    // phys should normalize velocity towards tV unless another force is acting (keyPress or onSurface)
    private final float gravC = -5; // gravitational constant, -9.8 is earth's but is too strong for this

    public VerlObj(VectorC2d spawnPos) {
        currentPos = spawnPos;
    
        scenePhysObjs.add(this);
    }

    public VerlObj() {
        currentPos = new VectorC2d(scene.width() / 2.0, scene.height() / 2.0);

        scenePhysObjs.add(this);
    }

    public VectorC2d getFacingPos() {
        return facingPos;
    }

    public void accelerate(VectorC2d accel) {
      acceleration.add(accel);
    }

    /**
     * Simple getter method for the private variable acceleration
     * @return returns the acceleration as a VectorC2d
     */
    public VectorC2d getAcceleration() {
      return acceleration; 
  }

    /**
     * Simple getter method for the current position of the object
     * @return The position of an object as a VectorC2d
     */
    public VectorC2d getPos() {
      return currentPos; 
    }

    public VectorC2d getTempPos() {
        return tempPos;
    }

    public void setPos(VectorC2d newPos) {
      currentPos = newPos; 
  }

    public void setTempPos(VectorC2d newPos) {
        tempPos = newPos;
  }

    public VectorC2d getVel() {
        return vel;
  }

    public void setVel(VectorC2d newVeloc) {
        vel = newVeloc;
    }
  
    public void iterate() {
        //The velocity is reset each iteration so set velocity to the difference that the object made over the last iteration
        vel = (VectorC2d) (currentPos.sub(tempPos));

        //Sets the temp pos to the current one because the current is now the next frame and the old frame is now the current frame
        tempPos = currentPos;

        //Invoke the ancient powers of Verlet Integration
         //Actually Pretty simple, its just: (Next Frame) = (Last Frame) + (velocity) * (deltaTime) // Delta time is the time between rendering frames
         // You want to use Delta Time to make sure that your simulation isn't run at different speeds based on your frame rate
        currentPos = tempPos.add(vel.mul(deltaTime));

        //Resets the acceleration for the next iteration
        acceleration = new VectorC2d(0.0f, 0.0f);
    }


    /**
     *  Gravity Iter is a separate iteration that is also called on every tick of the simulation that simulates gravity on every object with physics
     *  It does so by performing the law of universal gravitation and it's formula on each physics object
     */
    private void gravityIter() {
        /* law of universal gravitation = G * ( (m1 * m2) / r*r )
        G = 6.6743 * Math.pow(10,-11) // in units of m^3 * kg^-1 * s^-2
        m1 = mass of object 1 = Math.pow(5.97219,24);
        m2 = mass of object 2
        r = distance between centers of masses
        we could abitrarily place a very massive object quite far from the render window to create constant downwards gravity
         */
        scenePhysObjs.forEach(e -> {

        });
    }
    public void update() {
        gravity();
        collision();
        // iterate();
        // DO NOT DELETE! DISABLED FOR TESTING /*
        // apply dampening to acceleration
        // replace scalar with gravitational constant
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravitational constant of 9.8 m/s^2
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        // apply acceleration to velocity
        vel = vel.mul(0.99f);
        vel = vel.add(acc.div(mass));
        // apply terminal velocity, when velocity = terminal velocity, gravitational acceleration must be zero

        // save currentPos to tempPos
        this.setTempPos(new VectorC2d(this.getPos().x,this.getPos().y));
        // apply velocity/deltaTime scaled by the mass of the object
        this.setPos(this.getPos().add(vel.div(deltaTime)));

        this.setPos(this.getPos().add(movement.div(deltaTime)));
    }
    public void collision() {
        scene.GameRectObjs.forEach(currentObj -> {

        });
        if (currentPos.x < 0) { // collide and bounce off of left edge
            currentPos.x = 2;
            vel.x *= -0.9;
        } else if (currentPos.x > Client2.WIDTH) { // collide and bounce off of right edge
            currentPos.x = Client2.WIDTH - 2;
            vel.x *= -0.9;
        }
        if (currentPos.y > Client2.HEIGHT) { // collide and bounce off of ceiling
            currentPos.y = Client2.HEIGHT - 2;
            vel.y *= -0.9;
        }
    }
    public void gravity() {
        if (currentPos.y > 2) {
            acc.y = gravC;
        } else {
            acc.y = 0;
            currentPos.y = 2;
            vel.y *= -0.85;
        }
    }
    /*
    Movement calls are in DrawingPanel around line 100-130
     */
    public void move(VectorC2d v) {
        movement = v;
    }
    /*public void setFacing(VectorC2d v) {
        facingPos = v;
    }*/
}
