package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

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
    Sprite crate;
    Sprite bomb;
    SpriteBatch batch1;
    SpriteBatch batch2;
    OrthographicCamera camera;
    Vector3 temp = new Vector3();

    int turn = 0;
    int flag = 1;
    int shoot_flag = -1;
    int bomb_flag = -1;
    boolean setToDelete;
    boolean alreadyDestroyed;

    private  World world;
    Body player, platform, player2, airdrop, projectile, projectile2;
    private Box2DDebugRenderer b2dr;
    private final float SCALE = 2.0f;
    private final float PPM = 32;
    Texture terrain_texture;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    public GameScreen(final TankStars tankStars){
        this.tankStars = tankStars;
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1920, 1000);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        terrain = new Sprite(new Texture("terrain_blue.png"));
        batch2 = new SpriteBatch();

        world = new World(new Vector2(0, -25f), false);
        world.setContactListener(new myContactManager(this));
        b2dr = new Box2DDebugRenderer();

        player = createBox(20,5,40,32, 5,false);
        player2 = createBox(2,5,32,32,5,false);
        platform = createBox(13,2,415,60,1,true);
        setToDelete = false;
        alreadyDestroyed = false;

        terrain_texture = new Texture("terrain_blue.png");
//        map = new TmxMapLoader().load("tx.tmx");
//        tmr = new OrthogonalTiledMapRenderer(map);
    }

    private void touchHandle() {
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
        update(Gdx.graphics.getDeltaTime());

        batch1 = new SpriteBatch();
        pauseButton = new Sprite(new Texture("pause_button.png"));
        pauseButton.setSize(70,70);
        pauseButton.setPosition(28,678);
//        pauseButton.setColor(1,0,0,1);

        terrain.setPosition(0,0);
        terrain.setSize(1400,450);

        tank1 = new Sprite(new Texture("mark_revert.png"));
        tank1.setPosition(player.getPosition().x*52,200);
        tank1.setSize(150,100);

        tank2 = new Sprite(new Texture("siedge.gif"));
        tank2.setPosition(player2.getPosition().x*52 - 50,200);
        tank2.setSize(120,100);

        health1 = new Sprite(new Texture("health1.png"));
        health1.setPosition(450,705);
        health1.setSize(200,50);

        health2 = new Sprite(new Texture("health2.png"));
        health2.setPosition(750,700);
        health2.setSize(200,45);

        tankStarslogo = new Sprite(new Texture("logo2.png"));
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
        tankStarslogo.draw(batch1);
        fire_button_circle.draw(batch1);
        fire_button.draw(batch1);
        left_arrow.draw(batch1);
        right_arrow.draw(batch1);
        batch1.end();

        if((count1 == 4 || count2 == 4) && flag == 1){
            airdrop = createBox(12,12,28,28,5,false);
            flag = 0;
        }

        if(flag == 0){
            crate = new Sprite(new Texture("crate.png"));
            crate.setPosition(624, airdrop.getPosition().y*51 - 35);
            crate.setSize(95,95);
            batch1.begin();
            crate.draw(batch1);
            batch1.end();
        }

        if(shoot_flag == 0){
//            for(int i=0; i<projectile.getFixtureList().size;i++){
//                projectile.getFixtureList().get(i).setSensor(true);
//            }
            projectile = createBox(player.getPosition().x - (float)1.5, 5,5,5, 10,false);
            projectile.applyLinearImpulse(-15,13, projectile.getPosition().x, projectile.getPosition().y, true);
            bomb_flag = 0;
            shoot_flag = -1;
        }

        if(bomb_flag == 0){
            bomb = new Sprite(new Texture("bomb.png"));
            bomb.setPosition(projectile.getPosition().x*55 + 3,projectile.getPosition().y*50);
            bomb.setSize(25,25);

            batch1.begin();
            bomb.draw(batch1);
            batch1.end();
        }

        if(shoot_flag == 1){
            projectile2 = createBox( player2.getPosition().x + (float) 1.5, 6,5,5,10,false);
            projectile2.applyLinearImpulse(15,13, projectile2.getPosition().x, projectile2.getPosition().y, true);
            bomb_flag = 1;
            shoot_flag = -1;
        }

        if(bomb_flag == 1){
            bomb = new Sprite(new Texture("bomb.png"));
            bomb.setPosition(projectile2.getPosition().x*55 + 3,projectile2.getPosition().y*50);
            bomb.setSize(25,25);

            batch1.begin();
            bomb.draw(batch1);
            batch1.end();
        }

        batch2.begin();
        batch2.draw(terrain_texture, 0, 0, 25,4);
        batch2.end();
//        tmr.render();
        b2dr.render(world, camera.combined.scl(PPM));
        touchHandle();
//        Window pause = new Window("Pause", skin);
    }

    public Body createBox(float x, float y, int width, int height, float density, boolean isStatic){
        Body body;
        BodyDef terrain_def = new BodyDef();
        if(isStatic){
            terrain_def.type = BodyDef.BodyType.StaticBody;
        }
        else{
            terrain_def.type = BodyDef.BodyType.DynamicBody;
        }

        terrain_def.position.set(x,y);
        terrain_def.fixedRotation = true;
        body = world.createBody(terrain_def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/PPM, height/PPM);

        body.createFixture(shape, density).setUserData(body);
        shape.dispose();
        return body;
    }
    public void update(float delta){
        world.step(1/60f, 6,2);
        if(setToDelete && alreadyDestroyed == false){
            if(bomb_flag == 0){
                world.destroyBody(projectile);
                bomb_flag = -1;
            }
            if(bomb_flag == 1){
                world.destroyBody(projectile2);
                bomb_flag = -1;
            }
            alreadyDestroyed = true;
        }
        inputUpdate(delta);
        batch2.setProjectionMatrix(camera.combined);
    }


    public void inputUpdate(float delta){
        player.setLinearVelocity(0, player.getLinearVelocity().y);
        player2.setLinearVelocity(0, player2.getLinearVelocity().y);
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            moveleft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            moveright();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            shoot();
        }

    }
    public void shoot(){
        if(turn == 0){
            shoot_flag = 0;
            turn = 1;
        }
        else if(turn == 1){
            shoot_flag = 1;
            turn = 0;
        }
    }

    int count1 = 0;
    int count2 = 0;
    public void moveleft(){

        if(turn == 0){
            if(count1 > 8){
                return;
            }
            player.setLinearVelocity(-75, player.getLinearVelocity().y);
            count1++;
        }
        else if(turn == 1){
            if(count2 > 8){
                return;
            }
            player2.setLinearVelocity(-75, player2.getLinearVelocity().y);
            count2++;
        }
    }

    public void moveright(){
        if(turn == 0){
            if(count1 > 8){
                return;
            }
            player.setLinearVelocity(75, player.getLinearVelocity().y);
            count1++;
        }
        else if(turn == 1) {
            if(count2 > 8){
                return;
            }
            player2.setLinearVelocity(75, player2.getLinearVelocity().y);
            count2++;
        }
    }

    public void cameraUpdate(float delta){
        Vector3 position = camera.position;
        position.x = player.getPosition().x * PPM;
        position.y = player.getPosition().y * PPM;
        camera.position.set(position);
        camera.update();
    }

//    public void destroyBody(Body body){
//        setToDelete = true;
//    }
//
    public void collisionDetected(){
        setToDelete = true;
        alreadyDestroyed = false;
//        if(bomb_flag == 0){
//            destroyBody(projectile);
//        }
//        if(bomb_flag == 1){
//            destroyBody(projectile2);
//        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }


    public enum State
    {

    }

    public void setGameState(State s){
    }
    @Override
    public void pause() {
        tankStars.setScreen(new PauseMenu(tankStars, this));
    }

    @Override
    public void resume() {
    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        b2dr.dispose();
        world.dispose();
        batch1.dispose();
        batch2.dispose();
        tmr.dispose();
        map.dispose();
    }

}
