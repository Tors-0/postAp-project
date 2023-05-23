package Engine;

import Client.Client;
import org.joml.Vector2d;

import java.util.ArrayList;

import static Client.Client.*;

public class Player {
    private Vector2d lastPos;
    private Vector2d currPos;
    private Vector2d vel;
    private Vector2d acc;
    public static ArrayList<Player> players = new ArrayList<Player>();
    private float radius = Client.playerPx / 2.0f;
    private float deltaTime = Client.tps;
    private float mass;
    private float gravC = -15;
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
        Client.panel.setStatusBarText("pos" + player.currPos.toString() + ",vel" + player.getVel().toString() + ",acc" + player.getAcc().toString());
        for (Player p : players) {
            p.physics();
            //p.playerCollision();
            //p.rectCollision();
        }
    }
    public void physics() {
        lastPos = new Vector2d(currPos.x,currPos.y);
        currPos.add(vel.div(deltaTime));
        vel.add(acc);
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravC
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        vel.mul(0.99f);
        acc = new Vector2d(currPos.x-lastPos.x,currPos.y-lastPos.y);
        boundaries();
    }
    public void boundaries() {
        if (currPos.y < radius) { // test lower boundary
            vel.y *= -0.9f;
            vel.x *= 0.9f;
            currPos.y = radius;
        } else if (currPos.y > HEIGHT - radius) { // test upper boundary
            vel.y *= -0.9f;
            vel.x *= 0.9f;
            currPos.y = ((currPos.y - HEIGHT) * -1) + HEIGHT;
        }
        // else-ifs to save time because it's not possible to be in two places at once
        if (currPos.x < radius) { // test left boundary
            vel.x *= -0.9f;
            vel.y *= 0.9f;
            currPos.x *= -1;
        } else if (currPos.x > WIDTH - radius) { // test right boundary
            vel.x *= -0.9f;
            vel.y *= 0.9f;
            currPos.x = ((currPos.x - WIDTH) * -1) + WIDTH;
        }
    }
    public void rectCollision() {

    }
    public void playerCollision() {

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
    public float getRadius() {
        return radius;
    }
    // ------ Uniques ------ //
    public void velocirate(Vector2d v) {
        vel.add(v);
    }
}
