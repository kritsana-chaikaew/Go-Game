package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Board {
  public static final int BOARD_SIZE = 10;

  Block blockHover;

  ResourceBlock [][] resourceBlocks;
  GridBlock [][] gridBlocks;
  StoneBlock [][] stoneBlocks;
  TroopBlock [][] troopBlocks;

  public int offsetX;
  public int offsetY;

  public Board (int x, int y) {
    offsetX = x / 64 * 64;
    offsetY = y / 64 * 64;

    setupBoard();
  }

  public void update (float delta) {

  }

  public int getX () {
    return offsetX;
  }

  public int getY () {
    return offsetY;
  }

  public void setupBoard () {
    resourceBlocks = new ResourceBlock[BOARD_SIZE][BOARD_SIZE];
    gridBlocks = new GridBlock[BOARD_SIZE][BOARD_SIZE];
    stoneBlocks = new StoneBlock[BOARD_SIZE][BOARD_SIZE];
    troopBlocks = new TroopBlock[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {

        Grid gridLayer;

        if ( (i == 0 && j == 0)) {
          gridLayer = Grid.LEFT_TOP_CORNER;
        } else if ( i == 0 && j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.LEFT_DOWN_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == 0 ) {
          gridLayer = Grid.RIGHT_TOP_CORNER;
        } else if ( i == BOARD_SIZE - 1 && j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.RIGHT_DOWN_CORNER;
        } else if ( i == 0 ) {
          gridLayer = Grid.LEFT_SIDE;
        } else if ( i == BOARD_SIZE - 1 ) {
          gridLayer = Grid.RIGHT_SIDE;
        } else if ( j == 0 ) {
          gridLayer = Grid.TOP_SIDE;
        } else if ( j == BOARD_SIZE - 1 ) {
          gridLayer = Grid.DOWN_SIDE;
        } else {
          gridLayer = Grid.CENTER;
        }

        resourceBlocks[i][j] = new ResourceBlock(i, j);

        gridBlocks[i][j] = new GridBlock(i, j);
        if (gridLayer != null) {
          gridBlocks[i][j].setGridLayer(gridLayer);
        }

        stoneBlocks[i][j] = new StoneBlock(i, j);
        troopBlocks[i][j] = new TroopBlock(i, j);
      }
    }

    // CUATION!!! NUMBER ALL RESOURCE MUST NOT EXCEED NUMBER OF BLOCKS
    setupResource(Resource.WOOD, 5);
    setupResource(Resource.CLAY, 5);
    setupResource(Resource.IRON, 5);
    setupResource(Resource.CROP, 5);

    setTroopAt(Troop.WORKER, 0, 0);
    setStoneAt(Stone.BLACK, 0, 0);
  }

  public void setupResource (Resource resourceLayer, int num) {
    int i = 0;
    while (i < num) {
      int row = MathUtils.random(BOARD_SIZE - 1);
      int column = MathUtils.random(BOARD_SIZE - 1);
      if (resourceBlocks[row][column].getResourceLayer() == Resource.EMPTY_RESOURCE) {
        resourceBlocks[row][column].setResourceLayer(resourceLayer);
        i++;
      }
    }

  }

  public void setStoneAt (Stone stoneLayer, int row, int column) {
    if (stoneBlocks[row][column].getStoneLayer() == Stone.EMPTY_STONE) {
      stoneBlocks[row][column].setStoneLayer(stoneLayer);
    }
  }

  public void setTroopAt (Troop troopLayer, int row, int column) {
    if (troopBlocks[row][column].getTroopLayer() == Troop.EMPTY_TROOP) {
      troopBlocks[row][column].setTroopLayer(troopLayer);
    }
  }

  public ResourceBlock getResourceBlock (int row, int column) {
    return resourceBlocks[row][column];
  }

  public StoneBlock getStoneBlock (int row, int column) {
    return stoneBlocks[row][column];
  }

  public TroopBlock getTroopBlock (int row, int column) {
    return troopBlocks[row][column];
  }
}
