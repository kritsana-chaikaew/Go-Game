package com.go;

import com.badlogic.gdx.graphics.Texture;

public class PanelRenderer {
  GoGame game;
  Panel panel;

  private Texture resourceLayerImage;
  private Texture troopLayerImage;

  public PanelRenderer (GoGame game, Panel panel) {
    this.game = game;
    this.panel = panel;
  }

  public void render () {
    for (int j = 0; j < Panel.PANEL_HEIGHT; j++) {
      for (int i = 0; i < Panel.PANEL_WIDHT; i++) {
        renderResourceLayer(panel.getResourceBlock(i, j));
        renderStoneLayer(panel.getStoneBlock(i, j));
        renderTroopLayer(panel.getTroopBlock(i, j));
      }
    }
  }

  public void renderResourceLayer (ResourceBlock resourceBlock) {
    if (resourceBlock.getResourceLayer() == Resource.WOOD) {
      drawBlock(Assets.woodImage, resourceBlock.getRow(), resourceBlock.getColumn());
    } else if (resourceBlock.getResourceLayer() == Resource.CLAY) {
      drawBlock(Assets.clayImage, resourceBlock.getRow(), resourceBlock.getColumn());
    } else if (resourceBlock.getResourceLayer() == Resource.IRON) {
      drawBlock(Assets.ironImage, resourceBlock.getRow(), resourceBlock.getColumn());
    } else if (resourceBlock.getResourceLayer() == Resource.CROP) {
      drawBlock(Assets.cropImage, resourceBlock.getRow(), resourceBlock.getColumn());
    }
  }

  public void renderStoneLayer (StoneBlock stoneBlock) {
    if (stoneBlock.getStoneLayer() == Stone.BLACK) {
      drawBlock(Assets.blackStoneImage, stoneBlock.getRow(), stoneBlock.getColumn());
    } else if (stoneBlock.getStoneLayer() == Stone.WHITE) {
      drawBlock(Assets.whiteStoneImage, stoneBlock.getRow(), stoneBlock.getColumn());
    }
  }

  public void renderTroopLayer (TroopBlock troopBlock) {
    if (troopBlock.getTroopLayer() == Troop.WORKER) {
      drawBlock(Assets.workerImage, troopBlock.getRow(), troopBlock.getColumn());
    }
  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.begin();
      game.batch.draw(image, panel.getX() + (Block.BLOCK_SIZE * row),
                      GoGame.SCREEN_HEIGHT - (Block.BLOCK_SIZE * (column + 1) ) );
      game.batch.end();
    }
  }


}
