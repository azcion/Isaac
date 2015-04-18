package com.isaac.main;

import graphics.Skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.isaac.entity.DynamicEntity;
import com.isaac.entity.StaticEntity;
import com.isaac.handlers.Contact;
import com.isaac.handlers.Controls;
import com.isaac.handlers.Movement;

import static com.isaac.res.Vars.R;
import static com.isaac.res.Vars.h;
import static com.isaac.res.Vars.w;


public class MainScreen implements Screen {
	
	MainGame game;
	
	public static World world;
	
	public static SpriteBatch batch;
	
	public static OrthographicCamera cam;
	public static Box2DDebugRenderer debugcam;
	
	Contact cont;
	
	
	public MainScreen (MainGame game) {
		this.game = game;
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(cont = new Contact());
		
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		debugcam = new Box2DDebugRenderer(); ///////////////////////
		cam.setToOrtho(true, w/R, h/R);
		cam.update();
		
		StaticEntity.createWalls();
		StaticEntity.createGround();
		StaticEntity.createRocks();
		
		DynamicEntity.createPlayer();
		
	}

	@Override
	public void show () {
		
	}
	
	public void update (float delta) {
		
		Movement.handleInput();
		
		world.step(delta, 6, 2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl30.glClearColor(0F, 0F, 0F, 1F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		System.out.println(Gdx.graphics.getFramesPerSecond());
		
		Controls.update();
		update(1/60f);
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		
		batch.begin();
			
			Skin.drawWalls();
			Skin.drawFloor();
			Skin.drawRocks();
			
			Skin.drawPlayer();
			
		batch.end();
		
		//debugcam.render(world, cam.combined); ///////////////////////
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
