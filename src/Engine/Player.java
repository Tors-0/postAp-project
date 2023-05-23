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
    public static ArrayList<Player> players = new ArrayList<>();
    private float radius = Client.playerPx / 2.0f;
    private float deltaTime = Client.tps;
    private float mass;
    private float gravC = -5;
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
        Client.panel.setStatusBarText("pos" + p.currPos.toString() + ",vel" + p.getVel().toString() + ",acc" + p.getAcc().toString());
        players.forEach(p -> {
            p.physics();
            p.rectCollision();
            p.playerCollision();
        });
    }
    public void physics() {
        lastPos = new Vector2d(currPos.x,currPos.y);
        currPos.add(vel.div(deltaTime));
        vel.add(acc.div(deltaTime));
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravitational constant of 9.8 m/s^2
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        boundaries();
    }
    public void boundaries() {
        if (currPos.y < radius) { // test lower boundary
            vel.y *= -0.9;
            vel.x *= 0.9f;
            currPos.y = radius;
        } else if (currPos.y > HEIGHT - radius) { // test upper boundary
            vel.y *= -0.9;
            vel.x *= 0.9f;
            currPos.y = HEIGHT - radius;
        }
        // else-ifs to save time because it's not possible to be in two places at once //
        if (currPos.x < radius) { // test left boundary
            vel.x *= -0.9f;
            vel.y *= 0.9f;
            currPos.x = radius;
        } else if (currPos.x > WIDTH - radius) { // test right boundary
            vel.x *= -0.9f;
            vel.y *= 0.9f;
            currPos.x = WIDTH - radius;
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
}
