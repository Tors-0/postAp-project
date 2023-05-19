package Engine;

import VerletEngine.VerlObj;
import org.joml.Vector2d;

public class Player extends VerlObj {

    Vector2d vel;
    Vector2d acc;

    private float mass;

    public Player(float x, float y) {
        super(new Vector2d(x,y));
        vel = new Vector2d(0,0);
        acc = new Vector2d(0,0);
    }
    public void update() {
        acc.mul(0.95);
        vel.add(acc);
        super.setPos(super.getPos().add(vel));
    }
}
