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
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadingScreen implements Screen {
    final TankStars tankStars;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    Sprite logo;
    SpriteBatch batch1;
    OrthographicCamera camera;

    public LoadingScreen(final TankStars tankStars) {
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("PauseScreenBackground.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 4500, 2531);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    private void touchHandle(){
        if (Gdx.input.isTouched()) {
            tankStars.setScreen(new MainMenu(tankStars));
            dispose();
        }
    }

    @Override
    public void render(float delta) {
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
//        font1.draw(tankStars.batch, "Welcome to Tank Stars!", 200, 340);
//        font2.draw(tankStars.batch, "Click anywhere to begin!", 300, 240);
        generator.dispose();
        tankStars.batch.end();

        logo = new Sprite(new Texture("logo2.png"));
        logo.setPosition(550, 450);
        logo.setSize(350,300);

        batch1 = new SpriteBatch();
        batch1.begin();
        logo.draw(batch1);
        batch1.end();

        touchHandle();

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

