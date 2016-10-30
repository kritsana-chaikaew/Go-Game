package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;

public class Input extends InputAdapter {
  Board board;
  Panel leftPanel, rightPanel;

  Troop troop = Troop.EMPTY_TROOP;
  Stone stone = Stone.EMPTY_STONE;

  public Input (Board board, Panel leftPanel, Panel rightPanel) {
    this.board = board;
    this.leftPanel = leftPanel;
    this.rightPanel = rightPanel;

    Gdx.input.setInputProcessor(this);
  }

  @Override
  public boolean touchDown (int x, int y, int pointer, int button) {
    int row = x / Block.BLOCK_SIZE;
    int column = y / Block.BLOCK_SIZE;

    System.out.println(row + " " + column);
    onClick(row, column);

    return true;
  }

  public void onClick (int row, int column) {
    if (row >= leftPanel.getX() / Block.BLOCK_SIZE
        && row < board.getX() / Block.BLOCK_SIZE
        && column < Board.BOARD_SIZE) {
      clickOnPanel(leftPanel, row, column);
    } else if ( row >= board.getX() / Block.BLOCK_SIZE
                && row < (board.getX() / Block.BLOCK_SIZE) + Board.BOARD_SIZE
                && column < Board.BOARD_SIZE) {
      clickOnBoard(row - board.getX() / Block.BLOCK_SIZE, column);
    } else if ( row >= rightPanel.getX() / Block.BLOCK_SIZE
                && row < (rightPanel.getX() / Block.BLOCK_SIZE) + Panel.PANEL_WIDHT
                && column < Board.BOARD_SIZE) {
      clickOnPanel(rightPanel, row - rightPanel.getX() / Block.BLOCK_SIZE, column);
    }
  }

  public void clickOnBoard (int row, int column) {
    board.setTroopAt(troop, row, column);
    board.setStoneAt(stone, row, column);
    troop = Troop.EMPTY_TROOP;
    stone = Stone.EMPTY_STONE;
  }

  public void clickOnPanel (Panel panel, int row, int column) {
    if ((row - panel.getX() / Block.BLOCK_SIZE) == 1 && column == 5) {
      endTurn();
    }

    troop = getTroopOnClick(panel, row, column);
    stone = getStoneOnclick(panel, row, column);
  }

  public Troop getTroopOnClick (Panel panel, int row, int column) {
    if (row < Panel.PANEL_WIDHT
        && column < Panel.PANEL_HEIGHT) {
      return panel.getTroopBlock(row, column).getTroopLayer();
    } else {
      return Troop.EMPTY_TROOP;
    }
  }

  public Stone getStoneOnclick (Panel panel, int row, int column) {
    if (row < Panel.PANEL_WIDHT
        && column < Panel.PANEL_HEIGHT) {
      return panel.getStoneBlock(row, column).getStoneLayer();
    } else {
      return Stone.EMPTY_STONE;
    }
  }

  public void setStoneOnClick (Stone stone, int row, int column) {
    if (board.stoneBlocks[row][column].getStoneLayer() == Stone.EMPTY_STONE){
      board.stoneBlocks[row][column].setStoneLayer(stone);
    }
  }

  public void endTurn () {
    System.out.println("End Turn");
  }
}
