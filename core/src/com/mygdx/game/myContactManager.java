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
        if(CollectAirDrop(a,b)){
            gameScreen.collectAirDrop();
        }
    }

    public void endContact(Contact contact){

    }

    public void preSolve(Contact contact, Manifold manifold){

    }

    public void postSolve(Contact contact, ContactImpulse contactImpulse){

    }

    public boolean CollectAirDrop(Fixture a,Fixture b){
        if(a.getDensity()==2 && b.getDensity()==3){
            return true;
        }
        else if(a.getDensity()==3 && b.getDensity()==2){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isCollision(Fixture a, Fixture b){
        System.out.println(a.getDensity());
        System.out.println(b.getDensity());
        if(a.getDensity() == 50 && b.getDensity() == 2){
            return true;
        }
        else if(a.getDensity() == 2 && b.getDensity() == 50){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isCollisionWithGround(Fixture a, Fixture b){
        System.out.println(a.getDensity());
        System.out.println(b.getDensity());
        if(a.getDensity() == 50 && b.getDensity() != 2){
            return true;
        }
        else if(a.getDensity() != 2 && b.getDensity() == 50){
            return true;
        }
        else{
            return false;
        }

    }
}
