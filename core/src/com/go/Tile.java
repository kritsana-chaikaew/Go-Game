package com.go;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
  public static final int BLOCK_SIZE = 64;
  public static final int EMPTY = 0;
  public static final int BLACK = 1;
  public static final int WHITE = 2;
  public static final int WOOD = 3;
  public static final int CLAY = 4;
  public static final int IRON = 5;
  public static final int CROP = 6;

  private int row;
  private int column;
  private int type = EMPTY;
  private Texture image;
  public Tile (int row, int column) {
    this.row = row;
    this.column = column;

    image = new Texture("tile_center.png");
  }

  public void setImage (Texture image) {
    this.image = image;
  }

  public Texture getImage () {
    return image;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }
}
