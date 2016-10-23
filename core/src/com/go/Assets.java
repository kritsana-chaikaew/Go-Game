package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
  public static Texture backGroundImage;

  public static Texture centerImage;
  public static Texture leftDownCornerImage;
  public static Texture leftTopCornerImage;
  public static Texture rightDownCornerImage;
  public static Texture riightTopCornerImage;
  public static Texture leftSideImage;
  public static Texture rightSideImage;
  public static Texture topSideImage;
  public static Texture downSideImage;

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
    backGroundImage = loadTexture("background.png");

    centerImage = loadTexture("center.png");
    leftDownCornerImage = loadTexture("corner1.png");
    leftTopCornerImage = loadTexture("corner2.png");
    rightDownCornerImage = loadTexture("corner3.png");
    riightTopCornerImage = loadTexture("corner4.png");
    leftSideImage = loadTexture("side1.png");
    rightSideImage = loadTexture("side3.png");
    topSideImage = loadTexture("side2.png");
    downSideImage = loadTexture("side4.png");

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
