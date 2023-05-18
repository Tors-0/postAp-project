import org.joml.Vector2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Player2d {

    // player location
    private double lX; // -x left, +x right
    private double lY; // -y up, +y down
    private Vector2d coords;
    private boolean notOnGround;

    // player velocity dir
    private double vX;
    private double vY;
    private Vector2d velocity;

    // player facing dir
    private double fX;
    private double fY;

    public Player2d(double xPos, double yPos, double xVel, double yVel, double xFac, double yFac) {
        lX = xPos; lY = yPos; coords = new Vector2d(lX, lY);
        vX = xVel; vY = yVel; velocity = new Vector2d(xVel,yVel);
        fX = xFac; fY = yFac;
    }
    public Player2d() {
        lX = 0; lY = 0;
        vX = 0; vY = 0; velocity = new Vector2d(vX,vY);
        fX = 0; fY = 0;
    }
    public void update() {
        lX += vX/20; lY += vY/20;
        coords = new Vector2d(lX,lY);
        vX *= 0.95; vY *= 0.9;
        velocity = new Vector2d(vX,vY);
        if (vX < .02) vX = 0;
        if (vY < .02) vY = 0;
    }
    public Vector2d getCoords() {
        return coords;
    }
    public void move(Vector2d m) {
        coords.add(m);
    }
    public void addVelocity(double x, double y) {
        vX += x;
        vY += y;
    }
}
