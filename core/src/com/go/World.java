package com.go;

public class World {
  GoGame game;
  Board board;

  public World (GoGame game) {
    this.game = game;
    board = new Board();
  }

  public void update (float delta) {
    board.update(delta);
  }
}
