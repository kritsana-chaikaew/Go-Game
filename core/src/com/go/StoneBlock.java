package com.go;

public class StoneBlock extends Block {
  Stone stoneLayer = Stone.EMPTY_STONE;
  
  public StoneBlock (int row, int column) {
    super(row, column);
  }

  public void setStoneLayer (Stone stoneLayer) {
    this.stoneLayer = stoneLayer;
  }

  public Stone getStoneLayer () {
    return stoneLayer;
  }
}
