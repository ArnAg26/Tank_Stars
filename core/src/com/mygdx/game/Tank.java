package com.mygdx.game;

import java.io.Serializable;

public class Tank implements Serializable {
    Projectile special1;
    float currX;
    float currY;
    int fuel;
    protected int health;
    public Tank(int health,float initX,float initY){
        this.health=health;
        this.fuel=100;
        currX=initX;
        currY=initY;

    }


    private void ChooseProjectile(){};
    public void makeMove(){};


}
