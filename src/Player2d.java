import org.joml.Vector2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Player2d {

    // player location
    private float lX; // -x left, +x right
    private float lY; // -y up, +y down
    private Vector2d coords;
    private boolean notOnGround;

    // player velocity dir
    private float vX;
    private float vY;
    private Vector2d velocity;

    // player facing dir
    private float fX;
    private float fY;
    private int sinceLastJump = 20;
    private int timeBetweenJump = 32; // 20 = 1 sec // min time between player jumps
    public void sides(float x) {
        vX = x;
    }
    public void other(float y) {
        if (sinceLastJump >= timeBetweenJump) {
            vY = y;
            sinceLastJump = 0;
        }
    }

    public Player2d(float xPos, float yPos, float xVel, float yVel, float xFac, float yFac) {
        lX = xPos; lY = yPos; coords = new Vector2d(lX, lY);
        vX = xVel; vY = yVel; velocity = new Vector2d(xVel,yVel);
        fX = xFac; fY = yFac;
    }
    public Player2d() {
        lX = 0; lY = 0; coords = new Vector2d(lX,lY);
        vX = 0; vY = 0; velocity = new Vector2d(vX,vY);
        fX = 0; fY = 0;
    }
    public void update() {
        if (sinceLastJump < timeBetweenJump) sinceLastJump++;
        if (lX < 2) {
            lX = 2;
            vX = -vX;
        }
        if (lX > 798) {
            lX = 798;
            vX = -vX;
        }
        lX += vX/20; lY += vY/20;
        coords = new Vector2d(lX,lY);
        vX *= 0.95;
        if (lY < 488) {
            vY += 5;
        }
        if (lY >= 488) {
            vY = (float) -0.8 * vY;
            lY = 488;
        }
        velocity = new Vector2d(vX,vY);
        if (Math.abs(vX) < 0.1) vX = 0;
        if (Math.abs(vY) < 0.1) vY = 0;
    }
    public Vector2d getCoords() {
        return coords;
    }
    public Vector2d getVelocity() {
        return velocity;
    }
}
