package com.go;

public class WorldRenderer {
  GoGame game;
  World world;
  BoardRenderer boardRenderer;
  public WorldRenderer (GoGame game, World world) {
    this.game = game;
    this.world = world;

    boardRenderer = new BoardRenderer(game, world.board);
  }

  public void render () {
    boardRenderer.render();
  }
}
