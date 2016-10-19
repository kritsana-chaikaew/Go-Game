package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.MathUtils;

public class Board {
  public static final int BOARD_SIZE = 10;

  Block blockHover;

  Block [][] blocks;

  public int offsetX;
  public int offsetY;

  public Board (int x, int y) {
    offsetX = x / 64 * 64;
    offsetY = y / 64 * 64;
    setupGrid();
  }

  public void update (float delta) {
    onHover();
    onClick();
  }

  public void setupGrid () {
    blocks = new Block[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {

        Grid gridLayer;

        if ( (i == 0 && j == 0)) {
          gridLayer = Grid.LEFT_DOWN_CORNER;
        } else if ( i == 0 && j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.LEFT_TOP_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == 0 ) {
          gridLayer = Grid.RIGHT_DOWN_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.RIGHT_TOP_CORNER;
        } else if ( i == 0 ) {
          gridLayer = Grid.LEFT_SIDE;
        } else if ( i == BOARD_SIZE - 1 ) {
          gridLayer = Grid.RIGHT_SIDE;
        } else if ( j == 0 ) {
          gridLayer = Grid.DOWN_SIDE;
        } else if ( j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.TOP_SIDE;
        } else {
          gridLayer = Grid.CENTER;
        }


        blocks[i][j] = new Block(i, j);
        if (gridLayer != null) {
          blocks[i][j].setGridLayer(gridLayer);
        }
      }
    }

    // CUATION!!! NUMBER ALL RESOURCE MUST NOT EXCEED NUMBER OF BLOCKS
    randomResource(Resource.WOOD, 5);
    randomResource(Resource.CLAY, 5);
    randomResource(Resource.IRON, 5);
    randomResource(Resource.CROP, 5);
  }

  public Block getBlockOnHover () {
    int row = (Gdx.input.getX() - offsetX) / Block.BLOCK_SIZE;
    int column = ( ( GoGame.SCREEN_HEIGHT - Gdx.input.getY() ) - offsetY )
        / Block.BLOCK_SIZE;
    if (row >= 0 && row < BOARD_SIZE
        &&  column >= 0 && column < BOARD_SIZE) {
      return blocks[row][column];
    } else {
      return null;
    }
  }

  public void onHover () {
    Block block = getBlockOnHover();
    if (block!= null) {
      blockHover = block;
    }
  }

  public void onClick () {
    if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
      Block block= getBlockOnHover();
      if (block != null && block.getStoneLayer() == Stone.EMPTY_STONE) {
        block.setStoneLayer(Stone.BLACK);
      }
    }
    if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
      Block block = getBlockOnHover();
      if (block != null && block.getStoneLayer() == Stone.EMPTY_STONE) {
        block.setTroopLayer(Troop.WORKER);
        block.setStoneLayer(Stone.WHITE);
      }
    }
  }

  public Block randomBlock () {
    return blocks[MathUtils.random(BOARD_SIZE - 1)][MathUtils.random(BOARD_SIZE - 1)];
  }

  public void randomResource (Resource resourceLayer, int num) {
    int i = 0;
    while (i < num) {
      Block randomBlock = randomBlock();
      if (randomBlock.getResourceLayer() == Resource.EMPTY_RESOURCE
          && randomBlock.getRow() != 0
          && randomBlock.getColumn() != 0) {
        randomBlock.setResourceLayer(resourceLayer);
        i++;
      }
    }

  }
}
