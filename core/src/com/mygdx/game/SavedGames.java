package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

public class SavedGames implements Screen{

    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;
    Vector3 temp = new Vector3();
    Sprite backButton = null;
    Sprite emptyButton1 = null;
    Sprite emptyButton2 = null;
    Sprite emptyButton3 = null;
    Sprite game1;
    Sprite game2;
    Sprite game3;
    SpriteBatch batch1 = null;

    public SavedGames(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("among_trees___under_the_cliff_by_freya_passifolle_de2faw8-pre.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1236, 600);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    void touchHandle() throws IOException, ClassNotFoundException {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
            System.out.println(temp.x);
            System.out.println(temp.y);
            if (xtouch >= 25 && xtouch <= 72.223 && ytouch >= 403.819 && ytouch <= 459.561) {
                tankStars.setScreen(new MainMenu(tankStars));
            }
            if (xtouch >= 292 && xtouch <= 520 && ytouch >= 292 && ytouch <= 459.561) {
                GameScreen gd=deserialize();
                tankStars.setScreen(gd);
            }
            if (xtouch >= 292 && xtouch <= 520 && ytouch >= 198 && ytouch <= 240) {
                deserialize();
                tankStars.setScreen(new MainMenu(tankStars));
            }
            if (xtouch >= 292 && xtouch <= 520 && ytouch >= 106 && ytouch <= 146) {
                tankStars.setScreen(new GameScreen(tankStars));
            }

        }
    }

    @Override
    public void render(float delta) {

        batch1 = new SpriteBatch();
        backButton = new Sprite(new Texture("back_button.png"));
        backButton.setSize(100, 100);
        backButton.setPosition(38,648);

        emptyButton1 = new Sprite(new Texture("empty_button.png"));
        emptyButton1.setSize(500,100);
        emptyButton1.setPosition(450,150);

        emptyButton2 = new Sprite(new Texture("empty_button.png"));
        emptyButton2.setSize(500,100);
        emptyButton2.setPosition(450,300);

        emptyButton3 = new Sprite(new Texture("empty_button.png"));
        emptyButton3.setSize(500,100);
        emptyButton3.setPosition(450,450);

        game1 = new Sprite(new Texture("game1.png"));
        game1.setSize(500,100);
        game1.setPosition(450,150);

        game2 = new Sprite(new Texture("game2.png"));
        game2.setSize(500,100);
        game2.setPosition(450,300);

        game3 = new Sprite(new Texture("game3.png"));
        game3.setSize(500,100);
        game3.setPosition(450,450);


        ScreenUtils.clear(0, 0, 0, 0);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SeventiesGroovy-owZ7q.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = new Color(0,0,0,1);
        BitmapFont font1 = generator.generateFont(parameter);

        camera.update();
        tankStars.batch.setProjectionMatrix(camera.combined);
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        font1.draw(tankStars.batch, "Load Game", 300, 433);
        generator.dispose();
        tankStars.batch.end();

        batch1.begin();
        backButton.draw(batch1);
        emptyButton1.draw(batch1);
        emptyButton2.draw(batch1);
        emptyButton3.draw(batch1);
        game1.draw(batch1);
        game2.draw(batch1);
        game3.draw(batch1);
        batch1.end();
        try {
            touchHandle();
        }catch(Exception e){
           System.out.println(e.getStackTrace());
        }
        dispose();
    }

    public static GameScreen deserialize() throws IOException,ClassNotFoundException {
        ObjectInputStream in=null;
        try{
            in=new ObjectInputStream(new FileInputStream("newObjectFile0.txt"));
            GameScreen tg=(GameScreen) in.readObject();
            return tg;
        }
        finally{
            in.close();
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
        batch1.dispose();
    }

}
