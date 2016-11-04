package com.go;

import com.badlogic.gdx.math.MathUtils;

public class Board {
  public static final int BOARD_SIZE = 6;

  ResourceBlock [][] resourceBlocks;
  StoneBlock [][] stoneBlocks;
  TroopBlock [][] troopBlocks;

  private int x;
  private int y;

  public Board (int x, int y) {
    this.x = toRow(x) * Block.BLOCK_SIZE;
    this.y = toColumn(y) * Block.BLOCK_SIZE;

    setupBoard();
  }

  public int toRow (int x) {
    return x / Block.BLOCK_SIZE;
  }

  public int toColumn (int y) {
    return y / Block.BLOCK_SIZE;
  }

  public int toX (int row) {
    return x + (row * Block.BLOCK_SIZE);
  }

  public int toY (int column) {
    return y + (column * Block.BLOCK_SIZE);
  }

  public void update () {
    TroopBlock troopBlock;

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        troopBlock = troopBlocks[i][j];

        if (  troopBlock.getHP() <= 0
              && !troopBlock.hasLayer(Troop.EMPTY_TROOP) ) {
          removeTroopAt(i, j);
          removeStoneAt(i, j);
        }
      }
    }
  }

  public int getX () {
    return x;
  }

  public int getY () {
    return y;
  }

  public static boolean isInBoard (int i, int j) {
    return  i >= 0 && i < BOARD_SIZE
            && j >= 0 && j < BOARD_SIZE;
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
    randomResource(Resource.WOOD, 4);
    randomResource(Resource.CLAY, 4);
    randomResource(Resource.IRON, 4);
    randomResource(Resource.CROP, 4);
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

  public void setTroopAt (TroopBlock troopBlock, int row, int column) {
    if ( troopBlocks[row][column].hasLayer(Troop.EMPTY_TROOP) ) {
      troopBlocks[row][column].setTroopLayer(troopBlock.getTroopLayer());
      troopBlocks[row][column].setRow(row);
      troopBlocks[row][column].setColumn(column);
      troopBlocks[row][column].setHP(troopBlock.getHP());
      troopBlocks[row][column].setDamage(troopBlock.getDamage());
      troopBlocks[row][column].setAttackRange(troopBlock.getAttackRange());
      troopBlocks[row][column].setCost(troopBlock.getCost());
    }
  }

  public void removeStoneAt (int row, int column) {
    stoneBlocks[row][column].setStoneLayer(Stone.EMPTY_STONE);
  }

  public void removeTroopAt (int row, int column) {
    troopBlocks[row][column] = new TroopBlock(row, column);
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

  public void calculateAttack (Stone stoneLayer) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if ( stoneBlocks[i][j].hasLayer(stoneLayer) ) {
          attackInRange(i, j);
        }
      }
    }
  }

  public void attackInRange (int row, int column) {
    TroopBlock troopBlock = troopBlocks[row][column];
    StoneBlock stoneBlock = stoneBlocks[row][column];
    int a = troopBlock.getAttackRange();

    for (int i = row - a; i <= row + a; i++) {
      for (int j = column - a; j <= column + a; j++) {
        if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE) {
          if ( !stoneBlocks[i][j].hasLayer( stoneBlock.getStoneLayer() )
                && !troopBlocks[i][j].hasLayer(Troop.EMPTY_TROOP) ) {
            troopBlock.attack(troopBlocks[i][j]);
          }
        }
      }
    }
  }


}
