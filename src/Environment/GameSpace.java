package Environment;

import java.util.ArrayList;

public class GameSpace {
    final public static int winWidth = 1600;
    final public static int winHeight = 900;

    public static ArrayList<Rectangle> GameRectObjs = new ArrayList<>();

    public static void newRectangle(int x1, int y1, int width, int height) {
        new Rectangle(x1,y1,width,height);
    }
}
