package com.crappsco.FrameworkHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Ray on 2016-01-20.
 */
public class AssetLoader {

    public static Texture bg, levelbutton;
    public static Texture faceup, facedown, play, settings;
    public static Texture loading;

    public static BitmapFont font;

    public static Animation flipAnimation;

    public static boolean finished = false;

    public static void load() {


        bg = new Texture("background.png");
        levelbutton = new Texture(Gdx.files.internal("levelbutton.png"), true);
        faceup = new Texture(Gdx.files.internal("faceup.png"), true);
        facedown = new Texture(Gdx.files.internal("facedown.png"), true);
        loading = new Texture(Gdx.files.internal("loading.png"), true);
        play = new Texture(Gdx.files.internal("play.png"), true);
        settings = new Texture(Gdx.files.internal("settings.png"), true);

        levelbutton.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        faceup.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        facedown.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        loading.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        play.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        settings.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);

        //loading in font file
        FileHandle fontFile = Gdx.files.internal("Dense-Regular.otf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 300;

        font = generator.generateFont(parameter);
        generator.dispose();
        font.setColor(0f, 0f, 0f, 1f);

        finished = true;
    }

    public static void dispose() {

        bg.dispose();
        faceup.dispose();
        facedown.dispose();
    }


}
