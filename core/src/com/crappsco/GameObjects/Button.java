package com.crappsco.GameObjects;

import com.badlogic.gdx.math.Circle;

/**
 * Created by C on 2016-02-05.
 */
public class Button {
    public static final float radius = 150;
    public float x, y;
    public Circle circle;

    public Button(float xloc, float yloc){
        x = xloc;
        y = yloc;
        circle = new Circle(x + radius, y + radius, radius);
    }

    public boolean contains(float xLoc, float yLoc) {
        if (circle.contains(xLoc, yLoc)) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
