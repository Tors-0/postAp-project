import VerletEngine.VerletObject;
import org.joml.Vector2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static VerletEngine.VerletObject.VerlPhysObjs;

public class Client2 {
    static Player2d p = new Player2d(400,400,0,0,10,10);
    // Creates the drawing panel object
    static DrawingPanel panel = new DrawingPanel(800, 500);
    static final int fps = 30;
    static final int tps = 30;
    // Obtains the graphics object that allows for drawing on the panel
    static Graphics g = panel.getGraphics();
    public static void main(String[] args){
        
        GameSpace.newRectangle(600,100,50,20);
        
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
    public static void update() {
        Vector2d coord = p.getCoords();
        panel.clearWithoutRepaint();
        g.fillOval((int) Math.round(coord.x)-2,(int) Math.round(coord.y)-2,4,4);
        g.drawOval((int) Math.round(coord.x)-2,(int) Math.round(coord.y)-2,4,4);
        g.drawLine(0,490,800,490);
        VerlPhysObjs.forEach(currentObj ->
                g.drawOval((int)currentObj.getPosCurrent().x - 50,(int)currentObj.getPosCurrent().y - 50,100,100)
        );
    }
}
