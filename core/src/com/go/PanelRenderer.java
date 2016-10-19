package com.go;

import com.badlogic.gdx.graphics.Texture;

public class PanelRenderer {
  GoGame game;
  Panel panel;
  public PanelRenderer (GoGame game, Panel panel) {
    this.game = game;
    this.panel = panel;
  }

  public void render () {
    game.batch.begin();
    drawBlock(Assets.tileWoodImage, 2, 0);
    drawBlock(Assets.tileClayImage, 3, 0);
    drawBlock(Assets.tileIronImage, 4, 0);
    drawBlock(Assets.tileCropImage, 5, 0);

    drawBlock(Assets.tileBlackImage, 3, 2);
    drawBlock(Assets.workerImage, 3, 2);

    drawBlock(Assets.tileBlackImage, 3, 3);
    drawBlock(Assets.workerImage, 3, 3);

    drawBlock(Assets.tileBlackImage, 3, 4);
    drawBlock(Assets.workerImage, 3, 4);
    game.batch.end();
  }

  public void drawBlock (Texture image, int row, int column) {
    game.batch.draw(image, panel.getX() + (Tile.BLOCK_SIZE * row),
                    GoGame.SCREEN_HEIGHT- (Tile.BLOCK_SIZE * (column + 1) ) );
  }


}
