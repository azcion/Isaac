package com.isaac;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class MainGame extends Game {
	
	public MainScreen Screen;
	public static final int Vw = 320;
	public static final int Vh = 240;
	public static final int SCALE = 2;
	
	@Override
	public void create () {
		
		Gdx.input.setInputProcessor(new Input());
		
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
