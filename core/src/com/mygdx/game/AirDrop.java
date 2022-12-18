package com.mygdx.game;

import java.io.Serializable;

public class AirDrop implements Serializable {
    private Projectile p;
    private float posX;

    public Projectile getP(){
        return p;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    private float posY;
    public AirDrop(Projectile p,float posX,float posY){
        this.p=p;
        this.posX=posX;
        this.posY=posY;
    }


}
