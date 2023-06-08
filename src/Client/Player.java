package Client;

import org.joml.Vector2d;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static Client.Client.*;

public class Player {
    // ------ Variables ------ //
    private float lastX; // temp X location
    private float lastY; // temp Y location
    private float currX; // current X location
    private float currY; // current Y location
    private float velX; // X velocity
    private float velY; // Y velocity
    private float accX; // X acceleration
    private float accY; // Y acceleration
    public static ArrayList<Player> players = new ArrayList<Player>(); // List of players to iterate through
    private final float radius = (float) (Client.playerPx / 2.0); // radius of player for collisions
    private final float deltaTime = Client.tps; // Delta time is the time between frames, and is used to separate the physics rate from the frame rate
    private float mass; // mass of player to affect gravity, currently unused because a real simulation of gravity was way too much gravity for the tiny gamespace
    private float gravC = -1; // gravitational constant to use instead of and actual gravitational simulation
    public static Vector2d mousePos; // position of the mouse to decide where to launch to
    public boolean stopYMomentum; // decides when you are on a box to prevent downwards movement
    private int launchTimer = 0; // timer for launch cooldown
    private static int launchDura = 45; // how long that cooldown should actually take
    // ------ Constructor ------ //

    /**
     * Player constructor containing simple info about spawn position, and mass
     * @param posX player's starting X pos
     * @param posY player's starting Y pos
     * @param mass how much mass the player has, affects gravity
     */
    public Player(float posX, float posY, float mass) {
        lastX = posX; lastY = posY;
        currX = posX; currY = posY;
        this.mass = mass;
        velX = 0; velY = 0;
        accX = 0; accY = 0;
    }
    // ------ Solver ------ //

    /**
     * Update is the method that runs every physics tick and calls the other methods to solve collisions and stuff
     */
    public static void update() {
        panel.setStatusBarText("pos" + vecStr(player.currX,player.currY) + ",vel" + vecStr(player.velX,player.velY) + ",acc"
                + vecStr(player.accX,player.accY));
        player.launchTimer--;
        player.rectCollision();
        player.physics();
        player.boundaries();
    }
    private static DecimalFormat df = new DecimalFormat("#.###");
    private static String vecStr(float x, float y) {
        return "(" + df.format(x) + ", " + df.format(y) + ")";
    }

    /**
     * physics is a semi-realistic implementation of gravity, and physics that iterate the players based on their velocity
     */
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

    /**
     * limitVel is a simulation of terminal velocity, preventing something from moving faster than possible due to gravity, however our implementation applies to all acceleration, not just gravity
     */
    public void limitVel() {
        float absVel = (float) Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
        if (absVel > 10) {
            velX /= absVel; velX *= 10;
            velY /= absVel; velY *= 10;
        }
    }

    /**
     * boundaries tests player collision with the edges of the gamespace/window
     */
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

    /**
     * rectCollision detects and ideally prevents the player from going into rectangles, but is currently borked, and lets you bug into the rectangle and then get stuck
     */
    public void rectCollision() {
        scene.GameRectObjs.forEach(rec -> {

            //An old method of testing detection, that we left in for reference
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
                        player.currX = player.lastX; //resets player to last position
                    }
                } else if (Math.abs(player.currX - rec.x2) < 10 && rec.x2 > player.currX) { // check right edge
                    if (rec.y1 < player.currY && player.currY < rec.y2) {
                        player.currX = player.lastX; //resets player to last position
                    }
                } else if (Math.abs(player.currY - player.radius - rec.y1) < 10 && rec.y1 - player.radius < player.currY) {
                    // check bottom
                    if (rec.x1 < player.currX && player.currX < rec.x2) {
                        player.currY = player.lastY; //resets player to last position
                    }
                } else if (Math.abs(player.currY - rec.y2) < 10 && rec.y2 > player.currY) {
                    // check top
                    if (rec.x1 < player.currX && player.currX < rec.x2) {
                        player.currY = player.lastY; //resets player to last position
                    }
                }
        });
    }


    // ------ Getters + Setters ------ //

    /**
     * basic getter for velocity
     * @return A vector2d of the velocity
     */
    public Vector2d getVel() {
        return new Vector2d(velX, velY);
    }

    /**
     * basic getter for acceleration for use outside of this class
     * @return a vector2d of the player's velocity
     */
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

    /**
     * getLastPos returns the temp position of the player
     * @return the vector2d of the player
     */
    public Vector2d getLastPos() {
        return new Vector2d(lastX, lastY);
    }

    /**
     * simple getter for the radius of the player
     * @return the radius as a float
     */
    public float getRadius() {
        return radius;
    }

    /**
     * sets the position of the player
     * @param newP the new position for the player
     */
    public void setPos(Vector2d newP) {
        currX = (float) newP.x;
        currY = (float) newP.y;
    }

    /**
     * sets the temp position
     * @param newTP the new temp pos as a 2d vector
     */
    public void setTempPos(Vector2d newTP) {
        lastX = (float) newTP.x;
        lastY = (float) newTP.y;
    }

    /**
     * sets the acceleration
     * @param newAcc the new acceleration as a vector2d
     */
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
        if (launchTimer <= 0) {
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
            launchTimer = launchDura;
        }
    }
}
