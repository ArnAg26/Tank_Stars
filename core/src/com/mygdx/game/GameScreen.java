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

import java.util.Vector;

public class GameScreen implements Screen{

    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    Sprite pauseButton = null;
    SpriteBatch batch1 = null;
    OrthographicCamera camera;
    Vector3 temp = new Vector3();
    private State state = State.RUN;


    public GameScreen(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("GameScreenBG.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1236, 600);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    void touchHandle() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
            System.out.println(temp.x + " lo: " + (pauseButton.getX() - 283.098) + " hi: " + ((pauseButton.getX() + pauseButton.getWidth()) - 342.219));
            System.out.println(" " + temp.y + " lo:" + (pauseButton.getY() - 68.039) + " hi: " + ((pauseButton.getY() + pauseButton.getHeight()) - 123.446));

            if (xtouch >= 25 && xtouch <= 72.223 && ytouch >= 403.819 && ytouch <= 459.561) {
                pause();
            }
            if (state.equals(State.PAUSE) && xtouch >= 25 && xtouch <= 72.223 && ytouch >= 403.819 && ytouch <= 459.561) {
                resume();
            }
        }
    }

    @Override
    public void render(float delta) {

        batch1 = new SpriteBatch();
        pauseButton = new Sprite(new Texture("pause_button.png"));
        pauseButton.setSize(70,70);
        pauseButton.setPosition(28,678);
//        pauseButton.setColor(1,0,0,1);

        ScreenUtils.clear(0, 0, 0, 0);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SeventiesGroovy-owZ7q.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter2.size = 20;
        BitmapFont font1 = generator.generateFont(parameter);
        BitmapFont font2 = generator.generateFont(parameter2);


        camera.update();
        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
//        font1.draw(tankStars.batch, "Game Screen!", 200, 340);
//        font2.draw(tankStars.batch, "lol!", 300, 240);
        generator.dispose();
        tankStars.batch.end();

        batch1.begin();
        pauseButton.draw(batch1);
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
        this.state = State.PAUSE;
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
