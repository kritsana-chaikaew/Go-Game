package com.go;

public class TroopBlock extends Block {
  Troop troopLayer = Troop.EMPTY_TROOP;

  private int hitPoint = 0;
  private int damage = 0;
  private int attackRange = 0;

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

  public void setDamage (int damage) {
    this.damage = damage;
  }

  public int getDamage () {
    return damage;
  }

  public void setAttackRange (int range) {
    attackRange = range;
  }

  public int getAttackRange () {
    return attackRange;
  }

  public void attack (TroopBlock target) {
    target.setHP(target.getHP() - damage);
  }
}
