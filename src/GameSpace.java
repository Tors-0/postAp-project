import java.util.ArrayList;

public class GameSpace {
    public static ArrayList<Rectangle> GameRectObjs = new ArrayList<>();

    public static void newRectangle(int x1, int y1, int width, int height) {
        new Rectangle(x1,y1,width,height);
    }
}
class Rectangle {
    final int x1;
    final int x2;
    final int y1;
    final int y2;
    private int width;
    private int height;
    public Rectangle(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.x2 = x1 + width - 1;
        this.width = width;
        this.y1 = y1;
        this.y2 = y1 + height - 1;
        this.height = height;
        GameSpace.GameRectObjs.add(this);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
