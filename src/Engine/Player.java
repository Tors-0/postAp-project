package Engine;

import VerletEngine.VerlObj;

public class Player extends VerlObj {

    /*
    IMPORTANT CONSIDERATION: Vector2d.add(v) modifies the original vector, making it useless for comparisons, so I made VectorC2d.add(v),
    which does not modify the passed in vectors and can thus be used for comparisons without modifying the vectors
     */
    /*
    VectorC2d currentPos;
    VectorC2d tempPos;
     */


    public Player(float x, float y) {
        super(new VectorC2d(x,y));
        this.setTempPos(new VectorC2d(x,y));
        this.setVel(new VectorC2d(0,0));
    }

}
