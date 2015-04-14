package com.isaac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainScreen implements Screen {
	
	private MainGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	
	int w, h;

	
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
		//Gdx.gl30.glClearColor(0.5F, 0.5F, 0.5F, 0.5F);
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
			
			//t, x, y, orX, orY, w, h,    scX, scY, rot, sX, sY, sW, sH, fX, fY
			
			for (int i = h/6; i < h/6*4; i += h/6*4/7) {
				for (int j = w/9; j < w/9*7; j += w/9*7/13) {
					if (Math.random() > 0.9) {
						//batch.draw(Assets.sRockBasement0, 
							//	i, j, i+h/6*4/7, j+w/9*7/13);
					}
				}
				System.out.println(i);
			}
			/*batch.draw(Assets.tDoorHole, 
					w/2-64, 0,      w/2, 64,    128, 128, 
					1, 1, 0,    0, 0, 64, 64, true, true);
			batch.draw(Assets.tDoorHole, 
					w/2-64, h-128,  540, 656,   128, 128, 
					1, 1, 180,  0, 0, 64, 64, true, false);
			batch.draw(Assets.tDoorHole, 
					0, h/2-64,       64, 360,   128, 128, 
					1, 1, 90,   0, 0, 64, 64, false, true);
			batch.draw(Assets.tDoorHole, 
					w-64, h/2-64,  1016, 360,   128, 128, 
					1, 1, 270,  0, 0, 64, 64, false, false);*/
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
