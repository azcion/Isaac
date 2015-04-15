package com.isaac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainScreen implements Screen {
	
	private MainGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite rock;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	
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

		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();
		
		
		// create wall
		BodyDef wall = new BodyDef();
		wall.type = BodyType.StaticBody;
		Body walls = world.createBody(wall);
		FixtureDef fdef = new FixtureDef();
		
		PolygonShape rect = new PolygonShape();
		
		wall.position.set(w/9/2, h/2);
		wall.position.set(w/2, h/2);
		rect.setAsBox(w/9, h/6);
		fdef.shape = rect;
		walls.createFixture(fdef);
		
		
		/*
		BodyDef wR = new BodyDef();
		wR.type = BodyType.StaticBody;
		wR.position.set(w/2, h/2);
		Body bR = world.createBody(wR);
		
		BodyDef wU = new BodyDef();
		wU.type = BodyType.StaticBody;
		wU.position.set(w/2, h/2);
		Body bU = world.createBody(wU);
		
		BodyDef wD = new BodyDef();
		wD.type = BodyType.StaticBody;
		wD.position.set(w/2, h/2);
		Body bD = world.createBody(wD);
		
		/*
		PolygonShape rect = new PolygonShape();
		torso.setAsBox(100, 300);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = torso;
		body.createFixture(fdef);
		
		/*
		CircleShape head = new CircleShape();
		head.setRadius(w/9*7/13);
		 */
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, w, h);
		
		batch = new SpriteBatch();
		
		
		
		//Group walls = new Group();
		//Group floor = new Group();
		
		//walls.setBounds(0, 0, w, h);
		//floor.setBounds(w/9, h/6, w/9*7, h/6*4);
	}

	@Override
	public void show () {
		
	}
	
	public void update (float delta) {
		world.step(delta, 6, 2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl30.glClearColor(0F, 0F, 0F, 1F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		b2dr.render(world, camera.combined);
		
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
