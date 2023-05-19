package Engine;

import Client.Client2;
import VerletEngine.VerlObj;
import org.joml.Vector2d;

public class Player extends VerlObj {
    Vector2d tempPos;
    Vector2d currentPos;
    Vector2d vel;
    Vector2d acc;
    float deltaTime = Client2.tps;

    private float mass;

    public Player(float x, float y) {
        tempPos = new Vector2d(x,y);
        currentPos = new Vector2d(x,y);
        vel = new Vector2d(0,0);
        acc = new Vector2d(0,0);
    }
    public void update() {
        // apply dampening to acceleration
        acc.mul(0.95);
        // apply acceleration to velocity
        vel.add(acc);
        // save currentPos to tempPos
        tempPos = new Vector2d(currentPos.x,currentPos.y);
        // apply velocity/deltaTime scaled by the mass of the object
        currentPos = currentPos.mul(vel.mul(mass / deltaTime));
    }
}
