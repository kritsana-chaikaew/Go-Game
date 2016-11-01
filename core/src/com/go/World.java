package com.go;

import com.badlogic.gdx.InputAdapter;

public class World {
  GoGame game;
  Board board;
  Panel leftPanel, rightPanel;

  Input input;

  public static GameState gameState = GameState.BLACK_TURN;

  public World (GoGame game) {
    this.game = game;

    int boardPosition =
          GoGame.SCREEN_WIDTH / 2 -
          (Board.BOARD_SIZE * Block.BLOCK_SIZE / 2);

    int rightPanelPosition =
          (GoGame.SCREEN_WIDTH - (Panel.PANEL_WIDHT * Block.BLOCK_SIZE)) /
          Block.BLOCK_SIZE * Block.BLOCK_SIZE;

    board = new Board(boardPosition, 0);
    leftPanel = new Panel(0, 0, Stone.BLACK, this);
    rightPanel = new Panel(rightPanelPosition, 0, Stone.WHITE, this);
    input = new Input(this);
  }

  public void update () {
    board.update();
    leftPanel.update();
    rightPanel.update();
  }

  public Board getBoard () {
    return board;
  }

  public static void changeGameState (GameState gameState) {
    World.gameState = gameState;
  }
}
