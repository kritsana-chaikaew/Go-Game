package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;

public class Input extends InputAdapter {
  Board board;
  Panel panel;

  Troop troop = Troop.EMPTY_TROOP;
  Stone stone = Stone.EMPTY_STONE;

  public Input (Board board, Panel panel) {
    this.board = board;
    this.panel = panel;

    Gdx.input.setInputProcessor(this);
  }

  @Override
  public boolean touchDown (int x, int y, int pointer, int button) {
    int row = x / Block.BLOCK_SIZE;
    int column = y / Block.BLOCK_SIZE;

    onClick(row, column);

    return true;
  }

  public Troop getTroopOnClick (int row, int column) {
    System.out.println(row + " " + column);
    if (row < Panel.PANEL_WIDHT && column < Panel.PANEL_HEIGHT) {
      return panel.getTroopBlock(row, column).getTroopLayer();
    } else {
      return Troop.EMPTY_TROOP;
    }
  }

  public Stone getStoneOnclick (int row, int column) {
    if (row < Panel.PANEL_WIDHT && column < Panel.PANEL_HEIGHT) {
      return panel.getStoneBlock(row, column).getStoneLayer();
    } else {
      return Stone.EMPTY_STONE;
    }
  }

  public void setTroopOnClick (Troop troop, int row, int column) {
    if (board.troopBlocks[row][column].getTroopLayer() == Troop.EMPTY_TROOP){
      board.troopBlocks[row][column].setTroopLayer(troop);
    }
    System.out.println("setTroop");
  }

  public void setStoneOnClick (Stone stone, int row, int column) {
    if (board.stoneBlocks[row][column].getStoneLayer() == Stone.EMPTY_STONE){
      board.stoneBlocks[row][column].setStoneLayer(stone);
    }
    System.out.println("setStone");
  }

  public void onClick (int row, int column) {
    if (row < Board.BOARD_SIZE && column < Board.BOARD_SIZE) {
      setTroopOnClick(troop, row, column);
      setStoneOnClick(stone, row, column);
      troop = Troop.EMPTY_TROOP;
      stone = Stone.EMPTY_STONE;
    } else if (row >= Board.BOARD_SIZE || column >= Board.BOARD_SIZE) {
      System.out.println("getTroop");
      troop = getTroopOnClick(row - Board.BOARD_SIZE, column);
      stone = getStoneOnclick(row - Board.BOARD_SIZE, column);
    }
  }
}
