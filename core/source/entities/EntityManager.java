package entities;

import graphics.Skin;
import handlers.Movement;
import logic.Monster;
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
		
		int monsterID = 0;
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (!Assets.monsterMap[i][j]) {
					continue;
				}
				int monsterType = Assets.monsterMapS[i][j]-1;
				currentRoom.addMonster(monsterType, Vars.y(i)+45, Vars.x(j)+45);
				monsterSkins[i][j] = new Skin(currentRoom.monsters.get(monsterID).body, monsterType);
				++monsterID;
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
					j.drawMonster();
				}
			}
		}
	}
	
	public void damage (int id) {
		currentRoom.monsters.get(id).damageMonster(getPlayer().getDamage());
	}
	
	public void linear (Entity monster, int direction) {
		Movement.linear(monster);
	}
	
	public void buzz (Entity monster) {
		Movement.buzz(monster.body);
	}
	
	public void chase (Entity monster) {
		if (playerBody.body.getPosition().dst2(monster.body.getPosition()) > 20) {
			return;
		}
		Movement.chase(playerBody, monster.body);
	}
	
	public Player getPlayer () {
		return playerEntity.PLAYER;
	}
	
	public Monster getMonster (int id) {
		return currentRoom.monsters.get(id).MONSTER;
	}
	
	public Monster getMonster (String id) {
		return getMonster(Integer.parseInt(id));
	}
}
