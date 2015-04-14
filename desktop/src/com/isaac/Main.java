package com.isaac;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main (String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Isaac";
		cfg.width = 468*3;
		cfg.height = 312*3;
		cfg.resizable = true;
		
		new LwjglApplication(new MainGame(), cfg);
	}
}
