package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;

public class Input extends InputAdapter {
    World world;
    Board board;
    Panel leftPanel;
    Panel rightPanel;

    TroopBlock selectedTroop;
    Stone stone = Stone.EMPTY_STONE;
    Panel lastClickPanel;

    public Input(World world) {
        this.world = world;
        this.board = world.board;
        this.leftPanel = world.leftPanel;
        this.rightPanel = world.rightPanel;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        int row = x / Block.BLOCK_SIZE;
        int column = y / Block.BLOCK_SIZE;

        onClick(row, column);

        return true;
    }

    public void onClick(int row, int column) {
        if (isClickOnLeftPanel(row, column)) {
            clickOnPanel(leftPanel, row, column);
        } else if (isClickOnBoard(row, column)) {
            clickOnBoard(row - board.getX() / Block.BLOCK_SIZE, column);
        } else if (isClickOnRightPanel(row, column)) {
            clickOnPanel(rightPanel, row - rightPanel.getX()
                / Block.BLOCK_SIZE, column);
        } else {
            clearSelection();
        }
    }

    public boolean isClickOnRightPanel(int row, int column) {
        return  row >= rightPanel.getX() / Block.BLOCK_SIZE
                && row < (rightPanel.getX() / Block.BLOCK_SIZE)
                    + Panel.PANEL_WIDHT
                && column < Panel.PANEL_HEIGHT;
    }

    public boolean isClickOnLeftPanel(int row, int column) {
        return  row >= leftPanel.getX() / Block.BLOCK_SIZE
                && row < board.getX() / Block.BLOCK_SIZE
                && column < Panel.PANEL_HEIGHT;
    }

    public boolean isClickOnBoard(int row, int column) {
        return  row >= board.getX() / Block.BLOCK_SIZE
                && row < (board.getX() / Block.BLOCK_SIZE) + Board.BOARD_SIZE
                && column < Board.BOARD_SIZE;
    }

    public void clickOnBoard(int row, int column) {
        if (selectedTroop != null && isNoTroopAt(row, column)) {
            board.setTroopAt(selectedTroop, row, column);
            board.setStoneAt(stone, row, column);

            calculateCurrentTroop();
            clearSelection();
        }
    }

    public boolean isNoTroopAt(int row, int column) {
        return board.getTroopAt(row, column).hasLayer(Troop.EMPTY_TROOP);
    }

    public void calculateCurrentTroop() {
        if (selectedTroop.hasLayer(Troop.SWORDMAN)) {
            lastClickPanel.currentSwordMan--;
        } else if (selectedTroop.hasLayer(Troop.BOWMAN)) {
            lastClickPanel.currentBowMan--;
        } else if (selectedTroop.hasLayer(Troop.GUARDIAN)) {
            lastClickPanel.currentGuardian--;
        }
    }

    public void clickOnPanel(Panel panel, int row, int column) {
        if (canClickOnPanel(panel)) {
            lastClickPanel = panel;

            if (isEndTurnButtonClick(panel, row, column)
                    && selectedTroop == null) {
                panel.endTurn();
            }

            if (isTrainButtonClick(panel, row, column)) {
                panel.train(getTroopBlockOnClick(panel,
                        row - 5, column).getTroopLayer());
            }

            if (panel.getCurrentTroop(getTroopBlockOnClick(panel,
                        row, column)) > 0) {
                selectedTroop = getTroopBlockOnClick(panel, row, column);
                stone = getStoneOnclick(panel, row, column);
            }
        }
    }

    public TroopBlock getTroopBlockOnClick(Panel panel, int row, int column) {
        if (row >=0 && row < Panel.PANEL_WIDHT
                && column >=0 && column < Panel.PANEL_HEIGHT) {
            return panel.getTroopBlock(row, column);
        } else {
            return null;
        }
    }

    public Stone getStoneOnclick(Panel panel, int row, int column) {
        if (row < Panel.PANEL_WIDHT
                && column < Panel.PANEL_HEIGHT) {
            return panel.getStoneBlock(row, column).getStoneLayer();
        } else {
            return Stone.EMPTY_STONE;
        }
    }

    public void setStoneOnClick(Stone stone, int row, int column) {
        if (board.stoneBlocks[row][column].getStoneLayer()
                == Stone.EMPTY_STONE){
            board.stoneBlocks[row][column].setStoneLayer(stone);
        }
    }

    public void clearSelection() {
        selectedTroop = null;
        stone = Stone.EMPTY_STONE;
    }

    public boolean canClickOnPanel(Panel panel) {
        boolean canClick = (panel.getStoneLayer() == Stone.BLACK
                                && World.gameState == GameState.BLACK_TURN)
                            || (panel.getStoneLayer() == Stone.WHITE
                                && World.gameState == GameState.WHITE_TURN);
        return canClick;
    }

    public boolean isEndTurnButtonClick(Panel panel, int row, int column) {
        return  row == panel.getEndTurnButton().getRow()
                && column == panel.getEndTurnButton().getColumn();
    }

    public boolean isTrainButtonClick(Panel panel, int row, int column) {
        if (getTroopBlockOnClick(panel, row - 5, column) != null) {
            return !getTroopBlockOnClick(panel,
                        row - 5, column).hasLayer(Troop.EMPTY_TROOP);
        }
        return false;
    }
}
