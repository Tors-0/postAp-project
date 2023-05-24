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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public String getShape() {
        return shape;
    }
}
