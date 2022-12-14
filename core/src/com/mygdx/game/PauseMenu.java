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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PauseMenu implements Screen {
    final TankStars tankstars;
    final GameScreen gameScreen;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    SpriteBatch batch;
    SpriteBatch batch1;
    Sprite resumeButton;
    Sprite aboutButton;
    Sprite save;
    Sprite quitButton;
    OrthographicCamera camera;
    Vector3 temp=new Vector3();
    private static PauseMenu pausemenu;

    public static PauseMenu getInstance(TankStars tankstars,GameScreen gs){
        if(pausemenu==null){
            pausemenu=new PauseMenu(tankstars,gs);
        }
        return pausemenu;
    }

    private PauseMenu(TankStars tankstars, GameScreen gameScreen) {
        this.tankstars = tankstars;
        this.gameScreen = gameScreen;
        this.backgroundImage = new Texture(Gdx.files.internal("background1.jpg"));
        this.backgroundTexture = new TextureRegion(backgroundImage,0,0,1920,1080);
        camera=new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void show() {

    }

    private void touchHandle() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
            System.out.println(temp.x );
            System.out.println(temp.y );

            if (xtouch >= 328.31 && xtouch <= 478.28 && ytouch >= 320.8 && ytouch <= 374) {
                resume();
            }

            if (xtouch >= 328.31 && xtouch <= 478.28 && ytouch >= 227.3 && ytouch <= 284.2) {
                tankstars.setScreen(About.getInstance(tankstars,this));
            }

            if (xtouch >= 328.31 && xtouch <= 478.28 && ytouch >= 131.9 && ytouch <= 193.2) {
                tankstars.setScreen(new MainMenu(tankstars));
            }

            if (xtouch >= 334.2 && xtouch <= 470.85 && ytouch >= 55.12 && ytouch <= 102.8) {
                serialize();
                tankstars.setScreen(new MainMenu(tankstars));

            }
        }
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();

        tankstars.batch.setProjectionMatrix(camera.combined);
        tankstars.batch.begin();
        tankstars.batch.draw(backgroundTexture, 0,0, 800, 480);
        tankstars.batch.end();

        resumeButton = new Sprite(new Texture("resume_button.png"));
        resumeButton.setPosition(550, 500);
        resumeButton.setSize(300,125);

        aboutButton = new Sprite(new Texture("abouts_button.png"));
        aboutButton.setPosition(550, 350);
        aboutButton.setSize(300,125);

        quitButton = new Sprite(new Texture("quit_button.png"));
        quitButton.setPosition(550, 200);
        quitButton.setSize(300,125);

        save = new Sprite(new Texture("save.png"));
        save.setPosition(160, -200);
        save.setSize(1100,550);

        batch1 = new SpriteBatch();

        batch1.begin();
        resumeButton.draw(batch1);
        aboutButton.draw(batch1);
        quitButton.draw(batch1);
        save.draw(batch1);
        batch1.end();

        touchHandle();
    }

    void serialize() {
        ObjectOutputStream o=null;
        try{
            String p=Integer.toString(TankGame.noOfSavedgames);
            System.out.println(p);
            p="newObjectFile"+p+".txt";
            o=new ObjectOutputStream(new FileOutputStream(p));
            o.writeObject(gameScreen);
            TankGame.noOfSavedgames++;
            o.close();
        }catch(IOException e){
            System.out.println(e.getStackTrace());
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
        tankstars.setScreen(gameScreen);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
