package Environment;

import java.util.ArrayList;

public class GameSpace {
    protected int width;
    protected int height;
    public GameSpace(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }

    public static ArrayList<Rectangle> GameRectObjs = new ArrayList<>();

    public void newRectangle(int x1, int y1, int width, int height) {
        GameRectObjs.add(new Rectangle(x1,y1,width,height));
    }


}
