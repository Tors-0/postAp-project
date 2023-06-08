package Client;

import Render.DrawingPanel;
import Environment.GameSpace;
import org.joml.Vector2d;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {
    public static final int WIDTH = 1280; // Width of the screen
    public static final int HEIGHT = 720; // Height of the screen
    // creates the physics scene with the specified width and height
    public static final GameSpace scene = new GameSpace(WIDTH,HEIGHT);
    // Creates the player object
    public static Player player = new Player(WIDTH/2,HEIGHT/2,2);
    // Creates the drawing panel object
    public static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
    // Obtains the graphics object that allows for drawing on the panel
    static Graphics2D g = panel.getGraphics();

    public static final int fps = 15; // SRC of the frames per second var
    public static final int tps = 30; // SRC of the physCalcs per second var
    public static void main(String[] args){

        // create rectangles for the player to interact with
        scene.newRectangle(600,250,201,30);
        scene.newRectangle(480,395,120,20);
        scene.newRectangle(300,365,50,85);
        scene.newRectangle(300,405,200,45);

        // Execute the graphic update and physics update at their respective times
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(Client::update,0,1000/fps,TimeUnit.MILLISECONDS);
        // draw frame fps times per second
        executor.scheduleAtFixedRate(Player::update,0,1000/tps,TimeUnit.MILLISECONDS);
        // run phys calc tps times per second
    }
    // Size of the player in pc
    public static int playerPx = 4;
    // the offset from the center to the drawing start pos
    static int offset = (int) Math.round(playerPx / 2.0);

    /**
     * Updates the graphics display
     */
    public static void update() {
        // Graphics API: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html

        panel.clearWithoutRepaint();
        // make the background my favorite color
        panel.setBackground(Color.pink);

        int offset = (int) player.getRadius();
        // load player coords from method
        Vector2d coord = player.getPos();
        // draw the player
        g.drawOval((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset,playerPx,playerPx);
        scene.GameRectObjs.forEach(current -> { // iterate through all PhysObjs and draw them
            g.drawRect(current.x1, HEIGHT - current.y1 - current.getHeight(), current.getWidth(), current.getHeight());
        });
        // here we do funny stuff so that we can pretend that y=0 is the bottom in our physics calculations
        //g.fillRect((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset,playerPx,playerPx);
        //g.drawOval((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset,playerPx,playerPx);
    }
}
