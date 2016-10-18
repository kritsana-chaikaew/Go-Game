package com.go;

public class Troop {
  private int hitPoint;
  public Troop () {

  }

  public void setHitPoint (int hp) {
    hitPoint = hp;
  }

  public int getHitPoint () {
    return hitPoint;
  }

  public boolean isDead () {
    return hitPoint == 0;
  }
}
