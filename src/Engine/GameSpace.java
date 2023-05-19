package Engine;

import java.util.ArrayList;

public class GameSpace {
    public static ArrayList<Rectangle> GameRectObjs = new ArrayList<>();

    public static void newRectangle(int x1, int y1, int width, int height) {
        new Rectangle(x1,y1,width,height);
    }
}
