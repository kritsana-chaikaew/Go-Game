package com.go;

import com.badlogic.gdx.math.MathUtils;

public class Board {
  public static final int BOARD_SIZE = 5;

  ResourceBlock [][] resourceBlocks;
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
    stoneBlocks = new StoneBlock[BOARD_SIZE][BOARD_SIZE];
    troopBlocks = new TroopBlock[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        resourceBlocks[i][j] = new ResourceBlock(i, j);
        stoneBlocks[i][j] = new StoneBlock(i, j);
        troopBlocks[i][j] = new TroopBlock(i, j);
      }
    }

    // CUATION!!! NUMBER ALL RESOURCE MUST NOT EXCEED NUMBER OF BLOCKS
    randomResource(Resource.WOOD, 5);
    randomResource(Resource.CLAY, 5);
    randomResource(Resource.IRON, 5);
    randomResource(Resource.CROP, 5);
  }

  public void randomResource (Resource resourceLayer, int num) {
    int i = 0;

    while (i < num) {
      int row = MathUtils.random(BOARD_SIZE - 1);
      int column = MathUtils.random(BOARD_SIZE - 1);
      if ( resourceBlocks[row][column].hasLayer(Resource.EMPTY_RESOURCE) ) {
        resourceBlocks[row][column].setResourceLayer(resourceLayer);
        i++;
      }
    }

  }

  public void setStoneAt (Stone stoneLayer, int row, int column) {
    if ( stoneBlocks[row][column].hasLayer(Stone.EMPTY_STONE) ) {
      stoneBlocks[row][column].setStoneLayer(stoneLayer);
    }
  }

  public void setTroopAt (Troop troopLayer, int row, int column) {
    if ( troopBlocks[row][column].hasLayer(Troop.EMPTY_TROOP) ) {
      troopBlocks[row][column].setTroopLayer(troopLayer);
    }
  }

  public void removeStoneAt (int row, int column) {
    setStoneAt(Stone.EMPTY_STONE, row, column);
  }

  public void removeTroopAt (int row, int column) {
    setTroopAt(Troop.EMPTY_TROOP, row, column);
  }

  public ResourceBlock getResourceAt (int row, int column) {
    return resourceBlocks[row][column];
  }

  public StoneBlock getStoneAt (int row, int column) {
    return stoneBlocks[row][column];
  }

  public TroopBlock getTroopAt (int row, int column) {
    return troopBlocks[row][column];
  }
}
