package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class VictoryScreen implements Screen {

    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    SpriteBatch batch1;
    Sprite victory;
    Sprite restart_button;
    Sprite quit_button;
    Sprite text;

    public VictoryScreen(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("victory1.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 5000, 3000);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void touchHandle() {
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        tankStars.batch.end();

        victory = new Sprite(new Texture("victory.png"));
        victory.setPosition(350, 400);
        victory.setSize(700,500);

        restart_button = new Sprite(new Texture("restart_button.png"));
        restart_button.setPosition(550, 375);
        restart_button.setSize(250,125);

        quit_button = new Sprite(new Texture("quit.png"));
        quit_button.setPosition(550, 200);
        quit_button.setSize(250,140);


        batch1 = new SpriteBatch();

        batch1.begin();
        victory.draw(batch1);
        restart_button.draw(batch1);
        quit_button.draw(batch1);
        batch1.end();

        touchHandle();
    }

    @Override
    public void show() {

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

