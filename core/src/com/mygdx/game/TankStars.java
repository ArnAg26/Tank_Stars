package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankStars extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
