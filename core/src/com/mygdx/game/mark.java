package com.mygdx.game;

public class mark extends Tank{
    public mark(int health, float initX, float initY) {
        super(health, initX, initY);
        
    }

    @Override
    public Projectile ChooseProjectile() {
        return null;

    }
}
