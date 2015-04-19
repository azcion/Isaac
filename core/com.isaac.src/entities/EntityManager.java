package entities;

import resources.Vars;
import graphics.Skin;
import handlers.Movement;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;



public class EntityManager {
	
	public Engine engine;
	
	private Movement movement;
	
	private StaticEntity[] scene;	// walls, ground, rocks, doors
	private DynamicEntity player;
	private DynamicEntity[][] monsters;
	
	private Skin playerSkin;
	private Skin[][] monsterSkins;
	
	public EntityManager () {
		engine = new Engine();
		movement = new Movement();
		scene = new StaticEntity[4];
		player = new DynamicEntity();
		monsters = new DynamicEntity[5][10];	///////////////////
		monsterSkins = new Skin[5][10];			///////////////////
	}
	
	public void setupScene () {
		// walls
		scene[0] = new StaticEntity();
		scene[0].createWalls();
		
		// ground
		scene[1] = new StaticEntity();
		scene[1].createGround();
		
		// rocks
		scene[2] = new StaticEntity();
		scene[2].createRocks();
		
		// doors
		scene[3] = new StaticEntity();
		scene[3].createDoors();
	}
	
	public void setupEntities () {
		player.createPlayer();
		playerSkin = new Skin(player);
		
		// fly
		for (int j = 0; j < 10; ++j) {
			monsters[0][j] = new DynamicEntity();
			monsters[0][j].createFly(Vars.x(3), Vars.y(j+3));
			monsterSkins[0][j] = new Skin(monsters[0][j]);
		}
	}
	
	public void update () {
		Movement.handleInput(player);
		//Movement.chase();
		for (DynamicEntity i : monsters[0]) {
			movement.idle(i);
		}
	}
	
	public void render () {
		playerSkin.drawPlayer();
		
		// fly
		for (Skin i : monsterSkins[0]) {
			i.drawFly();
		}
	}
}
