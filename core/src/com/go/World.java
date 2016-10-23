package com.go;

import com.badlogic.gdx.InputAdapter;

public class World {
  GoGame game;
  Board board;
  Panel panel;

  Input input;

  public World (GoGame game) {
    this.game = game;
    board = new Board(  0, 0);

    panel = new Panel(596, 0);

    input = new Input(board, panel);
  }

  public void update (float delta) {
    board.update(delta);
  }
}
