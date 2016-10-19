package com.go;

public class Block {
  public static final int BLOCK_SIZE = 64;

  Resource resourceLayer = Resource.EMPTY_RESOURCE;
  Grid gridLayer = Grid.EMPTY_GRID;
  Stone stoneLayer = Stone.EMPTY_STONE;
  Troop troopLayer = Troop.EMPTY_TROOP;

  private int row;
  private int column;

  public Block (int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }

  public void setResourceLayer (Resource resourceLayer) {
    this.resourceLayer = resourceLayer;
  }

  public void setGridLayer (Grid gridLayer) {
    this.gridLayer = gridLayer;
  }

  public void setStoneLayer (Stone stoneLayer) {
    this.stoneLayer = stoneLayer;
  }

  public void setTroopLayer (Troop troopLayer) {
    this.troopLayer = troopLayer;
  }

  public Resource getResourceLayer () {
    return resourceLayer;
  }

  public Grid getGridLayer () {
    return gridLayer;
  }

  public Stone getStoneLayer () {
    return stoneLayer;
  }

  public Troop getTroopLayer () {
    return troopLayer;
  }
}
