package Environment;

import static Client.Client2.scene;

public class PhysObj {
    public final int x1;
    public final int x2;
    public final int y1;
    public final int y2;
    private int width;
    private int height;
    private String shape;

    /**
     * A simple constructor for all the parameters
     * @param x1 top left corner x pos
     * @param y1 top left corner y pos
     * @param width width of the object
     * @param height height of the object
     * @param shape the shape of the object, accepts "circle" and "rectangle" currently
     */
    public PhysObj(int x1, int y1, int width, int height, String shape) {
        this.x1 = x1;
        this.x2 = x1 + width - 1;
        this.width = width;
        this.y1 = y1;
        this.y2 = y1 + height - 1;
        this.height = height;
        this.shape = shape;
        scene.GameRectObjs.add(this);
    }

    /**
     * gets the width of the object
     * @return the width as an int
     */
    public int getWidth() {
        return width;
    }

    /**
     * gets the height of the object
     * @return the height as an int
     */
    public int getHeight() {
        return height;
    }

    /**
     * gets the bottom left position
     * @return the X position of the bottom left corner as an int
     */
    public int getX1() {
        return x1;
    }

    /**
     * gets the bottom left corner's y pos
     * @return the y pos as an int
     */
    public int getY1() {
        return y1;
    }

    /**
     * gets the bottom right X position
     * @return an int of the X pos
     */
    public int getX2() {
        return x2;
    }

    /**
     * gets the bottom right corner Y pos of the object
     * @return an int of the Y pos
     */
    public int getY2() {
        return y2;
    }

    /**
     * returns the shape of the object, very important for both drawing and collisions
     * @return the String as a shape
     */
    public String getShape() {
        return shape;
    }
}
