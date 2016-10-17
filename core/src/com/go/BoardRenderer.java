package com.go;

import com.badlogic.gdx.graphics.Texture;

public class BoardRenderer {
  GoGame game;

  private Texture tileCenter;
  private Texture tileSide1;
  private Texture tileSide2;
  private Texture tileSide3;
  private Texture tileSide4;
  private Texture tileCorner1;
  private Texture tileCorner2;
  private Texture tileCorner3;
  private Texture tileCorner4;

  public BoardRenderer (GoGame game) {
    this.game = game;

    tileCenter = new Texture("tile_center.png");
    tileSide1 = new Texture("tile_side1.png");
    tileSide2 = new Texture("tile_side2.png");
    tileSide3 = new Texture("tile_side3.png");
    tileSide4 = new Texture("tile_side4.png");
    tileCorner1 = new Texture("tile_corner1.png");
    tileCorner2 = new Texture("tile_corner2.png");
    tileCorner3 = new Texture("tile_corner3.png");
    tileCorner4 = new Texture("tile_corner4.png");
  }

  public void render () {
    for (int i = 0; i < 13; i ++) {
      for (int j = 0; j < 13; j++) {

        if ( (i == 0 && j == 0)) {
          game.batch.draw(tileCorner1, i * 64, j * 64);
        } else if ( i == 0 && j == 12 ) {
          game.batch.draw(tileCorner2, i * 64, j * 64);
        } else if ( i == 12 && j == 0 ) {
          game.batch.draw(tileCorner3, i * 64, j * 64);
        } else if ( i == 12 && j == 12 ) {
          game.batch.draw(tileCorner4, i * 64, j * 64);
        } else if ( i == 0 ) {
          game.batch.draw(tileSide1, i * 64, j * 64);
        } else if ( i == 12 ) {
          game.batch.draw(tileSide3, i * 64, j * 64);
        } else if ( j == 0 ) {
          game.batch.draw(tileSide4, i * 64, j * 64);
        } else if ( j == 12 ) {
          game.batch.draw(tileSide2, i * 64, j * 64);
        } else {
          game.batch.draw(tileCenter, i * 64, j * 64);
        }

      }
    }
  }
}
