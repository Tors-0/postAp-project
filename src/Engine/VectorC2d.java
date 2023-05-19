package Engine;

import org.joml.Vector2d;

public class VectorC2d extends Vector2d {

    public VectorC2d(double x, double y) {
        super(x,y);
    }

    public VectorC2d() {
        super();
    }

    public VectorC2d sub(VectorC2d v) {
        return new VectorC2d(this.x-v.x(),this.y-v.y());
    }
    public VectorC2d add(VectorC2d v) {
        return new VectorC2d(this.x+v.x,this.y+v.y);
    }
    public VectorC2d mul(VectorC2d v) {
        return new VectorC2d(this.x*v.x,this.y*v.y);
    }
    public VectorC2d mul(float scalar) {
        return new VectorC2d(this.x*scalar,this.y*scalar);
    }
    public VectorC2d div(float scalar) {
        return new VectorC2d(this.x/scalar,this.y/scalar);
    }
    public VectorC2d div(VectorC2d v) {
        return new VectorC2d(this.x/v.x,this.y/v.y);
    }
}
