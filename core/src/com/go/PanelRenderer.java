package com.go;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PanelRenderer {
  GoGame game;
  Panel panel;

  private Texture resourceLayerImage;
  private Texture troopLayerImage;

  private BitmapFont font;

  public PanelRenderer (GoGame game, Panel panel) {
    this.game = game;
    this.panel = panel;

    font = new BitmapFont();
  }

  public void render () {
    for (int j = 0; j < Panel.PANEL_HEIGHT; j++) {
      for (int i = 0; i < Panel.PANEL_WIDHT; i++) {
        renderResourceLayer(panel.getResourceBlock(i, j));
        renderStoneLayer(panel.getStoneBlock(i, j));
        renderTroopLayer(panel.getTroopBlock(i, j));
        renderEndTurnButton();
      }
    }
  }

  public void renderResourceLayer (ResourceBlock resourceBlock) {
    Resource resourceLayer = resourceBlock.getResourceLayer();
    int row = resourceBlock.getRow();
    int column = resourceBlock.getColumn();
    if (resourceLayer == Resource.WOOD) {
      drawBlock(Assets.woodImage, row, column);
      drawResourceFont("" + panel.wood + "", row, column);
      drawRateFont("(+" + panel.woodRate + ")", row, column);
    } else if (resourceLayer == Resource.CLAY) {
      drawBlock(Assets.clayImage, row, column);
      drawResourceFont("" + panel.clay + "", row, column);
      drawRateFont("(+" + panel.clayRate + ")", row, column);
    } else if (resourceLayer == Resource.IRON) {
      drawBlock(Assets.ironImage, row, column);
      drawResourceFont("" + panel.iron + "", row, column);
      drawRateFont("(+" + panel.ironRate + ")", row, column);
    } else if (resourceLayer == Resource.CROP) {
      drawBlock(Assets.cropImage, row, column);
      drawResourceFont("" + panel.crop + "", row, column);
      drawRateFont("(+" + panel.cropRate + ")", row, column);
    }
  }

  public void renderStoneLayer (StoneBlock stoneBlock) {
    Stone stoneLayer = stoneBlock.getStoneLayer();
    int row = stoneBlock.getRow();
    int column = stoneBlock.getColumn();

    if (stoneLayer == Stone.BLACK) {
      drawBlock(Assets.blackStoneImage, row, column);
    } else if (stoneLayer == Stone.WHITE) {
      drawBlock(Assets.whiteStoneImage, row, column);
    }
  }

  public void renderTroopLayer (TroopBlock troopBlock) {
    if (troopBlock.getTroopLayer() == Troop.WORKER) {
      drawBlock(Assets.workerImage,
                troopBlock.getRow(),
                troopBlock.getColumn());
    }
  }

  public void renderEndTurnButton () {
    drawBlock(Assets.endTurnImage,
              panel.getEndTurnButton().getRow(),
              panel.getEndTurnButton().getColumn());
  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.begin();
      game.batch.draw(image,
                      panel.getX() + (Block.BLOCK_SIZE * row),
                      panel.getY() +  GoGame.SCREEN_HEIGHT
                                   - (Block.BLOCK_SIZE * (column + 1) ) );
      game.batch.end();
    }
  }

  public void drawResourceFont (String string, int row, int column) {
    font.setColor (Color.BLACK);

    int r = panel.getX() + (Block.BLOCK_SIZE * row) + 16;
    int c = GoGame.SCREEN_HEIGHT - (Block.BLOCK_SIZE * (column + 1) ) + 40;

    game.batch.begin();
    font.draw(game.batch, string, r, c);
    game.batch.end();
  }

  public void drawRateFont (String string, int row, int column) {
    font.setColor (Color.GREEN);

    int r = panel.getX() + (Block.BLOCK_SIZE * row) + 28;
    int c = GoGame.SCREEN_HEIGHT - (Block.BLOCK_SIZE * (column + 1) ) + 40;

    game.batch.begin();
    font.draw(game.batch, string, r, c);
    game.batch.end();
  }

}
