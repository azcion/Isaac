package entities;

import logic.Player;
import resources.Assets;
import resources.Vars;
import graphics.Skin;
import handlers.Movement;



public class EntityManager {
	
	static Movement movement;
	
	public Entity PLAYER;
	
	private Room room;
	private DynamicBody player;
	
	private Skin playerSkin;
	private Skin[][] monsterSkins;
	
	public EntityManager () {
		
		PLAYER = new Entity(new Player());
		newRoom(Vars.w, Vars.h);
		movement = new Movement();
		
		player = new DynamicBody();
		monsterSkins = new Skin[7][13];
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
		int flyID = 0;
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (!Assets.monsterMap[i][j]) {
					continue;
				}
				room.addMonster(0x00, Vars.y(i), Vars.x(j));
				monsterSkins[i][j] = new Skin(room.MONSTERS.get(flyID).body);
				++flyID;
				
			}
		}
	}
	
	public void update () {
		Movement.handleInput(player);
		//Movement.chase();
		room.idle();
		
	}
	
	public void render () {
		playerSkin.drawPlayer();
				
		// fly
		for (Skin[] i : monsterSkins) {
			for (Skin j : i) {
				if (j != null) {
					j.draw();
				}
			}
		}
	}
	
}
