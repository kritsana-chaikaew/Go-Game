package com.go;

public class TroopBlock extends Block {
  Troop troopLayer = Troop.EMPTY_TROOP;

  private int hitPoint = 0;

  public TroopBlock (int row, int column) {
    super(row, column);
  }
  
  public void setTroopLayer (Troop troopLayer) {
    this.troopLayer = troopLayer;
  }

  public Troop getTroopLayer () {
    return troopLayer;
  }

  public boolean hasLayer (Troop troopLayer) {
    return this.troopLayer == troopLayer;
  }

  public void setHP (int hp) {
    hitPoint = hp;
  }

  public int getHP () {
    return hitPoint;
  }
}
