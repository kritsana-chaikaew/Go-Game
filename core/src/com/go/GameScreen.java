package com.go;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class GameScreen extends ScreenAdapter{
  GoGame game;
  World world;
  WorldRenderer worldRenderer;
  public GameScreen (GoGame game) {
    this.game = game;

    world = new World(game);
    worldRenderer = new WorldRenderer(game, world);
  }

  @Override
  public void render (float delta) {
    draw();
    update(delta);
  }

  @Override
  public void dispose () {
  }

  public void update (float delta) {
    world.update();
  }

  public void draw () {
    Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    worldRenderer.render();
  }
}
