package com.crappsco.TweenAcessors;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Ray on 2016-01-29.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {

    public static final int ALPHA = 1;
    public static final int ROTATION = 2;
    public static final int SCALEXY = 3;
    public static final int POSY = 5;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            case ROTATION:
                returnValues[0] = target.getRotation();
                return 2;
            case SCALEXY:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 3;
            case POSY:
                returnValues[0] = target.getY();
            default:
                return 0;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case ALPHA:
                target.setColor(1, 1, 1, newValues[0]);
                break;
            case ROTATION:
                target.setRotation(newValues[0]);
                break;
            case SCALEXY:
                target.setScale(newValues[0], newValues[1]);
                break;
            case POSY:
                target.setY(newValues[0]);
                break;
        }
    }
}
