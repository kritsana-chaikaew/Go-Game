package com.go;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class GameScreen extends ScreenAdapter{
  GoGame game;
  public GameScreen (GoGame game) {
    this.game = game;
  }

  @Override
  public void render (float delta) {
    draw();
  }

  public void draw () {
    Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		game.batch.end();
  }
}
