package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture boardBackGroundImage;

    public static Texture blackStoneImage;
    public static Texture whiteStoneImage;

    public static Texture woodImage;
    public static Texture clayImage;
    public static Texture ironImage;
    public static Texture cropImage;

    public static Texture swordImage;
    public static Texture shieldImage;
    public static Texture bowImage;

    public static Texture endTurnImage;

    public static Texture trainImage;

    public static Texture rangeImage;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
	}

    public static void load() {
        boardBackGroundImage = loadTexture("grass.png");

        blackStoneImage = loadTexture("black.png");
        whiteStoneImage = loadTexture("white.png");

        woodImage = loadTexture("wood.png");
        clayImage = loadTexture("clay.png");
        ironImage = loadTexture("iron.png");
        cropImage = loadTexture("crop.png");

        swordImage = loadTexture("sword.png");
        shieldImage = loadTexture("shield.png");
        bowImage = loadTexture("bow.png");

        endTurnImage = loadTexture("end_turn.png");

        trainImage = loadTexture("train.png");

        rangeImage = loadTexture("range.png");
    }
}
