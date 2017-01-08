package com.crappsco.GameObjects;

import com.badlogic.gdx.math.Circle;


/**
 * Created by C on 2016-01-16.
 */
public class Tile {
    public static final float radius = 45;
    public float x, y;
    public int face;
    public boolean flippable;
    public Circle circle;

    public Tile(float xloc, float yloc, int face, boolean flippable) {
        x = xloc;
        y = yloc;
        this.face = face;
        this.flippable = flippable;
        circle = new Circle(x + radius, y + radius, radius);
    }


    public boolean contains(float xLoc, float yLoc) {
        if (circle.contains(xLoc, yLoc)) {
            return true;
        } else {
            return false;
        }
    }

    public Tile flipTile(float xLoc, float yLoc, int face, boolean flippable) {
        Tile flippedTile;
        if(flippable) {
            if (face == 0) {
                flippedTile = new Tile(xLoc, yLoc, 1, flippable);
            } else {
                flippedTile = new Tile(xLoc, yLoc, 0, flippable);
            }
        }
        else{
            flippedTile = new Tile(xLoc, yLoc, face, flippable);
        }
        return flippedTile;
    }

    public int getFace() {
        return face;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean getFlippable(){
        return flippable;
    }
}