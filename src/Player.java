import org.joml.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    // player coordinates
    private double xP;
    private double yP;
    private double zP;

    // player velocity
    private double vX;
    private double vY;
    private double vZ;
    private Vector4d playerVeloc = new Vector4d(vX,vY,vZ,0);

    // player facing direction
    private double yaw;
    private double pitch;
    private double roll;
    private Vector3d lookDir;


    // is player moving
    public boolean moving;

    public Player(double x, double y, double z, double roll, double pitch, double yaw) {
        xP = x;
        yP = y;
        zP = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    /**
     * updates velocity according to physics
     */
    public void update() {
        if (playerVeloc.equals(0,0,0,0)) {
            moving = false;
        } else {
            moving = true;
            playerVeloc.x *= 0.9;
            playerVeloc.y *= 0.9;
            playerVeloc.z *= 0.85;
            playerVeloc.w *= 0.9;
        }
        if (moving) {
            move(new Vector3d(playerVeloc.x,playerVeloc.y,playerVeloc.z), playerVeloc.w);
        }
    }
    public Player() {
        xP = 0;
        yP = 0;
        zP = 0;
        yaw = 0;
        pitch = 0;
        roll = 0;
    }

    public void launch(Vector3d dir, double pow) {

    }

    public void move(Vector3d dir, double veloc) {
        xP += dir.x * veloc;
        yP += dir.y * veloc;
        zP += dir.z * veloc;
    }

    public Vector3d getLookDir() {
        return lookDir;
    }

    public void keyPressed (KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
            System.out.println("w");
            forwardVelocity += 0.1;
        } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            System.out.println("a");

        } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
            System.out.println("s");

        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            System.out.println("d");

        } else if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
            launch(getLookDir(), 10);
        }
    }


    public Vector4d getForwardVelocity() {
        return VectorUtils.subV4d(playerVeloc,lookDir);
    }



    public void keyTyped (KeyEvent e) {}
    public void keyReleased (KeyEvent e) {}

}
