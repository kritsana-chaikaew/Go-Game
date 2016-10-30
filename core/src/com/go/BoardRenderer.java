package com.go;

import com.badlogic.gdx.graphics.Texture;

public class BoardRenderer {
  GoGame game;
  Board board;

  public BoardRenderer (GoGame game, Board board) {
    this.game = game;
    this.board = board;
  }

  public void render () {

    for (int i = 0; i < Board.BOARD_SIZE; i ++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {
        drawBlock(Assets.boardBackGroundImage, i, j);
        renderResourceLayer(board.resourceBlocks[i][j]);
        drawBlock(Assets.gridImage, i, j);
        renderStoneLayer(board.stoneBlocks[i][j]);
        renderTroopLayer(board.troopBlocks[i][j]);
      }
    }

  }

  public void drawBlock (Texture image, int row, int column) {
    if (image != null) {
      game.batch.begin();
      game.batch.draw(image, board.getX() + (Block.BLOCK_SIZE * row),
                      GoGame.SCREEN_HEIGHT - (Block.BLOCK_SIZE * (column + 1) ) );
      game.batch.end();
    }
  }

  public void renderResourceLayer (ResourceBlock resourceBlock) {
    if (resourceBlock.getResourceLayer() != Resource.EMPTY_RESOURCE) {
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
  }

  public void renderStoneLayer (StoneBlock stoneBlock) {
    if (stoneBlock.getStoneLayer() != Stone.EMPTY_STONE) {
      if (stoneBlock.getStoneLayer() == Stone.BLACK) {
        drawBlock(Assets.blackStoneImage, stoneBlock.getRow(), stoneBlock.getColumn());
      } else if (stoneBlock.getStoneLayer() == Stone.WHITE) {
        drawBlock(Assets.whiteStoneImage, stoneBlock.getRow(), stoneBlock.getColumn());
      }
    }
  }

  public void renderTroopLayer (TroopBlock troopBlock) {
    if (troopBlock.getTroopLayer() == Troop.WORKER) {
      drawBlock(Assets.workerImage, troopBlock.getRow(), troopBlock.getColumn());
    }
  }
}
