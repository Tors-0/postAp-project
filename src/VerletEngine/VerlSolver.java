package VerletEngine;

import Client.Client2;
import Engine.VectorC2d;
import org.joml.Vector2d;

import static Client.Client2.p;
import static Client.Client2.scene;

public class VerlSolver {

    private static Vector2d collisionAxis;
    private static float planetMass = Math.pow(5.97219, 24);
    private static VerlObj tempObj1;
    private static VerlObj tempObj2;
    private static float distance;
    private static Vector2d n;
    private static float delta;
    private static Vector2d toObj;
    private static float constraintDist;
    private static Vector2d constraintN;

    /**
     *  Gravity Iter is a separate iteration that is also called on every tick of the simulation that simulates gravity on every object with physics
     *  It does so by performing the law of universal gravitation and it's formula on each physics object
     */
    private static void gravityIter() {
        /* law of universal gravitation = G * ( (m1 * m2) / r*r )
        G = 6.6743 * Math.pow(10,-11) // in units of m^3 * kg^-1 * s^-2
        m1 = mass of object 1 = Math.pow(5.97219,24);
        m2 = mass of object 2
        r = distance between centers of masses
        we could arbitrarily place a very massive object quite far from the render window to create constant downwards gravity
         */
        VerlObj.scenePhysObjs.forEach(e -> {
            e.accelerate(new Vector2d(Client2.WIDTH / 2, 6.6743 * ((planetMass * e.getMass())/11))); // in units of m^3 * kg^-1 * s^-2);
        });
    }

    public static void update() {

        gravityIter();
        verlCollision();
        rectCollision();
        circleConstraint(new Vector2d(Client2.WIDTH, Client2.HEIGHT), 360);

        VerlObj.scenePhysObjs.forEach(VerlObj::iterate);

        Client2.panel.setStatusBarText("pos" + p.currentPos.toString() + ",vel" + p.getVel().toString() + ",acc" + p.getAcceleration().toString());

        // iterate();
        /* DO NOT DELETE! DISABLED FOR TESTING /*
        // apply dampening to acceleration
        // replace scalar with gravitational constant
        acc.y = ((acc.y - gravC) * 0.85) + gravC; // normalize vertical acceleration towards gravitational constant of 9.8 m/s^2
        acc.x *= 0.95; // normalize horizontal acceleration to zero
        // apply acceleration to velocity
        vel = vel.mul(0.99f);
        vel = vel.add(acc.div(mass));
        // apply terminal velocity, when velocity = terminal velocity, gravitational acceleration must be zero

        // save currentPos to tempPos
        this.setTempPos(new VectorC2d(this.getPos().x,this.getPos().y));
        // apply velocity/deltaTime scaled by the mass of the object
        this.setPos(this.getPos().add(vel.div(deltaTime)));

        this.setPos(this.getPos().add(movement.div(deltaTime)));
        */

    }

    private static void circleConstraint(Vector2d pos, float rad) {
        VerlObj.scenePhysObjs.forEach(e -> {
            toObj = e.getPos().sub(pos);
            constraintDist = (float) (e.getPos().distance(toObj));
            if(constraintDist > rad) {
                constraintN = toObj.div(constraintDist);
                e.setPos(pos.add(n.mul(constraintDist -50.0f)));
            }
        });
    }

    private static void verlCollision() {
        for(int i = 0; i < VerlObj.scenePhysObjs.size(); i++) {
            tempObj1 = VerlObj.scenePhysObjs.get(i);
            for(int j = 0; j < VerlObj.scenePhysObjs.size(); j++) {
                if (j == i) break;
                tempObj2 = VerlObj.scenePhysObjs.get(j);
                collisionAxis = tempObj1.getPos().sub(tempObj2.getPos());
                distance = (float) (tempObj1.getPos().distance(tempObj2.getPos()));
                if(distance < 100.0f) {
                    n = collisionAxis.div(distance);
                    delta = 100.0f - distance;
                    VerlObj.scenePhysObjs.get(i).setPos( (VerlObj.scenePhysObjs.get(i).getPos().add(n.mul(delta * 0.5f))));
                    VerlObj.scenePhysObjs.get(j).setPos( (VerlObj.scenePhysObjs.get(j).getPos().sub(n.mul(delta * 0.5f))));
                }
            }
        }
    }

    public static void rectCollision() {
        Vector2d l = Client2.p.getPos();
        Vector2d v = Client2.p.getVel();
        scene.GameRectObjs.forEach(currObj -> {
            if (Math.abs(l.x - currObj.x1) < 10 && currObj.x1 < l.x) {
                if (currObj.y1 < l.y && l.y < currObj.y2) {
                    l.x = currObj.x1;
                    v.x *= -0.95;
                    v.y *= 1.1;
                }
            } else if (Math.abs(l.x - currObj.x2) < 10 && currObj.x2 > l.x) {
                if (currObj.y1 < l.y && l.y < currObj.y2) {
                    l.x = currObj.x2;
                    v.x *= -0.95;
                    v.y *= 1.1;
                }
            } else if (Math.abs(l.y-2 - currObj.y1) < 10 && currObj.y1-2 < l.y) {
                if (currObj.x1 < l.x && l.x < currObj.x2) {
                    l.y = currObj.y1-2;
                    v.y *= -0.9;
                    v.x *= 0.8;
                }
            } else if (Math.abs(l.y - currObj.y2) < 10 && currObj.y2 > l.y) {
                if (currObj.x1 < l.x && l.x < currObj.x2) {
                    l.y = currObj.y2;
                    v.y *= -0.9;
                    v.x *= 0.8;
                }
            }
        }); /*
        if (l.x < 0) { // collide and bounce off of left edge
            l.x = 2;
            v.x *= -0.9;
        } else if (l.x > Client2.WIDTH) { // collide and bounce off of right edge
            l.x = Client2.WIDTH - 2;
            v.x *= -0.9;
        }
        if (l.y > Client2.HEIGHT) { // collide and bounce off of ceiling
            l.y = Client2.HEIGHT - 2;
            v.y *= -0.9;
        } */
    }
}
