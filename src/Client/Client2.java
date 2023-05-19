package Client;

import Controls.Player2d;
import Engine.DrawingPanel;
import Engine.GameSpace;
import org.joml.Vector2d;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Client2 {
    public static Player2d p = new Player2d(400,200,0,0,0,0);
    // Creates the drawing panel object
    static DrawingPanel panel = new DrawingPanel(800, 500);
    static final int fps = 30;
    static final int tps = 30;
    // Obtains the graphics object that allows for drawing on the panel
    static Graphics g = panel.getGraphics();
    public static void main(String[] args){
        
        GameSpace.newRectangle(600,350,201,20);
        GameSpace.newRectangle(500,395,100,20);
        GameSpace.newRectangle(300,405,50,85);
        GameSpace.newRectangle(300,405,200,45);

        /*
         * Other methods can be found in the Graphics API: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
         */
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(Client2::update, 0, 1000/fps, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(p::update,0,1000/tps,TimeUnit.MILLISECONDS);

        /*
         * 
         */
        
        
    }
    static int playerPx = 4;
    static int offset = (int) Math.round(playerPx / 2.0);
    public static void update() {
        Vector2d coord = p.getCoords();
        panel.clearWithoutRepaint();
        GameSpace.GameRectObjs.forEach(current -> g.drawRect(current.x1, current.y1, current.getWidth(), current.getHeight()));
        g.fillRect((int) Math.round(coord.x)-offset,(int) Math.round(coord.y)-offset,playerPx,playerPx);
        g.drawRect((int) Math.round(coord.x)-offset,(int) Math.round(coord.y)-offset,playerPx,playerPx);
        g.drawLine(0,490,800,490);
    }
}
