package com.crappsco.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crappsco.FrameworkHelpers.AssetLoader;
import com.crappsco.GameObjects.Button;
import com.crappsco.GameObjects.Tile;
import com.crappsco.flip.Flip;

/**
 * Created by Ray on 2016-01-26.
 */
public class LevelSelect implements Screen, InputProcessor {
    final Flip game;
    public static final float WIDTH = 1080;
    public static final float HEIGHT = 1920;
    public static final float tileSpacing = 140;

    private float runTime = 0;

    public Vector3 touchPoint;
    public Viewport viewport;
    public OrthographicCamera camera;
    public Button[] buttons;
    public static Tile[][] currentLevel;
    public static int rowNum, colNum, moves;



    public LevelSelect(Flip game) {

        this.game = game;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        //Set Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera = new OrthographicCamera(25 * aspectRatio, 25);
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);
        viewport.apply();

        //Set ProjectionMatrix
        game.batcher.setProjectionMatrix(camera.combined);

        //Set Buttons
        buttons = new Button[2];
        buttons[0] = new Button(100, 300);
        buttons[1] = new Button(680, 300);
    }

    public void selectLevel(int index){
        index++;
        if(index == 1){
            Level1();
        } else if(index == 2){
            Level2();
        }
    }

// =============================== LEVEL FUNCTIONS =================================================

    public static Tile[][] createLevel(int[][] faces){
        Tile[][] level = new Tile[rowNum][colNum];
        int [][] face = faces;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                level[i][j] = new Tile(tileSpacing * i + (45f + WIDTH - (tileSpacing * colNum))/2, tileSpacing * j + (45f + HEIGHT - (tileSpacing * rowNum))/2, face[i][j], true);
            }
        }
        return level;
    }

    public void Level1() {
        rowNum = 6;
        colNum = 6;
        moves = 10;
        int[][] playerGrid = new int[][]{
                { 1, 1, 1, 1, 1, 1},  // this is column 1 NOT row 1
                { 1, 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1, 1}
        };
        currentLevel = createLevel(playerGrid);
    }

    public void Level2() {
        rowNum = 4;
        colNum = 4;
        moves = 30;
        int[][] playerGrid = new int[][]{
                { 0, 0, 0, 0},  // this is column 1 NOT row 1
                { 0, 0, 0, 0},
                { 0, 0, 0, 0},
                { 0, 0, 0, 0}
        };
        currentLevel = createLevel(playerGrid);
    }

// ================================= UPDATE ========================================================

    @Override
    public void render(float delta) {
        runTime += delta;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batcher.begin();

        game.font.draw(game.batcher, "Level 1", 100, 1880);
        for(int i = 0; i < buttons.length; i++){
            game.batcher.draw(AssetLoader.levelbutton, buttons[i].getX(), buttons[i].getY());
        }

        game.batcher.end();

    }

// ================================== INPUT HANDLER ================================================

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPoint = camera.unproject(new Vector3(screenX, screenY, 0));
        for(int i = 0; i < buttons.length; i++) {
            Button butt = buttons[i];
            if(butt.contains(touchPoint.x, touchPoint.y)){
                selectLevel(i);
            }
        }
        game.setScreen(new GamePanel(game));
        dispose();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            game.setScreen(new MainMenu(game));
            dispose();
        }
        return false;
    }

// ================================= Unused Methods ================================================

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void show() {

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

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
