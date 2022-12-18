package com.mygdx.game;

import java.io.Serializable;

public abstract class Tank implements Serializable {
    public void setSpecial1(Projectile special1) {
        this.special1 = special1;
    }

    public void setCurrX(float currX) {
        this.currX = currX;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCurrY(float currY) {
        this.currY = currY;
    }

    protected Projectile special1;
    protected float currX;
    protected float currY;
    protected int fuel;
    protected int health;
    public Tank(int health,float initX,float initY){
        this.health=health;
        this.fuel=100;
        currX=initX;
        currY=initY;

    }
    public void move(){

    }

    public abstract Projectile ChooseProjectile();
    public void makeMove(){};


}
