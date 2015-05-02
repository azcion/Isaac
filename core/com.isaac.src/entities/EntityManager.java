package entities;

import logic.Player;
import main.MainScreen;
import resources.Assets;
import resources.Vars;
import graphics.Skin;
import handlers.Movement;



public class EntityManager {
	
	static Movement movement;
	
	public Entity PLAYER;
	private Room currentRoom;
	private DynamicBody player;
	
	private Skin playerSkin;
	private Skin[][] monsterSkins;
	
	public EntityManager () {
		
		PLAYER = new Entity(new Player());
		movement = new Movement();
		
		currentRoom = MainScreen.roomManager.rooms[0];
		player = new DynamicBody();
		monsterSkins = new Skin[7][13];
	}
	
	public void setupScene () {
		currentRoom.update();
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
				currentRoom.addMonster(0x00, Vars.y(i)+45, Vars.x(j)+45); ///////////////////
				monsterSkins[i][j] = new Skin(currentRoom.MONSTERS.get(flyID).body);
				++flyID;
			}
		}
	}
	
	public void update () {
		Movement.handleInput(player);
		//Movement.chase();
		currentRoom.idle();
		
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
