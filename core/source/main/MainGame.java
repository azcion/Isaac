package main;

import handlers.Input;

import java.io.IOException;

import resources.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class MainGame extends Game {
	
	public MainScreen Screen;
	public static final int Vw = 80;
	public static final int Vh = 45;
	public static final int SCALE = 16;
	
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
