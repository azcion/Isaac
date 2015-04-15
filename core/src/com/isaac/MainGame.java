package com.isaac;

import java.io.IOException;

import com.badlogic.gdx.Game;


public class MainGame extends Game {
	
	public MainScreen Screen;
	
	@Override
	public void create () {
		try {
			Assets.load();
		} catch (IOException e) {

		}
		Screen = new MainScreen(this);
		setScreen(Screen);
	}
	
	public void render (float delta) {
		Screen.render(delta);
	}
}
