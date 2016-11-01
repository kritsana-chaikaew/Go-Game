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
      drawFont("" + panel.wood + "", row, column, 16, 40, Color.BLACK);
      drawFont("+" + panel.woodRate + "", row, column, 30, 40, Color.GREEN);
    } else if (resourceLayer == Resource.CLAY) {
      drawBlock(Assets.clayImage, row, column);
      drawFont("" + panel.clay + "", row, column, 16, 40, Color.BLACK);
      drawFont("+" + panel.clayRate + "", row, column, 30, 40, Color.GREEN);
    } else if (resourceLayer == Resource.IRON) {
      drawBlock(Assets.ironImage, row, column);
      drawFont("" + panel.iron + "", row, column, 16, 40, Color.BLACK);
      drawFont("+" + panel.ironRate + "", row, column, 30, 40, Color.GREEN);
    } else if (resourceLayer == Resource.CROP) {
      drawBlock(Assets.cropImage, row, column);
      drawFont("" + panel.crop + "", row, column, 16, 40, Color.BLACK);
      drawFont("+" + panel.cropRate + "", row, column, 30, 40, Color.GREEN);
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
    int row = troopBlock.getRow();
    int column = troopBlock.getColumn();

    if (troopBlock.hasLayer(Troop.SWORDMAN)) {
      drawBlock(Assets.swordImage, row, column);
    } else if (troopBlock.hasLayer(Troop.BOWMAN)) {
      drawBlock(Assets.bowImage, row, column);
    } else if (troopBlock.hasLayer(Troop.GUARDIAN)) {
      drawBlock(Assets.shieldImage, row, column);
    }
  }

  public void renderEndTurnButton () {
    drawBlock(Assets.endTurnImage,
              panel.getEndTurnButton().getRow(),
              panel.getEndTurnButton().getColumn());
    if (World.gameState == GameState.BLACK_TURN && panel.getStoneLayer() == Stone.BLACK
        || World.gameState == GameState.WHITE_TURN && panel.getStoneLayer() == Stone.WHITE){
      drawFont("End Turn",
                panel.getEndTurnButton().getRow(),
                panel.getEndTurnButton().getColumn(),
                0, 40,
                Color.BLACK);
    }
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

  public void drawFont (String string, int row, int column, int x, int y, Color color) {
    font.setColor (color);

    int r = panel.getX() + (Block.BLOCK_SIZE * row) + x;
    int c = GoGame.SCREEN_HEIGHT - (Block.BLOCK_SIZE * (column + 1) ) + y;

    game.batch.begin();
    font.draw(game.batch, string, r, c);
    game.batch.end();
  }

}
