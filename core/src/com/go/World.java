package com.go;

public class World {
  GoGame game;
  Board board;

  public World (GoGame game) {
    this.game = game;
    board = new Board(  (GoGame.SCREEN_WIDTH -
                        ( (Board.BOARD_SIZE * Tile.BLOCK_SIZE) / 2 ),
                        0);
  }

  public void update (float delta) {
    board.update(delta);
  }
}
