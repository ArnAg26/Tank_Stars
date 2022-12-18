package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class coalition extends Tank{

    public coalition(int health, float initX, float initY) {
        super(health, initX, initY);
    }

    @Override
    public Projectile ChooseProjectile() {
        return null;

    }
}
