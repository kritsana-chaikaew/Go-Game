package com.go;

import com.badlogic.gdx.graphics.Texture;

public class PanelRenderer {
  GoGame game;
  Panel panel;

  private Texture resourceLayerImage;

  public PanelRenderer (GoGame game, Panel panel) {
    this.game = game;
    this.panel = panel;
  }

  public void render () {
    for (int j = 0; j < Panel.PANEL_HEIGHT; j++) {
      for (int i = 0; i < Panel.PANEL_WIDHT; i++) {
        renderResourceLayer(panel.blocks[i][j]);

        game.batch.begin();
        drawBlock(resourceLayerImage, i, j);
        game.batch.end();
      }
    }
  }

  public void renderResourceLayer (Block block) {
    if (block.getResourceLayer() == Resource.WOOD) {
      resourceLayerImage = Assets.woodImage;
    }
  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.draw(image, panel.getX() + (Block.BLOCK_SIZE * row),
                      GoGame.SCREEN_HEIGHT- (Block.BLOCK_SIZE * (column + 1) ) );
    }
  }


}
