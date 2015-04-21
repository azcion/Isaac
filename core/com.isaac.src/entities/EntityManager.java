package entities;

import logic.Player;
import resources.Vars;
import graphics.Skin;
import handlers.Movement;




public class EntityManager {
	
	private Movement movement;
	
	public static Entity entity;
	
	private Room room;
	private DynamicBody player;
	private DynamicBody[][] monsters;
	
	private Skin playerSkin;
	private Skin[][] monsterSkins;
	
	public EntityManager () {
		
		entity = new Entity(new Player());
		newRoom(Vars.w, Vars.h);
		//System.out.println(entity.ePLAYER.toString());	//////////////////
		movement = new Movement();
		
		player = new DynamicBody();
		monsters = new DynamicBody[5][10];	///////////////////
		monsterSkins = new Skin[5][10];			///////////////////
	}
	
	public void newRoom (float x0, float y0) {
		room = new Room(x0, y0);
	}
	
	public void setupScene () {
		room.update();
	}
	
	public void setupEntities () {
		player.createPlayer();
		playerSkin = new Skin(player);
		
		// fly
		for (int j = 0; j < 10; ++j) {
			monsters[0][j] = new DynamicBody();
			monsters[0][j].createFly(Vars.x(3), Vars.y(j+3));
			monsterSkins[0][j] = new Skin(monsters[0][j]);
		}
	}
	
	public void update () {
		Movement.handleInput(player);
		//Movement.chase();
		for (DynamicBody i : monsters[0]) {
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
