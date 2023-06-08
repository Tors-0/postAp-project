package Environment;

import java.util.ArrayList;

public class GameSpace {
    protected int width; // width of the scene
    protected int height; // height of the scene

    /**
     * Initialize the physics scene
     *
     * @param width  width of the physics scene
     * @param height height of the physics scene
     */
    public GameSpace(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get width of PhysScene
     *
     * @return width of scene in px
     */
    public int width() {
        return width;
    }

    /**
     * Get Height of PhysScene
     *
     * @return height of scene in px
     */
    public int height() {
        return height;
    }

    /**
     * List of all Rectangle objects in the current scene
     */
    public ArrayList<PhysObj> GameRectObjs = new ArrayList<>();
    public ArrayList<PhysObj> GameCircObjs = new ArrayList<>();

    /**
     * Create a new rectangle in the scene
     * @param x1 far left x coordinate
     * @param y1 bottom y coordinate
     * @param width width of the rectangle
     * @param height height of  the rectangle
     */
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