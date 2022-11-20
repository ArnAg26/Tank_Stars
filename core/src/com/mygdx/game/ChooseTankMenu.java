package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class ChooseTankMenu implements Screen {

    final TankStars tankstars;
    private Texture backgroundImage;
    private TextureRegion bgImageRegion;
    Sprite QuitButton;
    SpriteBatch ss;
    Sprite ResumeButton;
    OrthographicCamera cam;
    Vector3 temp=new Vector3();

    public ChooseTankMenu(TankStars tankstars) {
        this.tankstars = tankstars;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
