package com.isaac;

import com.badlogic.gdx.Game;


public class MainGame extends Game {
	
	public MainScreen Screen;
	
	@Override
	public void create () {
		Assets.load();
		Screen = new MainScreen(this);
		setScreen(Screen);
	}
	
	public void render (float delta) {
		Screen.render(delta);
	}
}
