package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class myContactManager implements ContactListener{
    GameScreen gameScreen;

    public myContactManager(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    public void beginContact(Contact contact){
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if(a == null || b == null){
            return;
        }

        if(a.getUserData() == null || b.getUserData() == null){
            return;
        }

        if(isCollision(a,b)){
            gameScreen.collisionDetected();
        }
    }

    public void endContact(Contact contact){

    }

    public void preSolve(Contact contact, Manifold manifold){

    }

    public void postSolve(Contact contact, ContactImpulse contactImpulse){

    }

    public boolean isCollision(Fixture a, Fixture b){
        System.out.println(a.getDensity());
        System.out.println(b.getDensity());
        if(a.getDensity() == 10 && b.getDensity() == 5){
            return true;
        }
        else if(a.getDensity() == 5 && b.getDensity() == 10){
            return true;
        }
        else{
            return false;
        }
    }
}
