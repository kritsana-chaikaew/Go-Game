package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Board {
  public static final int BOARD_SIZE = 11;
  Tile [][] tiles;

  public Board () {
    setup();
  }

  public void update (float delta) {
    changeImageOnHover();
  }

  public void setup () {
    tiles = new Tile [BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {

        String imagePath;
        tiles[i][j] = new Tile(i, j);

        if ( (i == 0 && j == 0)) {
          imagePath = "tile_corner1";
        } else if ( i == 0 && j == BOARD_SIZE - 1 ) {
          imagePath = "tile_corner2";
        } else if ( i == BOARD_SIZE - 1 && j == 0 ) {
          imagePath = "tile_corner3";
        } else if ( i == BOARD_SIZE - 1 && j == BOARD_SIZE - 1 ) {
          imagePath = "tile_corner4";
        } else if ( i == 0 ) {
          imagePath = "tile_side1";
        } else if ( i == BOARD_SIZE - 1 ) {
          imagePath = "tile_side3";
        } else if ( j == 0 ) {
          imagePath = "tile_side4";
        } else if ( j == BOARD_SIZE - 1 ) {
          imagePath = "tile_side2";
        } else {
          imagePath = "tile_center";
        }

        tiles[i][j].setImage(new Texture(imagePath + ".png"));
      }
    }
  }

  public Tile getTileOnHover () {
    int row = Gdx.input.getX() / Tile.BLOCK_SIZE;
    int column = ( GoGame.SCREEN_HEIGHT - Gdx.input.getY() ) / Tile.BLOCK_SIZE;
    if (row < BOARD_SIZE && column < BOARD_SIZE) {
      return tiles[row][column];
    } else {
      return null;
    }
  }

  public void changeImageOnHover () {
    Tile tile = getTileOnHover();
    if (tile != null) {
      tile.setImage(new Texture("tile_center_hover.png"));
    }
  }

}
