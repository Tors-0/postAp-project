import org.joml.Vector2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client2 {
    static Player2d p = new Player2d(10,10,70,90,10,10);
    // Creates the drawing panel object
    static DrawingPanel panel = new DrawingPanel(800, 500);

    // Obtains the graphics object that allows for drawing on the panel
    static Graphics g = panel.getGraphics();
    public static void main(String[] args){
        

        
        /*
         * Other methods can be found in the Graphics API: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
         */
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(Client2::update, 0, 50, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(p::update,0,50,TimeUnit.MILLISECONDS);
        
        
        /*
         * 
         */
        
        
    }
    public static void update() {
        Vector2d coord = p.getCoords();
        panel.clearWithoutRepaint();
        g.fillOval((int) Math.round(coord.x)-1,(int) Math.round(coord.y)-1,2,2);
        g.drawOval((int) Math.round(coord.x)-1,(int) Math.round(coord.y)-1,2,2);
    }
}
