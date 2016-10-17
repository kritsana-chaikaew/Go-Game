package com.go;

import com.badlogic.gdx.graphics.Texture;

public class BoardRenderer {
  GoGame game;
  Board board;

  private Texture tileImage;

  public BoardRenderer (GoGame game, Board board) {
    this.game = game;
    this.board = board;
  }

  public void render () {
    for (int i = 0; i < Board.BOARD_SIZE; i ++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {
        tileImage = board.tiles[i][j].getImage();
        game.batch.draw(tileImage, i * Tile.BLOCK_SIZE, j * Tile.BLOCK_SIZE);
      }
    }

  }
}
