package main;

import entities.EntityManager;
import entities.RoomManager;
import entities.TearManager;
import graphics.Skin;
import graphics.UserInterface;
import handlers.Contact;
import handlers.Controls;
import resources.Vars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;



public class MainScreen implements Screen {
	
	MainGame game;
	
	public static World world;
	public static RoomManager rManager;
	public static EntityManager eManager;
	public static TearManager tManager;
	
	public static SpriteBatch batch;
	
	public static OrthographicCamera cam;
	public static Box2DDebugRenderer debugcam;
	
	
	public MainScreen (MainGame game) {
		this.game = game;
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(new Contact());
		
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		debugcam = new Box2DDebugRenderer(); ///////////////////////
		cam.setToOrtho(true, Vars.w/Vars.R, Vars.h/Vars.R);
		cam.update();
		
		rManager = new RoomManager(1);	////////////////////////	
		eManager = new EntityManager();
		tManager = new TearManager();
		eManager.setupScene();
		eManager.setupEntities();
		
	}

	@Override
	public void show () {
		
	}
	
	public void update (float delta) {
		
		UserInterface.update();
		Controls.update();
		eManager.update();
		tManager.update();
		cam.update();
		
		world.step(delta, 6, 2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);  //IntelliJ whines about this
		
		update(1/55f);
		
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
			
			Skin.drawWalls();
			Skin.drawFloor();
			Skin.drawRocks();
			
			eManager.render();
			tManager.render();
			UserInterface.render();
			
		batch.end();
		
		debugcam.render(world, cam.combined); ///////////////////////
	}

	@Override
	public void hide () {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose () {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void resize (int width, int height) {
		// TODO Auto-generated method stub
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
