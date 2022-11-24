package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class About implements Screen {

    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    SpriteBatch batch, batch1;
    Sprite crossButton;

    public About(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("about.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 960, 1200);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    public void touchHandle() {
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        tankStars.batch.end();

        batch1 = new SpriteBatch();

        batch1.begin();
//        crossButton.draw(batch1);
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

