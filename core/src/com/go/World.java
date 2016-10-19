package com.go;

public class World {
  GoGame game;
  Board board;
  Panel panel;

  public World (GoGame game) {
    this.game = game;
    board = new Board(  0, 0);

    panel = new Panel(640, 0);
  }

  public void update (float delta) {
    board.update(delta);
  }
}
