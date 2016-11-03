package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class WorldRenderer {
  GoGame game;
  World world;
  Board board;
  BoardRenderer boardRenderer;
  PanelRenderer leftPanelRenderer, rightPanelRenderer;

  private BitmapFont font;

  public WorldRenderer (GoGame game, World world) {
    this.game = game;
    this.world = world;

    board = world.getBoard();

    boardRenderer = new BoardRenderer(game, world.board);

    leftPanelRenderer = new PanelRenderer(game, world.leftPanel);
    rightPanelRenderer = new PanelRenderer(game, world.rightPanel);

    font = new BitmapFont();
  }

  public void render () {
    boardRenderer.render();
    leftPanelRenderer.render();
    rightPanelRenderer.render();

    renderCursor();
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

    TroopBlock troopOnCursor = world.input.troopBlock;

    int r = (row - board.getX()) / Block.BLOCK_SIZE;
    int c = Board.BOARD_SIZE - (column - board.getY()) / Block.BLOCK_SIZE - 1;
    System.out.println("Row on Screen " + r + "Column on Screen " + c);

    if (world.input.stone == Stone.BLACK) {
      drawBlock(Assets.blackStoneImage, row, column);
    } else if (world.input.stone == Stone.WHITE) {
      drawBlock(Assets.whiteStoneImage, row, column);
    }

    if (troopOnCursor != null) {
      for (int i = 0; i < Board.BOARD_SIZE; i++) {
        for ( int j = 0; j < Board.BOARD_SIZE; j++) {
          if (Board.isInBoard(r, c) &&
              TroopBlock.isInRange( r, c, i, j,
                                    troopOnCursor.getAttackRange())) {
            drawBlock(Assets.rangeImage,
                      board.getX() + (i * Block.BLOCK_SIZE),
                      board.getY() + ((Board.BOARD_SIZE - j - 1) * Block.BLOCK_SIZE) );
          }
        }
      }

      if ( troopOnCursor.hasLayer(Troop.SWORDMAN) ) {
        drawBlock(Assets.swordImage, row, column);
      } else if ( world.input.troopBlock.hasLayer(Troop.BOWMAN) ) {
        drawBlock(Assets.bowImage, row, column);
      } else if ( world.input.troopBlock.hasLayer(Troop.GUARDIAN) ) {
        drawBlock(Assets.shieldImage, row, column);
      }

      if ( !world.input.troopBlock.hasLayer(Troop.EMPTY_TROOP) ) {
        if (world.input.troopBlock.getHP() > 9) {
          drawFont( "" + world.input.troopBlock.getHP(),
                    row, column, 24, 38, Color.RED);
        } else {
          drawFont( "" + world.input.troopBlock.getHP(),
                    row, column, 28, 38, Color.RED);
        }
        //drawFont( "" + world.input.troopBlock.getDamage(),
        //          row, column, 28, 24, Color.BLUE);
      }
    }

  }

}
