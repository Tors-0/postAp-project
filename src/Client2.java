import java.awt.*;

public class Client2 {
    public static void main(String[] args){
        
    	// Creates the drawing panel object
    	DrawingPanel panel = new DrawingPanel(800, 500);
    	
    	// Obtains the graphics object that allows for drawing on the panel
        Graphics g = panel.getGraphics();
        
        /*
         * Exanmple of creating a graphics object.  This creates a 5 x 10 oval
         * in the coordinate of (25, 25)
         * Other methods can be found in the Graphics API: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
         */
        Player p = new Player();
        
        
        /*
         * 
         */
        
        
    }
}
