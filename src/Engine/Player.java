package Engine;

import Client.Client2;
import VerletEngine.VerlObj;
import org.joml.Vector2d;

import static Client.Client2.scene;

public class Player /*extends VerlObj*/ {

    /*
    IMPORTANT CONSIDERATION: Vector2d.add(v) modifies the original vector, making it useless for comparisons, so I made VectorC2d.add(v),
    which does not modify the passed in vectors and can thus be used for comparisons without modifying the vectors
     */

    VectorC2d tempPos;
    VectorC2d currentPos;
    VectorC2d vel; // units are meters/sec
    VectorC2d acc = new VectorC2d(0,-9.8);
    float deltaTime = Client2.tps;
    boolean onGround = false;

    private float mass = 2.7f; // assume the player is a ping pong ball, mass = 2.7 grams
    private final float terminalVel = 9.5f; // still assuming the player is a ping pong ball, tV = 9.5 m/s
    // phys should normalize velocity towards tV unless another force is acting (keyPress or onSurface)
    private final float gravC = -5; // gravitational constant, -9.8 is earth's but is too strong for this

    public Vector2d getPos() {
        return currentPos;
    }

    public Player(float x, float y) {
        tempPos = new VectorC2d(x,y);
        currentPos = new VectorC2d(x,y);
        vel = new VectorC2d(0,0);
    }
    public void update() {
        // apply dampening to acceleration
        // TODO replace scalar with gravitational constant
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravitational constant of 9.8 m/s^2
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        // apply acceleration to velocity
        vel = vel.add(acc.div(mass));
        vel = vel.mul(0.9f);
        // apply terminal velocity, when velocity = terminal velocity, gravitational acceleration must be zero

        // save currentPos to tempPos
        tempPos = new VectorC2d(currentPos.x,currentPos.y);
        // apply velocity/deltaTime scaled by the mass of the object
        currentPos = currentPos.add(vel.div(deltaTime));
        gravity();
        collision();
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
    public void addForce(Vector2d v) {
        // acceleration = Force / mass
        acc.add(v.div(mass));
    }
}
