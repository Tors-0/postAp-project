import org.joml.Vector2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player2d {
    KeyListener keys = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
                System.out.println("w");

            } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
                System.out.println("a");

            } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
                System.out.println("s");

            } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
                System.out.println("d");

            } else if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    // player location
    private double lX; // -x left, +x right
    private double lY; // -y up, +y down
    private boolean notOnGround;

    // player velocity dir
    private double vX;
    private double vY;
    private Vector2d velocity;

    // player facing dir
    private double fX;
    private double fY;

    public Player2d(double xPos, double yPos, double xVel, double yVel, double xFac, double yFac) {
        lX = xPos; lY = yPos;
        vX = xVel; vY = yVel; velocity = new Vector2d(xVel,yVel);
        fX = xFac; fY = yFac;
    }
    public Player2d() {
        lX = 0; lY = 0;
        vX = 0; vY = 0; velocity = new Vector2d(vX,vY);
        fX = 0; fY = 0;
    }
    public void update() {
        
        if (notOnGround) {

        } else {

        }
    }
}
