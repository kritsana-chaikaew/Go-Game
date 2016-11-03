package com.go;

public class TroopBlock extends Block {
  Troop troopLayer = Troop.EMPTY_TROOP;

  private int hitPoint = 0;
  private int damage = 0;
  private int attackRange = 0;
  private Cost cost;

  public TroopBlock (int row, int column) {
    super(row, column);

    cost = new Cost(0, 0, 0, 0);
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

  public void setCost (Cost cost) {
    this.cost.wood = cost.wood;
    this.cost.clay = cost.clay;
    this.cost.iron = cost.iron;
    this.cost.crop = cost.crop;
  }

  public Cost getCost() {
    return cost;
  }

  public static boolean isInRange ( int sourceRow, int sourceColumn,
                                    int targetRow, int targetColumn,
                                    int range) {
    return  !(targetRow == sourceRow && targetColumn == sourceColumn )
            && targetRow >= (sourceRow - range)
            && targetRow <= (sourceRow + range)
            && targetColumn >= (sourceColumn - range)
            && targetColumn <= (sourceColumn + range);
  }
}
