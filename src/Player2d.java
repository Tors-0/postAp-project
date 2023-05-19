import org.joml.Vector2d;
import org.lwjgl.vulkan.VkXcbSurfaceCreateInfoKHR;

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
    private int timeBetweenJump = 45; // 20 = 1 sec // min time between player jumps
    public void sides(float x) {
        vX += x;
        if (vX > 100) vX = 100;
        if (vX < -100) vX = -100;
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
        coords = new Vector2d(lX,lY); // end coords calc

        GameSpace.GameRectObjs.forEach(currObj -> {
            if (currObj.x1 < this.lX+2 && this.lX-2 < currObj.x2) {
                if (currObj.y1 < this.lY+2 && this.lY-2 < currObj.y2) {
                    if (this.vY < 0) {
                        lY = currObj.y2+2;
                        vY *= -0.9;
                        vX *= 1.1;
                    } else {
                        lY = currObj.y1-2;
                        vY *= -0.8;
                        vX *= 1.1;
                    }
                }
            }
            if (currObj.y1 < this.lY+2 && this.lY-2 < currObj.y2) {
                if (currObj.x1 < this.lX+2 && this.lX-2 < currObj.x2) {
                    if (this.vX < 0) {
                        lX = currObj.x2 + 2;
                        vX *= -0.95;
                        vY *= 1.1;
                    } else {
                        lX = currObj.x1 - 2;
                        vX *= -0.95;
                        vY *= 1.1;
                    }
                }
            }
        });

        // start vel calc
        vX *= 0.95;
        if (lY < 488) {
            vY += 5;
        }
        if (lY >= 488) {
            vY = (float) -0.8 * vY;
            lY = 488;
            vX *= 0.95;
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
