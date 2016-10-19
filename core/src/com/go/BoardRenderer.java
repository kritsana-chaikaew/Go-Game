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
  }

  public void render () {

    for (int i = 0; i < Board.BOARD_SIZE; i ++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {

        int x = i * Block.BLOCK_SIZE + board.offsetX;
        int y = j * Block.BLOCK_SIZE + board.offsetY;

        renderResourceLayer(board.blocks[i][j]);
        renderGridLayer(board.blocks[i][j]);
        renderStoneLayer(board.blocks[i][j]);
        renderTroopLayer(board.blocks[i][j]);

        game.batch.begin();

        game.batch.draw(resourceLayerImage, x, y);
        game.batch.draw(boardLayerImage, x, y);

        drawStoneLayer(board.blocks[i][j].getStoneLayer(), stoneLayerImage , x, y);
        drawTroopLayer(board.blocks[i][j].getTroopLayer(), troopLayerImage , x, y);

        if (board.blocks[i][j] == board.blockHover) {
          game.batch.draw(Assets.hoverImage, x, y);
        }

        game.batch.end();

      }
    }

  }

  public void drawStoneLayer (Stone layer, Texture image, int x, int y) {
    if (layer != Stone.EMPTY_STONE && image != null) {
      game.batch.draw(image, x, y);
    }
  }

  public void drawTroopLayer (Troop layer, Texture image, int x, int y) {
    if (layer != Troop.EMPTY_TROOP && image != null) {
      game.batch.draw(image, x, y);
    }
  }

  public void renderResourceLayer (Block block) {
    if (block.getResourceLayer() == Resource.EMPTY_RESOURCE) {
      resourceLayerImage = Assets.backGroundImage;
    } else if (block.getResourceLayer() == Resource.WOOD) {
      resourceLayerImage = Assets.woodImage;
    } else if (block.getResourceLayer() == Resource.CLAY) {
      resourceLayerImage = Assets.clayImage;
    } else if (block.getResourceLayer() == Resource.IRON) {
      resourceLayerImage = Assets.ironImage;
    } else if (block.getResourceLayer() == Resource.CROP) {
      resourceLayerImage = Assets.cropImage;
    }
  }

  public void renderGridLayer (Block block) {
    if (block.getGridLayer() != Grid.EMPTY_GRID) {
      if (block.getGridLayer() == Grid.CENTER) {
        boardLayerImage = Assets.centerImage;
      } else if (block.getGridLayer() == Grid.LEFT_DOWN_CORNER) {
        boardLayerImage = Assets.leftDownCornerImage;
      } else if (block.getGridLayer() == Grid.LEFT_TOP_CORNER) {
        boardLayerImage = Assets.leftTopCornerImage;
      } else if (block.getGridLayer() == Grid.RIGHT_DOWN_CORNER) {
        boardLayerImage = Assets.rightDownCornerImage;
      } else if (block.getGridLayer() == Grid.RIGHT_TOP_CORNER) {
        boardLayerImage = Assets.riightTopCornerImage;
      } else if (block.getGridLayer() == Grid.LEFT_SIDE) {
        boardLayerImage = Assets.leftSideImage;
      } else if (block.getGridLayer() == Grid.RIGHT_SIDE) {
        boardLayerImage = Assets.rightSideImage;
      } else if (block.getGridLayer() == Grid.DOWN_SIDE) {
        boardLayerImage = Assets.downSideImage;
      } else if (block.getGridLayer() == Grid.TOP_SIDE) {
        boardLayerImage = Assets.topSideImage;
      }
    }

  }

  public void renderStoneLayer (Block block) {
    if (block.getStoneLayer() != Stone.EMPTY_STONE) {
      if (block.getStoneLayer() == Stone.BLACK) {
        stoneLayerImage = Assets.blackStoneImage;
      } else if (block.getStoneLayer() == Stone.WHITE) {
        stoneLayerImage = Assets.whiteStoneImage;
      }
    }
  }

  public void renderTroopLayer (Block block) {
    if (block.getTroopLayer() == Troop.WORKER) {
      troopLayerImage = Assets.workerImage;
    }
  }

  public void dispose () {
    boardLayerImage.dispose();
  }
}
