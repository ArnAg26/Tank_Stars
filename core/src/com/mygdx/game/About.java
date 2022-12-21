package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.PauseableThread;
import com.badlogic.gdx.utils.ScreenUtils;

public class About implements Screen {
    final PauseMenu pauseScreen;
    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    SpriteBatch batch1;
    Sprite crossButton;
    Sprite text;
    Vector3 temp=new Vector3();
    private static About about;

    public static About getInstance(TankStars tankStars, PauseMenu pauseScreen){
        if(about ==null){
            about=new About(tankStars,pauseScreen);
        }
        return about;

    }
    private About(final TankStars tankStars, final PauseMenu pauseScreen){
        this.tankStars = tankStars;
        this.pauseScreen = pauseScreen;
        backgroundImage = new Texture(Gdx.files.internal("about.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 960, 1200);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void touchHandle() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
            System.out.println(xtouch);
            System.out.println(ytouch);

            if (xtouch >= 17.7 && xtouch <= 51.42 && ytouch >= 374 && ytouch <= 462.6) {
                tankStars.setScreen(pauseScreen);
            }
        }
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        tankStars.batch.end();

        text = new Sprite(new Texture("text4.png"));
        text.setPosition(250, 200);
        text.setSize(800,400);

        crossButton = new Sprite(new Texture("back.png"));
        crossButton.setPosition(25, 690);
        crossButton.setSize(70,70);

        batch1 = new SpriteBatch();

        batch1.begin();
        crossButton.draw(batch1);
        text.draw(batch1);
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

