package com.go;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GoGame extends Game {
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 640;

	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new GameScreen(this));
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
