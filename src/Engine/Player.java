package Engine;

import Client.Client;
import org.joml.Vector2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Client.Client.*;

public class Player
    implements ImageObserver {
    // ------ Variables ------ //
    private Vector2d lastPos;
    private Vector2d currPos;
    private Vector2d vel;
    private Vector2d acc;
    public static ArrayList<Player> players = new ArrayList<Player>();
    private float radius = Client.playerPx / 2.0f;
    private float deltaTime = Client.tps;
    private float mass;
    private float gravC = -1;
    public static Vector2d mousePos;
    public boolean stopYMomentum;
    public static String status;
    // ------ Texture ------ //
    public static Image texture = Toolkit.getDefaultToolkit().getImage("textures/player.png");
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
    // ------ Constructor ------ //
    public Player(Vector2d pos, float mass) {
        lastPos = pos;
        currPos = pos;
        this.mass = mass;
        vel = new Vector2d(0,0);
        acc = new Vector2d(0,0);
        players.add(this);
    }
    // ------ Solver ------ //
    public static void update() {
        panel.setStatusBarText("pos" + player.currPos.toString() + ",vel" + player.getVel().toString() + ",acc" + player.getAcc().toString() + "mou" + mousePos.toString());
        physics();
        rectCollision();
        playerCollision();
    }
    public void physics() {
        stopYMomentum = false;
        limitVel();
        lastPos = new Vector2d(currPos);
        vel.add(acc);
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravC
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        vel.mul(0.99f);
        setPos(getPos().add(getVel())); // TODO WHY THIS LINE NO WORKY :(
        currPos.x += getVel().x;
        currPos.y += getVel().y;
    }
    public void limitVel() {
        float absVel = (float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2));
        if (absVel > 10) {
            vel.div(absVel).mul(10);
        }
    }
    public static void boundaries() {
        players.forEach(p -> {
            if (p.currPos.y < p.radius) { // test lower boundary
                p.currPos.y = p.lastPos.y;
                /*
                p.vel.y *= -0.7f;
                p.vel.x *= 0.9f;
                p.currPos.y = p.radius;
                 */
            } else if (p.currPos.y > HEIGHT - p.radius) { // test upper boundary
                p.currPos.y = p.lastPos.y;
                /*
                p.vel.y *= -0.9f;
                p.vel.x *= 0.9f;
                p.currPos.y = ((p.currPos.y - HEIGHT) * -1) + HEIGHT;
                 */
            }
            // else-ifs to save time because it's not possible to be in two places at once
            if (p.currPos.x < p.radius) { // test left boundary
                p.currPos.x = p.lastPos.x;
                /*
                p.vel.x *= -0.9f;
                p.vel.y *= 0.9f;
                p.currPos.x *= -1; */
            } else if (p.currPos.x > WIDTH - p.radius) { // test right boundary
                p.currPos.x = p.lastPos.x;
                /*
                p.vel.x *= -0.9f;
                p.vel.y *= 0.9f;
                p.currPos.x = ((p.currPos.x - WIDTH) * -1) + WIDTH; */
            }
        });
    }

    public void rectCollision() {
        scene.GameRectObjs.forEach(r -> {
            if (r.x1 < currPos.x && currPos.x < r.x2 && r.y1 < currPos.y && currPos.y < r.y2) {
                currPos = new Vector2d(lastPos);
            }
            /* old code
                if (Math.abs(p.currPos.x - currObj.x1) < 10 && currObj.x1 < p.currPos.x) { // check left edge
                    if (currObj.y1 < p.currPos.y && p.currPos.y < currObj.y2) {
                        p.currPos.x = p.lastPos.x;
                    }
                } else if (Math.abs(p.currPos.x - currObj.x2) < 10 && currObj.x2 > p.currPos.x) { // check right edge
                    if (currObj.y1 < p.currPos.y && p.currPos.y < currObj.y2) {
                        p.currPos.x = p.lastPos.x;
                    }
                } else if (Math.abs(p.currPos.y - p.radius - currObj.y1) < 10 && currObj.y1 - p.radius < p.currPos.y) {
                    // check bottom
                    if (currObj.x1 < p.currPos.x && p.currPos.x < currObj.x2) {
                        p.currPos.y = p.lastPos.y;
                    }
                } else if (Math.abs(p.currPos.y - currObj.y2) < 10 && currObj.y2 > p.currPos.y) {
                    // check top
                    if (currObj.x1 < p.currPos.x && p.currPos.x < currObj.x2) {
                        p.currPos.y = p.lastPos.y;
                    }
                }
            });
        });
    }
    public static void playerCollision() {

    }
    // ------ Getters + Setters ------ //
    public Vector2d getVel() {
        return vel;
    }
    public Vector2d getAcc() {
        return acc;
    }
    public Vector2d getPos() {
        return currPos;
    }
    public Vector2d getLastPos() {return lastPos;}
    public float getRadius() {
        return radius;
    }
    public void setPos(Vector2d newP) {currPos = newP;}
    public void setTempPos(Vector2d newTP) {lastPos = newTP;}
    public void setAcc(Vector2d newAcc) {acc = newAcc;}
    // ------ Uniques ------ //
    /* Keypress detection is in Render.DrawingPanel @ ~line 100 */
    public void velocirate(Vector2d vel) {
        this.vel.add(vel);
    }
    public void launch() {
        vel = new Vector2d(mousePos.x-currPos.x,(HEIGHT - mousePos.y)-currPos.y).div(Math.sqrt(Math.pow(mousePos.x-currPos.x, 2)
                + Math.pow((HEIGHT - mousePos.y) - currPos.y, 2))).mul(15);
        limitVel();
    }
}
