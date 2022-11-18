package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileReader;

public class MainMenu implements Screen {

    final TankStars game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public MainMenu(final TankStars game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("armored_by_wojtekfus_dbl2y6q-pre.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1236, 600);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        FileHandle in = new FileHandle("font.otf");
        ScreenUtils.clear(0, 0, 0, 0);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SeventiesGroovy-owZ7q.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter2.size = 20;
        BitmapFont font1 = generator.generateFont(parameter);
        BitmapFont font2 = generator.generateFont(parameter2);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.setColor(1, 0, 0, 1);
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        font1.draw(game.batch, "Welcome to Tank Stars!", 200, 340);
        font2.draw(game.batch, "Click anywhere to begin!", 300, 240);
        generator.dispose();
        game.batch.end();


        if (Gdx.input.isTouched()) {
            game.setScreen(new Game(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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

