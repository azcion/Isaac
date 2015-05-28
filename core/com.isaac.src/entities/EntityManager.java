package entities;

import graphics.Skin;
import handlers.Movement;
import logic.Player;
import main.MainScreen;
import resources.Assets;
import resources.Vars;



public class EntityManager {
		
	static Movement movement;
	
	public static Room currentRoom;
	
	public Entity playerEntity;
	public DynamicBody playerBody;
	
	private Skin playerSkin;
	private Skin[][] monsterSkins;
	
	public EntityManager () {
		movement = new Movement();
		currentRoom = MainScreen.rManager.rooms[0]; /////////////////////
		playerEntity = new Entity(new Player());
		playerBody = new DynamicBody();
		monsterSkins = new Skin[7][13];
	}
	
	public void update () {
		Movement.handleInput(playerBody);
		currentRoom.update();
		currentRoom.idle();
		
	}
	
	public void setupScene () { //////////////////////////////////////////////
		currentRoom.createRoom();
		
		for (int i = 0; i < MainScreen.rManager.rooms.length; ++i) {
			MainScreen.rManager.rooms[i].update();
		}
		//currentRoom.update();
	}
	
	public void setupEntities () {
		/*for (int i = 0; i < MainScreen.roomManager.count; ++i) {
			DynamicBody.setCoords(MainScreen.roomManager.rooms[i])
		}*/
		DynamicBody.setCoords(currentRoom.X/2, currentRoom.Y);
		playerBody.createPlayer();
		playerSkin = new Skin(playerBody);
		
		// fly
		int flyID = 0;
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (!Assets.monsterMap[i][j]) {
					continue;
				}
				currentRoom.addMonster(0x00, Vars.y(i)+45, Vars.x(j)+45); ///////////////////
				monsterSkins[i][j] = new Skin(currentRoom.monsters.get(flyID).body);
				++flyID;
			}
		}
	}
	
	public void render () {
		// player
		playerSkin.drawPlayer();
		
		// fly
		for (Skin[] i : monsterSkins) {
			for (Skin j : i) {
				if (j != null) {
					j.drawFly();
				}
			}
		}
	}
	
	public void damage (int id) {
		currentRoom.monsters.get(id).damageMonster(getPlayer().getDamage());
	}
	
	public Player getPlayer () {
		return playerEntity.PLAYER;
	}
}
