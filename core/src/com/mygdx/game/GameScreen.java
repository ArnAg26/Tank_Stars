package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.net.PasswordAuthentication;
import java.util.Vector;

public class GameScreen implements Screen{

    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    Sprite pauseButton = null;
    Sprite terrain;
    Sprite tank1;
    Sprite tank2;
    Sprite health1;
    Sprite health2;
    Sprite fire_button;
    Sprite fire_button_circle;
    Sprite tankStarslogo;
    Sprite left_arrow;
    Sprite right_arrow;
    Sprite angle;
    SpriteBatch batch1 = null;
    OrthographicCamera camera;
    Vector3 temp = new Vector3();
    private State state = State.RUN;


    public GameScreen(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1000);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    void touchHandle() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
//            System.out.println(temp.x + " lo: " + (pauseButton.getX() - 283.098) + " hi: " + ((pauseButton.getX() + pauseButton.getWidth()) - 342.219));
//            System.out.println(" " + temp.y + " lo:" + (pauseButton.getY() - 68.039) + " hi: " + ((pauseButton.getY() + pauseButton.getHeight()) - 123.446));

            if (xtouch >= 25 && xtouch <= 55.223 && ytouch >= 424.819 && ytouch <= 459.561) {
                pause();
            }
//            if (state.equals(State.PAUSE) && xtouch >= 25 && xtouch <= 72.223 && ytouch >= 403.819 && ytouch <= 459.561) {
//                resume();
//            }
        }
    }

    @Override
    public void render(float delta) {

        batch1 = new SpriteBatch();
        pauseButton = new Sprite(new Texture("pause_button.png"));
        pauseButton.setSize(70,70);
        pauseButton.setPosition(28,678);
//        pauseButton.setColor(1,0,0,1);

        terrain = new Sprite(new Texture("terrain_blue.png"));
        terrain.setPosition(0,0);
        terrain.setSize(1400,450);

        tank1 = new Sprite(new Texture("mark_revert.png"));
        tank1.setPosition(750,240);
        tank1.setSize(150,100);

        tank2 = new Sprite(new Texture("siedge.gif"));
        tank2.setPosition(50,275);
        tank2.setSize(120,100);

        health1 = new Sprite(new Texture("health1.png"));
        health1.setPosition(450,705);
        health1.setSize(200,50);

        health2 = new Sprite(new Texture("health2.png"));
        health2.setPosition(750,700);
        health2.setSize(200,45);

        tankStarslogo = new Sprite(new Texture("logo.png"));
        tankStarslogo.setPosition(660,690);
        tankStarslogo.setSize(85,75);

        fire_button = new Sprite(new Texture("fire.png"));
        fire_button.setPosition(627,20);
        fire_button.setSize(125,125);

        fire_button_circle = new Sprite(new Texture("circle6.png"));
        fire_button_circle.setPosition(200,-290);
        fire_button_circle.setSize(1200,700);

        left_arrow = new Sprite(new Texture("left_arrow.png"));
        left_arrow.setPosition(75,50);
        left_arrow.setSize(75,75);

        right_arrow = new Sprite(new Texture("right_arrow.png"));
        right_arrow.setPosition(200,50);
        right_arrow.setSize(75,75);


        ScreenUtils.clear(0, 0, 0, 0);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SeventiesGroovy-owZ7q.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter2.size = 20;

        camera.update();
        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        generator.dispose();
        tankStars.batch.end();
        batch1.begin();
        tank1.draw(batch1);
        health1.draw(batch1);
        health2.draw(batch1);
        tank2.draw(batch1);
        pauseButton.draw(batch1);
        terrain.draw(batch1);
        tankStarslogo.draw(batch1);
        fire_button_circle.draw(batch1);
        fire_button.draw(batch1);
        left_arrow.draw(batch1);
        right_arrow.draw(batch1);
        batch1.end();

        switch (state)
        {
            case RUN:
                for(int i = 0; i < 10000; i++){
                    System.out.println(1);
                }
                break;
            case PAUSE:
                pause();
                break;
            case RESUME:
                resume();
                break;

            default:
                break;
        }

        touchHandle();

//        Window pause = new Window("Pause", skin);

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }


    public enum State
    {
        PAUSE,
        RUN,
        RESUME,
    }

    public void setGameState(State s){
        this.state = s;
    }
    @Override
    public void pause() {
        tankStars.setScreen(new PauseMenu(tankStars, this));
    }

    @Override
    public void resume() {
        this.state = State.RUN;
    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
