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

public class MainMenu implements Screen{
    final TankStars tankStars;
    OrthographicCamera camera;
    private TextureRegion backgroundTexture;
    private Texture backgroundImage;
    Sprite next_button;
    Sprite load_game_button;
    Sprite exit_button;
    SpriteBatch batch2;
    Vector3 temp = new Vector3();

    public MainMenu(final TankStars tankStars) {
        this.tankStars = tankStars;

        backgroundImage = new Texture(Gdx.files.internal("MainMenuBG.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1192, 670);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    void touchHandle(){
        if(Gdx.input.justTouched()){
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float xtouch = temp.x;
            float ytouch = temp.y;
            System.out.println(temp.x + " lo: " + (load_game_button.getX()-283.098) +" hi: " + ((load_game_button.getX() + load_game_button.getWidth())-342.219));
            System.out.println(" " + temp.y + " lo:" + (load_game_button.getY()-68.039) + " hi: " + ((load_game_button.getY() + load_game_button.getHeight())-123.446));

            if(xtouch >= (next_button.getX()-351.111) && xtouch <= (next_button.getX() + next_button.getWidth() - 403.333) && ytouch >= (next_button.getY()-71.799) && ytouch <= (next_button.getY() + next_button.getHeight())-117.905){
                tankStars.setScreen(new GameScreen(tankStars));
            }
            else if(xtouch >= (load_game_button.getX()-283.098) && xtouch <= (load_game_button.getX() + load_game_button.getWidth() - 342.219) && ytouch >= (load_game_button.getY()-68.039) && ytouch <= (load_game_button.getY() + load_game_button.getHeight())-123.446){
                //load game
            }
            else if(xtouch >= (exit_button.getX()-219.445) && xtouch <= ((exit_button.getX() + exit_button.getWidth()) - 271) && ytouch >= (exit_button.getY()-73.033) && ytouch <= (exit_button.getY() + exit_button.getHeight())-119.149){
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
        next_button.setPosition(800, 200);
        next_button.setSize(100,100);

        load_game_button = new Sprite(new Texture("load_game.png"));
        load_game_button.setPosition(650, 195);
        load_game_button.setSize(110,110);

        exit_button = new Sprite(new Texture("exit_button.png"));
        batch2 = new SpriteBatch();
        exit_button.setPosition(500, 200);
        exit_button.setSize(100,100);

        batch2.begin();
        next_button.draw(batch2);
        load_game_button.draw(batch2);
        exit_button.draw(batch2);
        batch2.end();

        touchHandle();
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
    public void dispose() {}
}
