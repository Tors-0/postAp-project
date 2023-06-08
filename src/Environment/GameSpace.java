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

    public ArrayList<PhysObj> GameRectObjs = new ArrayList<>();

    public void newRectangle(int x1, int y1, int width, int height) {
        GameRectObjs.add(new PhysObj(x1, y1, width, height, "Rectangle"));
    }

    /**
     * Create a new circle in the scene
     * @param x center x coordinate
     * @param y center y coordinate
     * @param radius radius of the circle
     */
    public void newSphere(int x, int y, int radius) {
        GameCircObjs.add(new PhysObj(x-radius,y-radius,radius*2,radius*2,"circle"));
    }
}