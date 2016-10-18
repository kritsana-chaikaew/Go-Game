package com.go;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Buttons;

public class Board {
  public static final int BOARD_SIZE = 11;

  Tile [][] tiles;
  Tile tileHover;

  public int random;

  public Board () {
    setup();

    // CUATION!!! NUMBER ALL RESOURCE MUST NOT EXCEED NUMBER OF TILES
    randomResource(Tile.WOOD, 5);
    randomResource(Tile.CLAY, 5);
    randomResource(Tile.IRON, 5);
    randomResource(Tile.CROP, 5);
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
    if (row >= 0 && row < BOARD_SIZE
        &&  column >= 0 && column < BOARD_SIZE) {
      System.out.println(row + " " + column);
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
    if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
      Tile tile = getTileOnHover();
      if (tile != null) {
        tile.setStoneLayer(Tile.BLACK);
      }
    }
    if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
      Tile tile = getTileOnHover();
      if (tile != null) {
        tile.setStoneLayer(Tile.EMPTY);
      }
    }
  }

  public Tile randomTile () {
    return tiles[MathUtils.random(BOARD_SIZE - 1)][MathUtils.random(BOARD_SIZE - 1)];
  }

  public void randomResource (int resourceLayer, int num) {
    int i = 0;
    while (i < num) {
      Tile randomTile = randomTile();
      if (randomTile.getResourceLayer() == Tile.EMPTY
          && randomTile.getRow() != 0
          && randomTile.getColumn() != 0) {
        randomTile.setResourceLayer(resourceLayer);
        i++;
      }
    }

  }
}
