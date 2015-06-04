package entities;

import graphics.Skin;
import logic.Monster;
import main.MainScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Room {
	
	private StaticBody walls, floor, doors, rocks;
	public Map<Integer, Entity> monsters;
	public Map<Integer, Skin> monsterSkins;
	
	public float X, Y;
	public static int roomMonsterID = 0x00;
	private Entity ent;	
	
	public Room (float x, float y) {
		monsters = new HashMap<>();
		monsterSkins = new HashMap<>();
		
		X = x;
		Y = y;
		
		walls = new StaticBody();
		floor = new StaticBody();
		doors = new StaticBody();
		rocks = new StaticBody();
	}
	
	public void update () {
		cleanupBodies();
		for (Map.Entry<Integer, Entity> i : monsters.entrySet()) {
			Entity ent = i.getValue();
			if (ent.MONSTER.ai()) {
				MainScreen.eManager.buzz(ent);
				MainScreen.eManager.chase(ent);
			} else {
				MainScreen.eManager.linear(ent, ent.MONSTER.getDirection());
			}
		}
	}
	
	public void createRoom () {
		StaticBody.setCoords(X/2, Y);
		walls.createWalls();
		floor.createGround();
		doors.createDoors(false, false, false, false);
		rocks.createRocks();
	}
	
	public void addMonster (int monsterID, float x, float y, int i, int j) {
		ent = new Entity(new Monster(monsterID));
		
		switch (monsterID) {
			case 0x00:
				ent.body.createAttackFly(x, y, roomMonsterID);
				break;
			case 0x01:
				ent.body.createRedBoomFly(x, y, roomMonsterID);
				ent.MONSTER.setDirection((j < 7) ? (i < 4) ? 5 : 3 : (i < 4) ? 7 : 1);
				break;
		}
		monsters.put(roomMonsterID, ent);
		++roomMonsterID;
	}
	
	private void cleanupBodies () {
		for (Entry<Integer, Entity> e : monsters.entrySet()) {
			ent = e.getValue();
			if (ent.MONSTER.isDead()) {
				monsters.remove(e.getKey());
				ent.body.destroyBody();
				break;
			}
		}
	}
}
