package Client;

import org.joml.Vector2d;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static Client.Client.*;

public class Player {
    // ------ Variables ------ //
    private float lastX;
    private float lastY;
    private float currX;
    private float currY;
    private float velX;
    private float velY;
    private float accX;
    private float accY;
    public static ArrayList<Player> players = new ArrayList<Player>();
    private final float radius = (float) (Client.playerPx / 2.0);
    private final float deltaTime = Client.tps;
    private float mass;
    private float gravC = -1;
    public static Vector2d mousePos;
    public boolean stopYMomentum;
    private int launchCool = 0;
    private static int launchTimer = 45;
    // ------ Constructor ------ //
    public Player(float posX, float posY, float mass) {
        lastX = posX; lastY = posY;
        currX = posX; currY = posY;
        this.mass = mass;
        velX = 0; velY = 0;
        accX = 0; accY = 0;
    }
    // ------ Solver ------ //
    public static void update() {
        panel.setStatusBarText("pos" + vecStr(player.currX,player.currY) + ",vel" + vecStr(player.velX,player.velY) + ",acc"
                + vecStr(player.accX,player.accY));
        player.launchCool--;
        player.rectCollision();
        player.physics();
        player.boundaries();
        player.playerCollision();
    }
    private static DecimalFormat df = new DecimalFormat("#.###");
    private static String vecStr(float x, float y) {
        return "(" + df.format(x) + ", " + df.format(y) + ")";
    }
    public void physics() {
        stopYMomentum = false;
        limitVel();
        lastX = currX; lastY = currY;
        velX += accX; velY += accY;
        accY = (float) ((accY - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravC
        accX *= 0.95; // normalize horizontal acceleration to zero
        velX *= 0.99f; velY *= 0.99f;
        currX += velX;
        currY += velY;
    }
    public void limitVel() {
        float absVel = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
        if (absVel > 10) {
            velX /= absVel; velX *= 10;
            velY /= absVel; velY *= 10;
        }
    }
    public void boundaries() {
            if (currY < 7) { // test lower boundary
                currY = 7;
            } else if (currY > HEIGHT - radius) { // test upper boundary
                currY = HEIGHT - radius;
            }
            // else-ifs to save time because it's not possible to be in two places at once
            if (currX < radius) { // test left boundary
                currX = radius;
            } else if (currX > WIDTH - 7) { // test right boundary
                currX = WIDTH - 7;
            }
    }
    float newX, newY;
    public void rectCollision() {
        scene.GameRectObjs.forEach(rec -> {
            /*
            if (rec.x1 < currX && currX < rec.x2 && rec.y1 < currY && currY < rec.y2) {
                // check if player is inside the rect
                newX = currX; newY = currY;
                if (lastY > rec.y2) { // check if player was above rectangle
                    newY = rec.y2 - radius; // put player on top of rectangle
                    velY = 0;
                    System.out.println("top detect");
                } else if (lastY < rec.y1) { // check if player was below rect
                    newY = rec.y1 + radius; // put player on bottom of rect
                    velY = 0;
                    System.out.println("bottom detect");
                }
                if (lastX < rec.x1) { // check if player was left of rect
                    newX = rec.x1 - radius; // put player to left of rect
                    velX = 0;
                    System.out.println("left detect");
                } else if (lastX > rec.x2) { // check if player was right of rect
                    newX = rec.x2 + radius; // put player to right of rect
                    velX = 0;
                    System.out.println("right detect");
                }
                player.currX = newX; player.currY = newY;
            }
            */
                if (Math.abs(player.currX - rec.x1) < 10 && rec.x1 < player.currX) { // check left edge
                    if (rec.y1 < player.currY && player.currY < rec.y2) {
                        player.currX = player.lastX;
                    }
                } else if (Math.abs(player.currX - rec.x2) < 10 && rec.x2 > player.currX) { // check right edge
                    if (rec.y1 < player.currY && player.currY < rec.y2) {
                        player.currX = player.lastX;
                    }
                } else if (Math.abs(player.currY - player.radius - rec.y1) < 10 && rec.y1 - player.radius < player.currY) {
                    // check bottom
                    if (rec.x1 < player.currX && player.currX < rec.x2) {
                        player.currY = player.lastY;
                    }
                } else if (Math.abs(player.currY - rec.y2) < 10 && rec.y2 > player.currY) {
                    // check top
                    if (rec.x1 < player.currX && player.currX < rec.x2) {
                        player.currY = player.lastY;
                    }
                }
        });
    }
    public void playerCollision() {

    }
    // ------ Getters + Setters ------ //
    public Vector2d getVel() {
        return new Vector2d(velX, velY);
    }
    public Vector2d getAcc() {
        return new Vector2d(accX, accY);
    }

    /**
     * Get the current position of the player
     * @return Vector2d with x and y coordinates set to those of the player
     */
    public Vector2d getPos() {
        return new Vector2d(currX,currY);
    }
    public Vector2d getLastPos() {
        return new Vector2d(lastX, lastY);
    }
    public float getRadius() {
        return radius;
    }
    public void setPos(Vector2d newP) {
        currX = (float) newP.x;
        currY = (float) newP.y;
    }
    public void setTempPos(Vector2d newTP) {
        lastX = (float) newTP.x;
        lastY = (float) newTP.y;
    }
    public void setAcc(Vector2d newAcc) {
        accX = (float) newAcc.x;
        accY = (float) newAcc.y;
    }
    // ------ Uniques ------ //
    /* Key-press detection is in Render.DrawingPanel at about line 100 */

    /**
     * Change to player's velocity to a new direction
     * @param vel Vector2d containing the new directions to add to the player's movement
     */
    public void velocirate(Vector2d vel) {
        velX += vel.x;
        velY += vel.y;
    }

    /**
     * Launch the player towards the mouse cursor, cooldown configurable
     */
    public void launch() {
        if (launchCool <= 0) {
            velX = (float) (mousePos.x - currX);
            velY = (float) (HEIGHT - mousePos.y) - currY;
            float absVel = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
            if (absVel > 10) {
                float div = (float) Math.sqrt(Math.pow(mousePos.x - currX, 2) + Math.pow((HEIGHT - mousePos.y) - currY, 2));
                velX /= div;
                velX *= 15;
                velY /= div;
                velY *= 15;
            }
            limitVel();
            launchCool = launchTimer;
        }
    }
}
