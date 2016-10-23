package com.go;

import java.util.List;
import java.util.ArrayList;

public class Panel {
  public static final int PANEL_WIDHT = 5;
  public static final int PANEL_HEIGHT = Board.BOARD_SIZE;

  private int x;
  private int y;

  private ResourceBlock [][] resourceBlocks;
  private StoneBlock [][] stoneBlocks;
  private TroopBlock [][] troopBlocks;


  int wood = 0;
  int clay = 0;
  int iron = 0;
  int crop = 0;

  int woodRate = 0;
  int clayRate = 0;
  int ironRate = 0;
  int cropRate = 0;

  int totalWorker = 0;
  int trainingWorker = 0;

  public Panel (int x, int y) {
    this.x = x;
    this.y = y;

    setupPanel();
  }

  public void setupPanel () {
    resourceBlocks = new ResourceBlock[PANEL_WIDHT][PANEL_HEIGHT];
    stoneBlocks = new StoneBlock[PANEL_WIDHT][PANEL_HEIGHT];
    troopBlocks = new TroopBlock[PANEL_WIDHT][PANEL_HEIGHT];

    for (int j = 0; j < PANEL_HEIGHT; j++) {
      for (int i = 0; i < PANEL_WIDHT; i++) {
        resourceBlocks[i][j] = new ResourceBlock(i, j);
        stoneBlocks[i][j] = new StoneBlock(i, j);
        troopBlocks[i][j] = new TroopBlock(i, j);
      }
    }

    resourceBlocks[1][0].setResourceLayer(Resource.WOOD);
    resourceBlocks[2][0].setResourceLayer(Resource.CLAY);
    resourceBlocks[3][0].setResourceLayer(Resource.IRON);
    resourceBlocks[4][0].setResourceLayer(Resource.CROP);

    stoneBlocks[2][2].setStoneLayer(Stone.BLACK);
    stoneBlocks[3][2].setStoneLayer(Stone.WHITE);
    stoneBlocks[4][2].setStoneLayer(Stone.BLACK);

    troopBlocks[2][2].setTroopLayer(Troop.WORKER);
    troopBlocks[3][2].setTroopLayer(Troop.WORKER);
    troopBlocks[4][2].setTroopLayer(Troop.WORKER);
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

  public int getX () {
    return x;
  }

  public int getY () {
    return y;
  }


  public void update () {
    updateWood();
    updateClay();
    updateIron();
    updateCrop();
  }

  public void updateWood () {
    wood += woodRate;
  }

  public void updateClay () {
    clay += clayRate;
  }

  public void updateIron () {
    iron += ironRate;
  }

  public void updateCrop () {
    crop += cropRate;
  }
}
