package com.go;

public class TroopBlock extends Block {
  Troop troopLayer = Troop.EMPTY_TROOP;
  
  public TroopBlock (int row, int column) {
    super(row, column);
  }

  public void setTroopLayer (Troop troopLayer) {
    this.troopLayer = troopLayer;
  }

  public Troop getTroopLayer () {
    return troopLayer;
  }
}
