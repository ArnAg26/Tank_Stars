package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseMenu implements Screen {
    final TankStars tankstars;
    private Texture backgroundImage;
    private TextureRegion bgImageRegion;
    Sprite QuitButton;
    SpriteBatch ss;
    Sprite ResumeButton;
    OrthographicCamera cam;
    Vector3 temp=new Vector3();

    public PauseMenu(TankStars tankstars) {
        this.tankstars = tankstars;
        this.backgroundImage = new Texture(Gdx.files.internal("PauseScreenBackground.png"));
        this.bgImageRegion=new TextureRegion(backgroundImage,0,0,1236,600);
        cam=new OrthographicCamera();
        cam.setToOrtho(false,800,480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ss=new SpriteBatch();
        QuitButton=new Sprite((new Texture("")));



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
