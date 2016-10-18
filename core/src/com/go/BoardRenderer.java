package com.go;

import com.badlogic.gdx.graphics.Texture;

public class BoardRenderer {
  GoGame game;
  Board board;

  private Texture resourceLayerImage;
  private Texture boardLayerImage;
  private Texture stoneLayerImage;
  private Texture troopLayerImage;

  public BoardRenderer (GoGame game, Board board) {
    this.game = game;
    this.board = board;

    boardLayerImage = Assets.tileCenterImage;
    stoneLayerImage = Assets.tileBlackImage;
    resourceLayerImage = Assets.tileWoodImage;
  }

  public void render () {

    for (int i = 0; i < Board.BOARD_SIZE; i ++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {

        int x = i * Tile.BLOCK_SIZE;
        int y = j * Tile.BLOCK_SIZE;

        renderResourceLayer(board.tiles[i][j]);
        renderBoardLayer(board.tiles[i][j]);
        renderStoneLayer(board.tiles[i][j]);
        renderTroopLayer(board.tiles[i][j]);

        game.batch.begin();

        game.batch.draw(resourceLayerImage, x, y);
        game.batch.draw(boardLayerImage, x, y);

        drawLayer(board.tiles[i][j].getStoneLayer(), stoneLayerImage , x, y);
        drawLayer(board.tiles[i][j].getTroopLayer(), troopLayerImage , x, y);

        if (board.tiles[i][j] == board.tileHover) {
          game.batch.draw(Assets.tileHoverImage, x, y);
        }

        game.batch.end();

      }
    }

  }

  public void drawLayer (int layer, Texture image, int x, int y) {
    if (layer != Tile.EMPTY) {
      game.batch.draw(image, x, y);
    }
  }

  public void renderResourceLayer (Tile tile) {
    if (tile.getResourceLayer() == Tile.EMPTY) {
      resourceLayerImage = Assets.tileBackGroundImage;
    } else if (tile.getResourceLayer() == Tile.WOOD) {
      resourceLayerImage = Assets.tileWoodImage;
    } else if (tile.getResourceLayer() == Tile.CLAY) {
      resourceLayerImage = Assets.tileClayImage;
    } else if (tile.getResourceLayer() == Tile.IRON) {
      resourceLayerImage = Assets.tileIronImage;
    } else if (tile.getResourceLayer() == Tile.CROP) {
      resourceLayerImage = Assets.tileCropImage;
    }
  }

  public void renderBoardLayer (Tile tile) {
    if (tile.getBoardLayer() != Tile.EMPTY) {
      if (tile.getBoardLayer() == Tile.CENTER) {
        boardLayerImage = Assets.tileCenterImage;
      } else if (tile.getBoardLayer() == Tile.LEFT_DOWN_CORNER) {
        boardLayerImage = Assets.tileLeftDownCornerImage;
      } else if (tile.getBoardLayer() == Tile.LEFT_TOP_CORNER) {
        boardLayerImage = Assets.tileLeftTopCornerImage;
      } else if (tile.getBoardLayer() == Tile.RIGHT_DOWN_CORNER) {
        boardLayerImage = Assets.tileRightDownCornerImage;
      } else if (tile.getBoardLayer() == Tile.RIGHT_TOP_CORNER) {
        boardLayerImage = Assets.tileRiightTopCornerImage;
      } else if (tile.getBoardLayer() == Tile.LEFT_SIDE) {
        boardLayerImage = Assets.tileLeftSideImage;
      } else if (tile.getBoardLayer() == Tile.RIGHT_SIDE) {
        boardLayerImage = Assets.tileRightSideImage;
      } else if (tile.getBoardLayer() == Tile.DOWN_SIDE) {
        boardLayerImage = Assets.tileDownSideImage;
      } else if (tile.getBoardLayer() == Tile.TOP_SIDE) {
        boardLayerImage = Assets.tileTopSidImage;
      }
    }

  }

  public void renderStoneLayer (Tile tile) {
    if (tile.getStoneLayer() != Tile.EMPTY) {
      if (tile.getStoneLayer() == Tile.BLACK) {
        stoneLayerImage = Assets.tileBlackImage;
      } else if (tile.getStoneLayer() == Tile.WHITE) {
        stoneLayerImage = Assets.tileWhiteImage;
      }
    }
  }

  public void renderTroopLayer (Tile tile) {
    if (tile.getTroopLayer() == Tile.WORKER) {
      troopLayerImage = Assets.workerImage;
    }
  }

  public void dispose () {
    boardLayerImage.dispose();
  }
}
