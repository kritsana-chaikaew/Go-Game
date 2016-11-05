package com.go;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Panel {
    public static final int PANEL_WIDHT = 6;
    public static final int PANEL_HEIGHT = 5;

    World world;

    private int x;
    private int y;

    private ResourceBlock [][] resourceBlocks;
    private StoneBlock [][] stoneBlocks;
    private TroopBlock [][] troopBlocks;

    private Block endTurnButton;

    private Stone stoneLayer;

    private boolean beginTurn = false;


    int wood;
    int clay;
    int iron;
    int crop;

    int woodRate;
    int clayRate;
    int ironRate;
    int cropRate;

    int availableSwordMan;
    int availableBowMan;
    int availableGuardian;

    int currentSwordMan;
    int currentBowMan;
    int currentGuardian;

    public Panel(int x, int y, Stone stoneLayer, World world) {
        this.x = x;
        this.y = y;
        this.stoneLayer = stoneLayer;
        this.world = world;

        setupPanel();
    }

    public void setupPanel() {
        resourceBlocks = new ResourceBlock[PANEL_WIDHT][PANEL_HEIGHT];
        stoneBlocks = new StoneBlock[PANEL_WIDHT][PANEL_HEIGHT];
        troopBlocks = new TroopBlock[PANEL_WIDHT][PANEL_HEIGHT];

        for (int j = 0; j < PANEL_HEIGHT; j++) {
            for (int i = 0; i < PANEL_WIDHT; i++) {
                resourceBlocks[i][j] = new ResourceBlock(i, j);
                stoneBlocks[i][j] = new StoneBlock(i, j);
                troopBlocks[i][j] = new TroopBlock(i, j);
            }
        }

        endTurnButton = new Block(0, 4);

        resourceBlocks[1][0].setResourceLayer(Resource.WOOD);
        resourceBlocks[2][0].setResourceLayer(Resource.CLAY);
        resourceBlocks[3][0].setResourceLayer(Resource.IRON);
        resourceBlocks[4][0].setResourceLayer(Resource.CROP);

        stoneBlocks[0][1].setStoneLayer(stoneLayer);
        stoneBlocks[0][2].setStoneLayer(stoneLayer);
        stoneBlocks[0][3].setStoneLayer(stoneLayer);

        troopBlocks[0][1].setTroopLayer(Troop.SWORDMAN);
        troopBlocks[0][2].setTroopLayer(Troop.BOWMAN);
        troopBlocks[0][3].setTroopLayer(Troop.GUARDIAN);

        troopBlocks[0][1].setHP(5);
        troopBlocks[0][2].setHP(3);
        troopBlocks[0][3].setHP(10);

        troopBlocks[0][1].setDamage(2);
        troopBlocks[0][2].setDamage(1);
        troopBlocks[0][3].setDamage(0);

        troopBlocks[0][1].setAttackRange(1);
        troopBlocks[0][2].setAttackRange(2);
        troopBlocks[0][3].setAttackRange(0);

        troopBlocks[0][1].setCost(new Cost(1, 2, 3, 2));
        troopBlocks[0][2].setCost(new Cost(3, 1, 1, 1));
        troopBlocks[0][3].setCost(new Cost(2, 2, 2, 2));
    }

    public Stone getStoneLayer() {
        return stoneLayer;
    }

    public ResourceBlock getResourceBlock(int row, int column) {
        return resourceBlocks[row][column];
    }

    public StoneBlock getStoneBlock(int row, int column) {
        return stoneBlocks[row][column];
    }

    public TroopBlock getTroopBlock(int row, int column) {
        return troopBlocks[row][column];
    }

    public Block getEndTurnButton() {
        return endTurnButton;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void update() {
        setResourceRate(Resource.WOOD, coutOccupyResource(Resource.WOOD));
        setResourceRate(Resource.CLAY, coutOccupyResource(Resource.CLAY));
        setResourceRate(Resource.IRON, coutOccupyResource(Resource.IRON));
        setResourceRate(Resource.CROP, coutOccupyResource(Resource.CROP));

        if (isMyTurn() && !beginTurn) {
            beginTurn();
        }

        availableSwordMan = countTrain(troopBlocks[0][1]);
        availableBowMan = countTrain(troopBlocks[0][2]);
        availableGuardian = countTrain(troopBlocks[0][3]);
    }

    public void setResourceRate(Resource resource, int value) {
        if (resource == Resource.WOOD) {
            woodRate = value;
        }

        if (resource == Resource.CLAY) {
            clayRate = value;
        }

        if (resource == Resource.IRON) {
            ironRate = value;
        }

        if (resource == Resource.CROP) {
            cropRate = value;
        }
    }

    public void updateResource() {
        wood += woodRate;
        clay += clayRate;
        iron += ironRate;
        crop += cropRate;
    }

    public int coutOccupyResource(Resource resource) {
        int occupy = 1;

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (world.getBoard().getStoneAt(i, j).hasLayer(stoneLayer)
                        && world.board.getResourceAt(i ,j).hasLayer(resource)) {
                    occupy++;
                }
            }
        }

        return occupy;
    }

    public void reduceResource(Cost cost) {
        wood -= cost.wood;
        clay -= cost.clay;
        iron -= cost.iron;
        crop -= cost.crop;
    }

    public void beginTurn() {
        updateResource();
        beginTurn = true;
    }

    public void endTurn() {
        if (World.gameState == GameState.BLACK_TURN) {
            World.changeGameState(GameState.WHITE_TURN);
        } else if (World.gameState == GameState.WHITE_TURN) {
            World.changeGameState(GameState.BLACK_TURN);
        }

        world.getBoard().calculateAttack(stoneLayer);

        beginTurn = false;
    }

    public boolean isMyTurn() {
        return  World.gameState == GameState.BLACK_TURN
                && stoneLayer == Stone.BLACK
                || World.gameState == GameState.WHITE_TURN
                && stoneLayer == Stone.WHITE;
    }

    public boolean isEnoughResource(Cost cost) {
        return  isEnoughWood(cost.wood)
                && isEnoughClay(cost.clay)
                && isEnoughIron(cost.iron)
                && isEnoughCrop(cost.crop);
    }

    public boolean isEnoughWood(int cost) {
        return wood >= cost;
    }

    public boolean isEnoughClay(int cost) {
        return clay >= cost;
    }

    public boolean isEnoughIron(int cost) {
        return iron >= cost;
    }

    public boolean isEnoughCrop(int cost) {
        return crop >= cost;
    }

    public int countTrain(TroopBlock troopBlock) {
        if (isEnoughResource(troopBlock.getCost())) {
            int fromWood = wood / troopBlock.getCost().wood;
            int fromClay = clay / troopBlock.getCost().clay;
            int fromIron = iron / troopBlock.getCost().iron;
            int fromCrop = crop / troopBlock.getCost().crop;

            return Math.min(Math.min(Math.min(fromWood, fromClay),
                    fromIron), fromCrop);
        }

        return 0;
    }

    public void train(Troop troopLayer) {
        if (troopLayer == Troop.SWORDMAN && availableSwordMan > 0) {
            availableSwordMan--;
            currentSwordMan++;
            reduceResource(troopBlocks[0][1].getCost());
        } else if (troopLayer == Troop.BOWMAN && availableBowMan > 0) {
            availableBowMan--;
            currentBowMan++;
            reduceResource(troopBlocks[0][2].getCost() );
        } else if (troopLayer == Troop.GUARDIAN && availableGuardian > 0) {
            availableGuardian--;
            currentGuardian++;
            reduceResource(troopBlocks[0][3].getCost() );
        }
    }

    public int getCurrentTroop(TroopBlock troopBlock) {
        if (troopBlock != null) {
            if (troopBlock.hasLayer(Troop.SWORDMAN)) {
                return currentSwordMan;
            } else if (troopBlock.hasLayer(Troop.BOWMAN)) {
                return currentBowMan;
            } else if (troopBlock.hasLayer(Troop.GUARDIAN)) {
                return currentGuardian;
            }
        }

        return 0;
    }
}
