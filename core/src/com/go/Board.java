package com.go;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Board {
  public static final int BOARD_SIZE = 11;

  Tile [][] tiles;
  Tile tileHover;

  public int random;

  public Board () {
    setup();
  }

  public void update (float delta) {
    onHover();
    onClick();
  }

  public void setup () {
    tiles = new Tile [BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {

        int boardLayer = Tile.EMPTY;
        int stoneLayer = Tile.EMPTY;
        int resourceLayer = Tile.EMPTY;

        if ( (i == 0 && j == 0)) {
          boardLayer = Tile.LEFT_DOWN_CORNER;
        } else if ( i == 0 && j == BOARD_SIZE - 1 ) {
          boardLayer = Tile.LEFT_TOP_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == 0 ) {
          boardLayer = Tile.RIGHT_DOWN_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == BOARD_SIZE - 1 ) {
          boardLayer = Tile.RIGHT_TOP_CORNER;
        } else if ( i == 0 ) {
          boardLayer = Tile.LEFT_SIDE;
        } else if ( i == BOARD_SIZE - 1 ) {
          boardLayer = Tile.RIGHT_SIDE;
        } else if ( j == 0 ) {
          boardLayer = Tile.DOWN_SIDE;
        } else if ( j == BOARD_SIZE - 1 ) {
          boardLayer = Tile.TOP_SIDE;
        } else {
          boardLayer = Tile.CENTER;
        }


        tiles[i][j] = new Tile(i, j);
        tiles[i][j].setBoardLayer(boardLayer);
        tiles[i][j].setStoneLayer(stoneLayer);
        tiles[i][j].setResourceLayer(resourceLayer);
      }
    }
  }

  public Tile getTileOnHover () {
    int row = Gdx.input.getX() / Tile.BLOCK_SIZE;
    int column = ( GoGame.SCREEN_HEIGHT - Gdx.input.getY() ) / Tile.BLOCK_SIZE;
    if (row < BOARD_SIZE && column < BOARD_SIZE && tiles[row][column] != null) {
      return tiles[row][column];
    } else {
      return null;
    }
  }

  public void onHover () {
    Tile tile = getTileOnHover();
    if (tile != null) {
      tileHover = tile;
    }
  }

  public void onClick () {
    if (Gdx.input.justTouched()) {
      Tile tile = getTileOnHover();
      tile.setStoneLayer(Tile.BLACK);
    }
  }
}
