package com.go;

public class Tile {
  public static final int BLOCK_SIZE = 64;

  public static final int EMPTY = 0;

  public static final int BLACK = 1;
  public static final int WHITE = 2;

  public static final int WOOD = 3;
  public static final int CLAY = 4;
  public static final int IRON = 5;
  public static final int CROP = 6;

  public static final int CENTER = 7;
  public static final int LEFT_DOWN_CORNER = 8;
  public static final int LEFT_TOP_CORNER = 9;
  public static final int RIGHT_DOWN_CORNER = 10;
  public static final int RIGHT_TOP_CORNER = 11;
  public static final int LEFT_SIDE = 12;
  public static final int RIGHT_SIDE = 13;
  public static final int TOP_SIDE = 14;
  public static final int DOWN_SIDE = 15;

  private int row;
  private int column;
  private int boardLayer = EMPTY;
  private int resourceLayer = EMPTY;
  private int stoneLayer = EMPTY;

  public Tile (int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }

  public void setBoardLayer (int boardLayer) {
    this.boardLayer = boardLayer;
  }

  public int getBoardLayer () {
    return boardLayer;
  }

  public void setStoneLayer (int stoneLayer) {
    this.stoneLayer = stoneLayer;
  }

  public int getStoneLayer () {
    return stoneLayer;
  }

  public void setResourceLayer (int resourceLayer) {
    this.resourceLayer = resourceLayer;
  }

  public int getResourceLayer () {
    return resourceLayer;
  }

}
