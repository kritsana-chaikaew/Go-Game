package com.go;

import java.util.List;
import java.util.ArrayList;

public class Panel {
  public static final int PANEL_WIDHT = 5;
  public static final int PANEL_HEIGHT = Board.BOARD_SIZE;

  private int x;
  private int y;
  private int woodRate;
  private int clayRate;
  private int ironRate;
  private int cropRate;
  private int wood;
  private int clay;
  private int iron;
  private int crop;

  public Block [][] blocks;

  public Panel (int x, int y) {
    this.x = x;
    this.y = y;

    setupPanel();

    getPlayerData();
  }

  public void setupPanel () {
    blocks = new Block[PANEL_WIDHT][PANEL_HEIGHT];

    for (int j = 0; j < PANEL_HEIGHT; j++) {
      for (int i = 0; i < PANEL_WIDHT; i++) {
        blocks[i][j] = new Block(i, j);
        blocks[i][j].setResourceLayer(Resource.WOOD);
      }
    }
  }

  public void setupWareHouse () {

  }

  public void getPlayerData () {

  }

  public int getX () {
    return x;
  }

  public int getY () {
    return y;
  }
}
