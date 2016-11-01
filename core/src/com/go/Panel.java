package com.go;

import java.util.List;
import java.util.ArrayList;

public class Panel {
  public static final int PANEL_WIDHT = 4;
  public static final int PANEL_HEIGHT = Board.BOARD_SIZE;

  World world;

  private int x;
  private int y;

  private ResourceBlock [][] resourceBlocks;
  private StoneBlock [][] stoneBlocks;
  private TroopBlock [][] troopBlocks;

  private Block endTurnButton;

  private Stone stoneLayer;


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

  public Panel (int x, int y, Stone stoneLayer, World world) {
    this.x = x;
    this.y = y;
    this.stoneLayer = stoneLayer;
    this.world = world;

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

    endTurnButton = new Block(0, 4);

    resourceBlocks[0][0].setResourceLayer(Resource.WOOD);
    resourceBlocks[1][0].setResourceLayer(Resource.CLAY);
    resourceBlocks[2][0].setResourceLayer(Resource.IRON);
    resourceBlocks[3][0].setResourceLayer(Resource.CROP);

    stoneBlocks[0][2].setStoneLayer(stoneLayer);
    stoneBlocks[1][2].setStoneLayer(stoneLayer);
    stoneBlocks[2][2].setStoneLayer(stoneLayer);

    troopBlocks[0][2].setTroopLayer(Troop.WORKER);
    troopBlocks[1][2].setTroopLayer(Troop.WORKER);
    troopBlocks[2][2].setTroopLayer(Troop.WORKER);
  }

  public Stone getStoneLayer () {
    return stoneLayer;
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

  public Block getEndTurnButton () {
    return endTurnButton;
  }

  public int getX () {
    return x;
  }

  public int getY () {
    return y;
  }


  public void update () {
    setResourceRate(Resource.WOOD, coutOccupyResource(Resource.WOOD));
    setResourceRate(Resource.CLAY, coutOccupyResource(Resource.CLAY));
    setResourceRate(Resource.IRON, coutOccupyResource(Resource.IRON));
    setResourceRate(Resource.CROP, coutOccupyResource(Resource.CROP));
  }

  public void setResourceRate (Resource resource, int value) {
    if (resource == Resource.WOOD) {
      woodRate = value;
    }
    if (resource == Resource.CLAY) {
      clayRate = value;
    }
    if (resource == Resource.IRON) {
      ironRate = value;
    }
    if (resource == Resource.CROP) {
      cropRate = value;
    }
  }

  public void updateResource () {
    wood += woodRate;
    clay += clayRate;
    iron += ironRate;
    crop += cropRate;
  }

  public int coutOccupyResource (Resource resource) {
    int occupy = 0;

    for (int i = 0; i < Board.BOARD_SIZE; i++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {
        if (world.getBoard().getStoneAt(i, j).hasLayer(stoneLayer)
            && world.board.getResourceAt(i ,j).hasLayer(resource)) {
          occupy++;
        }
      }
    }
    System.out.println("occupy: " + occupy);

    return occupy;
  }

  public void beginTurn () {
    updateResource();
  }

  public void endTurn () {
    if (World.gameState == GameState.BLACK_TURN) {
      World.changeGameState(GameState.WHITE_TURN);
    } else if (World.gameState == GameState.WHITE_TURN) {
      World.changeGameState(GameState.BLACK_TURN);
    }
  }

  public boolean isMyTurn () {
    return  World.gameState == GameState.BLACK_TURN
            && stoneLayer == Stone.BLACK
            || World.gameState == GameState.WHITE_TURN
            && stoneLayer == Stone.WHITE;
  }

}
