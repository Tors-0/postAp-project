package VerletEngine;

import org.joml.*;

import java.util.ArrayList;

public class VerletObject {
  private Vector2d posCurrent;
  private Vector2d posOld;
  private Vector2d acceleration;

  private Vector2d velocity;

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
    acceleration = null;
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