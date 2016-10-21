package com.go;

public class Block {
  public static final int BLOCK_SIZE = 64;

  private int row;
  private int column;

  public Block (int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }
}
