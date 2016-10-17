package com.go;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class GameScreen extends ScreenAdapter{
  GoGame game;
  Board board;
  BoardRenderer boardRenderer;
  public GameScreen (GoGame game) {
    this.game = game;

    board = new Board();
    boardRenderer = new BoardRenderer(game, board);
  }

  @Override
  public void render (float delta) {
    draw();
    update(delta);
  }

  public void update (float delta) {
    board.update(delta);
  }

  public void draw () {
    Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
    boardRenderer.render();
		game.batch.end();
  }
}
