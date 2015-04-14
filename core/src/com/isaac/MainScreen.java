package com.isaac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainScreen implements Screen {
	
	private MainGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private Sprite rock;
	
	int w, h;
	
	public float x (int row) {
		return w / 9 + w / 9 * 7 / 13 * row;
	}
	
	public float y (int col) {
		return h / 6 + h / 6 * 4 / 7 * col;
	}

	
	public MainScreen (MainGame game) {
		this.game = game;
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, w, h);
		
		batch = new SpriteBatch();
		
		stage = new Stage();
		
		Group walls = new Group();
		Group floor = new Group();
		
		walls.setBounds(0, 0, w, h);
		floor.setBounds(64, 64, w-64, h-64);
	}

	@Override
	public void show () {
		
	}

	@Override
	public void render (float delta) {
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			batch.draw(Assets.sBack, 0, 0, w/2, h/2);
			batch.draw(Assets.sBack, w, 0, -w/2, h/2);
			batch.draw(Assets.sBack, 0, h, w/2, -h/2);
			batch.draw(Assets.sBack, w, h, -w/2, -h/2);
			batch.draw(Assets.sBackFloor1, w/9, h/6, w/2-w/9, h/2-h/6);
			batch.draw(Assets.sBackFloor0, w/9*8, h/6, -(w/2-w/9), h/2-h/6);
			batch.draw(Assets.sBackFloor0, w/9, h/6*5, w/2-w/9, -(h/2-h/6));
			batch.draw(Assets.sBackFloor1, w/9*8, h/6*5, -(w/2-w/9), -(h/2-h/6));
			
			for (int i = 0; i < 7; ++i) {
				for (int j = 0; j < 13; ++j) {
					if (Assets.rockMap[i][j]) {
						rock = (Assets.rockMapR[i][j] == 0) ? 
								Assets.sRockBasement[0] :
								(Assets.rockMapR[i][j] == 1) ? 
								Assets.sRockBasement[1] :
								(Assets.rockMapR[i][j] == 2) ?
								Assets.sRockBasement[2] :
								(Assets.rockMapR[i][j] == 3) ?
								Assets.sRockBasement[3] :
								Assets.sRockBasement[4];
						batch.draw(rock, x(j), y(i), w/9*7/13, h/6*4/7);
					}
				}
			}
			
		batch.end();
	}

	@Override
	public void hide () {
		
	}

	@Override
	public void dispose () {
		
	}
	
	@Override
	public void resize (int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
