package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
  public static Texture boardBackGroundImage;

  public static Texture gridImage;

  public static Texture blackStoneImage;
  public static Texture whiteStoneImage;

  public static Texture woodImage;
  public static Texture clayImage;
  public static Texture ironImage;
  public static Texture cropImage;

  public static Texture hoverImage;

  public static Texture workerImage;

  public static Texture endTurnImage;


  public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

  public static void load () {
    boardBackGroundImage = loadTexture("board_background.png");

    gridImage = loadTexture("grid.png");

    blackStoneImage = loadTexture("black.png");
    whiteStoneImage = loadTexture("white.png");

    woodImage = loadTexture("wood.png");
    clayImage = loadTexture("clay.png");
    ironImage = loadTexture("iron.png");
    cropImage = loadTexture("crop.png");

    hoverImage = loadTexture("hover.png");

    workerImage = loadTexture("worker.png");

    endTurnImage = loadTexture("end_turn.png");
  }
}
