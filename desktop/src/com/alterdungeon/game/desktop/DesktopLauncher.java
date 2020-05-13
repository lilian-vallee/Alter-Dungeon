package com.alterdungeon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alterdungeon.game.AlterDungeonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Alter dungeon";
		config.width = 854;
		config.height = 480;
		new LwjglApplication(new AlterDungeonGame(), config);
	}
}
