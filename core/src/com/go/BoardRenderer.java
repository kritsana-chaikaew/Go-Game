package com.go;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BoardRenderer {
    GoGame game;
    Board board;

    private BitmapFont font;

    public BoardRenderer(GoGame game, Board board) {
        this.game = game;
        this.board = board;

        font = new BitmapFont();
    }

    public void render() {
        for (int i = 0; i < Board.BOARD_SIZE; i ++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                drawBlock(Assets.boardBackGroundImage, i, j);
                renderResourceLayer(board.resourceBlocks[i][j]);
                renderStoneLayer(board.stoneBlocks[i][j]);
                renderTroopLayer(board.troopBlocks[i][j]);
            }
        }
    }

    public void drawBlock(Texture image, int row, int column) {
        if (image != null) {

            game.batch.begin();
            game.batch.draw(image, board.toX(row),
                GoGame.flipYAxis(board.toY(column + 1)));
            game.batch.end();
        }
    }

    public void drawFont(String string, int row, int column,
            int offsetX, int offsetY, Color color) {
        font.setColor (color);

        game.batch.begin();
        font.draw( game.batch, string,
        board.toX(row) + offsetX,
        GoGame.flipYAxis( board.toY(column + 1) ) + offsetY );
        game.batch.end();
    }

    public void renderResourceLayer(ResourceBlock resourceBlock) {
        int row = resourceBlock.getRow();
        int column = resourceBlock.getColumn();

        if (resourceBlock.hasLayer(Resource.WOOD)) {
            drawBlock(Assets.woodImage, row, column);
        } else if (resourceBlock.hasLayer(Resource.CLAY)) {
            drawBlock(Assets.clayImage, row, column);
        } else if (resourceBlock.hasLayer(Resource.IRON) ) {
            drawBlock(Assets.ironImage, row, column);
        } else if (resourceBlock.hasLayer(Resource.CROP) ) {
            drawBlock(Assets.cropImage, row, column);
        }
    }

    public void renderStoneLayer(StoneBlock stoneBlock) {
        int row = stoneBlock.getRow();
        int column = stoneBlock.getColumn();

        if (stoneBlock.hasLayer(Stone.BLACK)) {
            drawBlock(Assets.blackStoneImage, row, column);
        } else if (stoneBlock.hasLayer(Stone.WHITE)) {
            drawBlock(Assets.whiteStoneImage, row, column);
        }
    }

    public void renderTroopLayer (TroopBlock troopBlock) {
        drawTroopImage(troopBlock);
        drawTroopHp(troopBlock);
    }

    public void drawTroopImage(TroopBlock troopBlock) {
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

    public void drawTroopHp(TroopBlock troopBlock) {
        int row = troopBlock.getRow();
        int column = troopBlock.getColumn();

        if (!troopBlock.hasLayer(Troop.EMPTY_TROOP)) {
            if (troopBlock.getHP() > 9) {
                drawFont("" + troopBlock.getHP(),
                    row, column,
                    24, 38, Color.RED);
            } else {
                drawFont("" + troopBlock.getHP(),
                    row, column,
                    28, 38, Color.RED);
            }
        }
    }
}
