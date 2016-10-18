package com.go;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
  public static Texture tileCenterImage;
  public static Texture tileLeftDownCornerImage;
  public static Texture tileLeftTopCornerImage;
  public static Texture tileRightDownCornerImage;
  public static Texture tileRiightTopCornerImage;
  public static Texture tileLeftSideImage;
  public static Texture tileRightSideImage;
  public static Texture tileTopSidImage;
  public static Texture tileDownSideImage;
  public static Texture tileBlackImage;
  public static Texture tileWhiteImage;
  public static Texture tileWoodImage;
  public static Texture tileClayImage;
  public static Texture tileIronImage;
  public static Texture tileCropImage;
  public static Texture tileHoverImage;


  public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

  public static void load () {
    tileCenterImage = loadTexture("tile_center.png");
    tileLeftDownCornerImage = loadTexture("tile_corner1.png");
    tileLeftTopCornerImage = loadTexture("tile_corner2.png");
    tileRightDownCornerImage = loadTexture("tile_corner3.png");
    tileRiightTopCornerImage = loadTexture("tile_corner4.png");
    tileLeftSideImage = loadTexture("tile_side1.png");
    tileRightSideImage = loadTexture("tile_side3.png");
    tileTopSidImage = loadTexture("tile_side2.png");
    tileDownSideImage = loadTexture("tile_side4.png");
    tileBlackImage = loadTexture("black.png");
    tileWhiteImage = loadTexture("white.png");
    tileWoodImage = loadTexture("wood.png");
    tileClayImage = loadTexture("clay.png");
    tileIronImage = loadTexture("iron.png");
    tileCropImage = loadTexture("crop.png");
    tileHoverImage = loadTexture("hover.png");
  }
}
