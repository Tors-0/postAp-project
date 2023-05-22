package Client;

import Engine.Player;
import Engine.VectorC2d;
import Render.DrawingPanel;
import Environment.GameSpace;
import VerletEngine.VerlObj;
import VerletEngine.VerlSolver;
import org.joml.Vector2d;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client2 {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    // creates the physics scene with the specified width and height
    public static final GameSpace scene = new GameSpace(WIDTH,HEIGHT);
    // Creates the player object
    public static VerlObj p = new VerlObj(new Vector2d(WIDTH/2,HEIGHT/2),2,2.7f);
    public static VerlObj obj1 = new VerlObj();
    public static VerlObj obj2 = new VerlObj(new Vector2d(500, 250), 50, 10.0f);

    // Creates the drawing panel object
    public static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
    // Obtains the graphics object that allows for drawing on the panel
    static Graphics g = panel.getGraphics();

    public static final int fps = 30;
    public static final int tps = 30;
    public static void main(String[] args){

        scene.newRectangle(600,350,201,20);
        scene.newRectangle(500,395,100,20);
        scene.newRectangle(300,405,50,85);
        scene.newRectangle(300,405,200,45);


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(Client2::update,0,1000/fps,TimeUnit.MILLISECONDS);
        // draw frame fps times per second
        executor.scheduleAtFixedRate(VerlSolver::update,0,1000/tps,TimeUnit.MILLISECONDS);
        // run phys calc tps times per second

        /*
         * 
         */
        
        
    }
    static int playerPx = 4;
    static int offset = (int) Math.round(playerPx / 2.0);
    public static void update() {
        // Graphics API: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
        Vector2d coord = p.getPos();
        panel.clearWithoutRepaint();
        VerlObj.scenePhysObjs.forEach(o -> {
            int offset = o.getRadius();
            g.drawOval((int) o.getPos().x, (int) (HEIGHT - o.getPos().y - (2 * offset)),2 * offset,2 * offset);
        });
        scene.GameRectObjs.forEach(current -> {
            g.drawRect(current.x1, HEIGHT - current.y1 - current.getHeight(), current.getWidth(), current.getHeight());
        });
        // here we do funny stuff so that we can pretend that y=0 is the bottom in our physics calculations
        g.fillRect((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset,playerPx,playerPx);
        g.drawRect((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset,playerPx,playerPx);
        //g.drawLine((int) Math.round(coord.x)-offset,HEIGHT - (int) Math.round(coord.y)-offset, (int) p.getFacingPos().x, (int) p.getFacingPos().y);
    }
}
