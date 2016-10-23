package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class WorldRenderer {
  GoGame game;
  World world;
  BoardRenderer boardRenderer;
  PanelRenderer panelRenderer;

  public WorldRenderer (GoGame game, World world) {
    this.game = game;
    this.world = world;

    boardRenderer = new BoardRenderer(game, world.board);

    panelRenderer = new PanelRenderer(game, world.panel);
  }

  public void render () {
    boardRenderer.render();
    renderCursor();
    panelRenderer.render();
  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.begin();
      game.batch.draw(image, row, column);
      game.batch.end();
    }
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

    if (world.input.troop == Troop.WORKER) {
      drawBlock(Assets.workerImage, row, column);
    }

  }

}
