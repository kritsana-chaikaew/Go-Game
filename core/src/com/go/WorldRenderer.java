package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class WorldRenderer {
  GoGame game;
  World world;
  BoardRenderer boardRenderer;
  PanelRenderer leftPanelRenderer, rightPanelRenderer;

  private BitmapFont font;

  public WorldRenderer (GoGame game, World world) {
    this.game = game;
    this.world = world;

    boardRenderer = new BoardRenderer(game, world.board);

    leftPanelRenderer = new PanelRenderer(game, world.leftPanel);
    rightPanelRenderer = new PanelRenderer(game, world.rightPanel);

    font = new BitmapFont();
  }

  public void render () {
    boardRenderer.render();
    renderCursor();
    leftPanelRenderer.render();
    rightPanelRenderer.render();
  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.begin();
      game.batch.draw(image, row, column);
      game.batch.end();
    }
  }

  public void drawFont (String string, int row, int column, int x, int y, Color color) {
    font.setColor (color);

    int r = row + x;
    int c = column + y;

System.out.println(r + " " + c);
    game.batch.begin();
    font.draw(game.batch, string, r, c);
    game.batch.end();
  }

  public void renderCursor () {
    int row = Gdx.input.getX() / Block.BLOCK_SIZE * Block.BLOCK_SIZE;
    int column =  ( GoGame.SCREEN_HEIGHT - Gdx.input.getY() ) /
                  Block.BLOCK_SIZE * Block.BLOCK_SIZE;

    if (world.input.stone == Stone.BLACK) {
      drawBlock(Assets.blackStoneImage, row, column);
    } else if (world.input.stone == Stone.WHITE) {
      drawBlock(Assets.whiteStoneImage, row, column);
    }

    if (world.input.troopBlock != null) {
      if ( world.input.troopBlock.hasLayer(Troop.SWORDMAN) ) {
        drawBlock(Assets.swordImage, row, column);
      } else if ( world.input.troopBlock.hasLayer(Troop.BOWMAN) ) {
        drawBlock(Assets.bowImage, row, column);
      } else if ( world.input.troopBlock.hasLayer(Troop.GUARDIAN) ) {
        drawBlock(Assets.shieldImage, row, column);
      }

      if ( !world.input.troopBlock.hasLayer(Troop.EMPTY_TROOP) ) {
        drawFont( "" + world.input.troopBlock.getHP(),
                  row, column, 30, 42, Color.RED);
        drawFont( "" + world.input.troopBlock.getDamage(),
                  row, column, 30, 24, Color.BLUE);
      }
    }

  }

}
