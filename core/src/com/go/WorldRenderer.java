package com.go;

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
    panelRenderer.render();
  }
}
