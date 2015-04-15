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
import static com.isaac.Vars.PM;


public class MainScreen implements Screen {
	
	private MainGame game;
	private OrthographicCamera b2dcam;
	private SpriteBatch batch;
	private Sprite rock;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private Contact cont;
	
	Body walls;
	Body ground;
	Body player;
	
	
	
	int w, h;
	
	public float x (int row) {
		// x coordinate of a row
		return w / 9 + w / 9 * 7 / 13 * row;
	}
	
	public float y (int col) {
		// y coordinate of a column
		return h / 6 + h / 6 * 4 / 7 * col;
	}

	
	public MainScreen (MainGame game) {
		this.game = game;
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		world = new World(new Vector2(0, 9.81f), true);
		world.setContactListener(cont = new Contact());
		b2dr = new Box2DDebugRenderer();
		
		// create walls
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		PolygonShape rect = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rect;
		fdef.filter.categoryBits = Vars.bWALL;
		fdef.filter.maskBits = Vars.bPLAYER;
		fdef.isSensor = false;
		
		bdef.position.set(w/9/2/PM, h/2/PM);
		walls = world.createBody(bdef);
		rect.setAsBox(w/9/2/PM, h/2/PM);
		walls.createFixture(fdef).setUserData("wL");
		
		bdef.position.set(w/PM-w/9/2/PM, h/2/PM);
		walls = world.createBody(bdef);
		walls.createFixture(fdef).setUserData("wR");
		
		bdef.position.set(w/2/PM, h/6/2/PM);
		walls = world.createBody(bdef);
		rect.setAsBox(w/9*7/2/PM, h/6/2/PM);
		walls.createFixture(fdef).setUserData("wU");
		
		bdef.position.set(w/2/PM, h/PM-h/6/2/PM);
		walls = world.createBody(bdef);
		walls.createFixture(fdef).setUserData("wD");
		
		// create ground
		PolygonShape floor = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = floor;
		fdef.filter.categoryBits = Vars.bGROUND;
		fdef.isSensor = true;
		
		bdef.position.set(w/2/PM, h/2/PM);
		ground = world.createBody(bdef);
		floor.setAsBox(w/9*7/2/PM, h/6*4/2/PM);
		ground.createFixture(fdef).setUserData("G");
		
		// create player
		CircleShape head = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = head;
		fdef.filter.categoryBits = Vars.bPLAYER;
		fdef.filter.maskBits = Vars.bWALL | Vars.bROCK;
		fdef.isSensor = false;
		
		bdef.position.set(w/2/PM, h/2/PM);
		player = world.createBody(bdef);
		head.setRadius(w/9*7/13/2/PM);
		player.createFixture(fdef).setUserData("P");
		player.setGravityScale(0);
		
		
		
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(true, w/PM, h/PM);
		
		batch = new SpriteBatch();
		
		
		
		//Group walls = new Group();
		//Group floor = new Group();
		
		//walls.setBounds(0, 0, w, h);
		//floor.setBounds(w/9, h/6, w/9*7, h/6*4);
	}

	@Override
	public void show () {
		
	}
	
	public void handleInput () {
		if (Controls.isDown(Controls.W)) {
			player.applyForceToCenter(0, -20, true);
		} else
		if (Controls.isDown(Controls.A)) {
			player.applyForceToCenter(-20, 0, true);
		} else
		if (Controls.isDown(Controls.S)) {
			player.applyForceToCenter(0, 20, true);
		} else
		if (Controls.isDown(Controls.D)) {
			player.applyForceToCenter(20, 0, true);
		}
	}
	
	public void drag () {
		Vector2 d = new Vector2();
		
		d.set(12f, 0);
		d.setAngle(player.getLinearVelocity().angle());
		d.scl(-1);
		player.applyForceToCenter(d, true);
	}
	
	public void limitSpeed () {
		Vector2 v = player.getLinearVelocity();
		
		if (Math.abs(v.x) > Vars.SPEED || Math.abs(v.y) > Vars.SPEED) {
			player.applyForceToCenter(-v.x, -v.y, true);
			System.out.println("here");
		}
	}
	
	public void update (float delta) {
		
		handleInput();
		
		drag();
		limitSpeed();
		
		world.step(delta, 6, 2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl30.glClearColor(0F, 0F, 0F, 1F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		b2dcam.update();
		Controls.update();
		update(1/60f);
		
		batch.setProjectionMatrix(b2dcam.combined);
		b2dr.render(world, b2dcam.combined);
		
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
