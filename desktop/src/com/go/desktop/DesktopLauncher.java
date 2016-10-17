package com.go.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.go.GoGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Go Game";
		config.width = GoGame.SCREEN_WIDTH;
		config.height = GoGame.SCREEN_HEIGHT;
		new LwjglApplication(new GoGame(), config);
	}
}
