package com.isaac.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.isaac.entity.DynamicEntity;
import com.isaac.entity.StaticEntity;
import com.isaac.handlers.Contact;
import com.isaac.handlers.Controls;
import com.isaac.handlers.Movement;
import com.isaac.res.Vars;

import static com.isaac.res.Vars.PM;
import static com.isaac.res.Vars.h;
import static com.isaac.res.Vars.w;
import static com.isaac.res.Vars.x;
import static com.isaac.res.Vars.y;


public class MainScreen implements Screen {
	
	private MainGame game;
	private OrthographicCamera b2dcam;
	private SpriteBatch batch;
	
	private Box2DDebugRenderer b2dr;
	private Contact cont;
	
	//private StaticEntity SE;
	//private DynamicEntity DE;
	
	//private Movement MOV;


	
	public MainScreen (MainGame game) {
		this.game = game;

		Vars.world = new World(new Vector2(0, -9.81f), true);
		Vars.world.setContactListener(cont = new Contact());
		b2dr = new Box2DDebugRenderer();
		
		StaticEntity.createWalls();
		StaticEntity.createGround();
		StaticEntity.createRocks();
		
		DynamicEntity.createPlayer();
		
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(true, w/PM, h/PM);
		
		batch = new SpriteBatch();
		
	}

	@Override
	public void show () {
		
	}
	
	public void update (float delta) {
		
		Movement.handleInput();
		Movement.drag();
		Movement.limitSpeed();
		
		Vars.world.step(delta, 6, 2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl30.glClearColor(0F, 0F, 0F, 1F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		b2dcam.update();
		Controls.update();
		update(1/60f);
		
		batch.setProjectionMatrix(b2dcam.combined);
		b2dr.render(Vars.world, b2dcam.combined);
		
		batch.begin();/*
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
						switch (Assets.rockMapS[i][j]) {
							case 0: rock = Assets.sRock01[0]; break;
							case 1: rock = Assets.sRock01[1]; break;
							case 2: rock = Assets.sRock01[2]; break;
							case 3: rock = Assets.sRock01[4]; break;
						}
						batch.draw(rock, x(j), y(i), w/9*7/13, h/6*4/7);
					}
				}
			}
			*/
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
