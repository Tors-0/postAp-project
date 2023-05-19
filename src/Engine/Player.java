package Engine;

import Client.Client2;
import VerletEngine.VerlObj;
import org.joml.Vector2d;

public class Player /*extends VerlObj*/ {

    /*
    IMPORTANT CONSIDERATION: Vector2d.add(v) modifies the original vector, making it useless for comparisons, so I made VectorC2d.add(v),
    which does not modify the passed in vectors and can thus be used for comparisons without modifying the vectors
     */

    VectorC2d tempPos;
    VectorC2d currentPos;
    VectorC2d vel; // units are meters/sec
    VectorC2d acc;
    float deltaTime = Client2.tps;
    boolean onGround = false;

    private float mass = 2.7f; // assume the player is a ping pong ball, mass = 2.7 grams
    private final float terminalVel = 9.5f; // still assuming the player is a ping pong ball, tV = 9.5 m/s
    // phys should normalize velocity towards tV unless another force is acting (keyPress or onSurface)

    public Vector2d getPos() {
        return currentPos;
    }

    public Player(float x, float y) {
        tempPos = new VectorC2d(x,y);
        currentPos = new VectorC2d(x,y);
        vel = new VectorC2d(0,0);
        acc = new VectorC2d(0,0);
    }
    public void update() {
        // apply dampening to acceleration
        // TODO replace scalar with gravitational constant
        /*if (vel.y < 0 && acc.y < 0) {
            acc.y = Math.pow(1.55,vel.y);
        }*/
        // apply acceleration to velocity
        vel = vel.add(acc.div(deltaTime));
        // apply terminal velocity, when velocity = terminal velocity, gravitational acceleration must be zero
        acc.y *= 1 - (acc.y / terminalVel);
        // save currentPos to tempPos
        tempPos = new VectorC2d(currentPos.x,currentPos.y);
        // apply velocity/deltaTime scaled by the mass of the object
        currentPos = currentPos.add(vel.mul(mass / deltaTime));
        gravity();
        collision();
    }
    public void collision() {
        Client2.scene.GameRectObjs.forEach(currentObj -> {

        });
    }
    public void gravity() {
        if (currentPos.y > 2) {
            acc.y = -9.8;
        } else {
            acc.y = 0;
            currentPos.y = 2;
            vel.y *= -0.85;
        }
    }
    public void addForce(Vector2d v) {
        // acceleration = Force / mass
        vel.add(v);
    }
}
