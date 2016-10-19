package com.go;

import java.util.List;
import java.util.ArrayList;

public class Panel {
  private int x;
  private int y;
  private int woodRate;
  private int clayRate;
  private int ironRate;
  private int cropRate;
  private int wood;
  private int clay;
  private int iron;
  private int crop;
  public Panel (int x, int y) {
    this.x = x;
    this.y = y;

    getPlayerData();
  }

  public void getPlayerData () {

  }

  public int getX () {
    return x;
  }

  public int getY () {
    return y;
  }
}
