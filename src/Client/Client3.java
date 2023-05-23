package Client;

import Engine.ClickMe;
import Render.DrawingPanel;
import org.joml.Vector2d;

import java.awt.*;

public class Client3 {
    static MouseListenerator mll = new MouseListenerator();
    static DrawingPanel panel = new DrawingPanel(500,500);
    static Graphics g = panel.getGraphics();
    public static ClickMe clicks = new ClickMe(new Vector2d(0,0),25);
    public static void main(String[] args) {

    }
    public void render() {

    }
}
