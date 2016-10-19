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
    drawBlock(Assets.woodImage, 2, 0);
    drawBlock(Assets.clayImage, 3, 0);
    drawBlock(Assets.ironImage, 4, 0);
    drawBlock(Assets.cropImage, 5, 0);

    drawBlock(Assets.blackStoneImage, 3, 2);
    drawBlock(Assets.workerImage, 3, 2);

    drawBlock(Assets.blackStoneImage, 3, 3);
    drawBlock(Assets.workerImage, 3, 3);

    drawBlock(Assets.blackStoneImage, 3, 4);
    drawBlock(Assets.workerImage, 3, 4);
    game.batch.end();
  }

  public void drawBlock (Texture image, int row, int column) {
    game.batch.draw(image, panel.getX() + (Block.BLOCK_SIZE * row),
                    GoGame.SCREEN_HEIGHT- (Block.BLOCK_SIZE * (column + 1) ) );
  }


}
