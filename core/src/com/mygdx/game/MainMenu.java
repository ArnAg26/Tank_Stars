package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision._btMprSupport_t;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen{
    final TankStars tankStars;
    OrthographicCamera camera;
    private TextureRegion backgroundTexture;
    private Texture backgroundImage;
    private float width;
    private float height;
    Sprite next_button;
    Sprite load_game_button;
    Sprite exit_button;
    Sprite bg;
    Sprite player1;
    Sprite player2;
    Sprite coalition, coalition2;
    Sprite mark, mark2;
    Sprite siedge, siedge2;
    SpriteBatch batch2;
    Vector3 temp = new Vector3();

    public MainMenu(final TankStars tankStars) {
        this.tankStars = tankStars;
        this.width = (float) Gdx.graphics.getWidth();
        this.height = (float) Gdx.graphics.getHeight();
        backgroundImage = new Texture(Gdx.files.internal("a439b065333621.5af0ea8ca23f5.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1192, 670);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void touchHandle(){
        if(Gdx.input.justTouched()){
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
//            System.out.println(temp.y);
//            System.out.println(height);
            if(xtouch >= width/2.02 && xtouch <= width/1.89 && ytouch >= height/2.23 && ytouch <= height/1.94){
                tankStars.setScreen(new GameScreen(tankStars));
            }

            if(xtouch >= width/2.02 && xtouch <= width/1.89 && ytouch >= height/3.48 && ytouch <= height/2.78){
                tankStars.setScreen(new SavedGames(tankStars));
            }

            if(xtouch >= width/2.02 && xtouch <= width/1.89 && ytouch >= height/8.07 && ytouch <= height/5.17){
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        tankStars.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        tankStars.batch.begin();
        tankStars.batch.draw(backgroundTexture, 0,0, 800, 480);
        tankStars.batch.end();


        next_button = new Sprite(new Texture("next_button.png"));
        batch2 = new SpriteBatch();
        next_button.setPosition(1200, 550);
        next_button.setSize(100,100);

        load_game_button = new Sprite(new Texture("load_game.png"));
        load_game_button.setPosition(1200, 350);
        load_game_button.setSize(110,110);

        exit_button = new Sprite(new Texture("exit_button.png"));
        exit_button.setPosition(1210, 150);
        exit_button.setSize(100,100);

        bg = new Sprite(new Texture("bg.jpg"));
        bg.setPosition(1100, 0);
        bg.setSize(700,1000);

        coalition = new Sprite(new Texture("coalition.gif"));
        coalition.setPosition(100,450);
        coalition.setSize(250,200);

        mark = new Sprite(new Texture("mark.gif"));
        mark.setPosition(400,450);
        mark.setSize(300,150);

        siedge = new Sprite(new Texture("siedge.gif"));
        siedge.setPosition(750,450);
        siedge.setSize(300,175);

        coalition2 = new Sprite(new Texture("coalition.gif"));
        coalition2.setPosition(100,100);
        coalition2.setSize(250,200);

        mark2 = new Sprite(new Texture("mark.gif"));
        mark2.setPosition(400,100);
        mark2.setSize(300,150);

        siedge2 = new Sprite(new Texture("siedge.gif"));
        siedge2.setPosition(750,100);
        siedge2.setSize(300,175);

        player1 = new Sprite(new Texture("player1.png"));
        player1.setPosition(400,575);
        player1.setSize(300,175);

        player2 = new Sprite(new Texture("player2.png"));
        player2.setPosition(400,200);
        player2.setSize(300,175);

        batch2.begin();
        bg.draw(batch2);
        next_button.draw(batch2);
        load_game_button.draw(batch2);
        exit_button.draw(batch2);
        coalition.draw(batch2);
        mark.draw(batch2);
        siedge.draw(batch2);
        coalition2.draw(batch2);
        mark2.draw(batch2);
        siedge2.draw(batch2);
        player1.draw(batch2);
        player2.draw(batch2);
        batch2.end();

        touchHandle();
        dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {batch2.dispose();}
}
