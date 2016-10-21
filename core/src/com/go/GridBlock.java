package com.go;

public class GridBlock extends Block {
  Grid gridLayer = Grid.EMPTY_GRID;

  public GridBlock (int row, int column) {
    super(row, column);
  }

  public void setGridLayer (Grid gridLayer) {
    this.gridLayer = gridLayer;
  }

  public Grid getGridLayer () {
    return gridLayer;
  }
}
