package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.TweenAcessors.SpriteAccessor;
import com.crappsco.TweenAcessors.Value;
import com.crappsco.flip.Flip;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Ray on 2016-01-29.
 */
public class LoadingScreen implements Screen {
    public Viewport viewport;
    public OrthographicCamera camera;
    public final float WIDTH = 1080;
    public final float HEIGHT = 1920;

    private float runTime = 0;

    public Vector3 touchPoint;
    public Value value;
    private TweenManager tweenManager;

    final Flip game;

    private Sprite loading;

    public LoadingScreen(Flip game) {

        this.game = game;

        //Set Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera = new OrthographicCamera(25 * aspectRatio, 25);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);
        viewport.apply();

        //Set ProjectionMatrix
        game.batcher.setProjectionMatrix(camera.combined);

        loading = new Sprite(AssetLoader.loading);
        loading.setColor(1, 1, 1, 0);
        loading.setPosition((WIDTH / 2) - (loading.getWidth() / 2), (HEIGHT / 2)
                - (loading.getHeight() / 2));

        value = new Value();

        tweenSetup();
    }



    public void tweenSetup() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        tweenManager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MainMenu(game));
            }
        };
        Tween.setCombinedAttributesLimit(5);
        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(loading, SpriteAccessor.ALPHA, 2f).target(1).ease(TweenEquations.easeInQuad))
                .push(Tween.to(loading, SpriteAccessor.ROTATION, 2f).target(360).ease(TweenEquations.easeInQuad))
                .end()
                .push(Tween.to(loading, SpriteAccessor.ALPHA, 2f).target(0).ease(TweenEquations.easeInQuad))
                .repeat(1, .6f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(tweenManager);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        tweenManager.update(delta);

        runTime += delta;
        update(delta);

        //****************************************RENDER SECTION****************************************
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batcher.begin();
        loading.draw(game.batcher);
        game.batcher.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
