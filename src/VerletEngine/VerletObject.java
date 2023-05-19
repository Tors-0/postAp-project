package VerletEngine;

import org.joml.*;

import java.util.ArrayList;

public class VerletObject {
  private Vector2d posCurrent = new Vector2d(400,250);
  private Vector2d posOld = new Vector2d(0,0);
  private Vector2d acceleration = new Vector2d(0,0);

  private Vector2d velocity = new Vector2d(0,0);

  private static int objCount;

  public static ArrayList<VerletObject> VerlPhysObjs = new ArrayList<VerletObject>();

  public void updatePosition(float dt) {
    //Compute last velocity
    velocity = posCurrent.sub(posOld); //- posOld;

    //save current pos
    posOld = posCurrent;

    //now perform Verlet integration
    posCurrent = posCurrent.add(velocity.add(acceleration.mul(2 * dt)));// + velocity + acceleration * dt * dt;

    //reset acceleration
    acceleration = new Vector2d(0,0);
  }

  public void accelerate(Vector2d acc) {
    acceleration = acceleration.add(acc);
  }

  {
    objCount++;
  }

  public int getCount() {
    return objCount;
  }

  public Vector2d getPosCurrent() {
    return posCurrent;
  }

  public void setPosCurrent(Vector2d pC) {
    posCurrent = pC;
  }

  public VerletObject(Vector2d pos) {
    posCurrent = pos;

    VerlPhysObjs.add(this);
  }
}