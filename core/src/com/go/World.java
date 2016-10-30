package com.go;

import com.badlogic.gdx.InputAdapter;

public class World {
  GoGame game;
  Board board;
  Panel leftPanel, rightPanel;

  Input input;

  public World (GoGame game) {
    this.game = game;

    int boardPosition = GoGame.SCREEN_WIDTH / 2 - (Board.BOARD_SIZE * Block.BLOCK_SIZE / 2);

    leftPanel = new Panel(0, 0);
    board = new Board(boardPosition, 0);
    rightPanel = new Panel(960, 0);
    input = new Input(board, leftPanel, rightPanel);
  }

  public void update (float delta) {
    board.update(delta);
  }
}
