package entities;

import graphics.Skin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import logic.Monster;



public class Room {
	
	private StaticBody walls, floor, doors, rocks;
	public Map<Integer, Entity> monsters;
	public Map<Integer, Skin> monsterSkins;
	
	public float X, Y;
	public static int roomMonsterID = 0x00;
	private Entity ent;
	
	
	public Room (float x, float y) {
		monsters = new HashMap<Integer, Entity>();
		monsterSkins = new HashMap<Integer, Skin>();
		
		X = x;
		Y = y;
		
		walls = new StaticBody();
		floor = new StaticBody();
		doors = new StaticBody();
		rocks = new StaticBody();
	}
	
	public void update () {
		cleanupBodies();
	}
	
	public void createRoom () {
		StaticBody.setCoords(X/2, Y);
		walls.createWalls();
		floor.createGround();
		doors.createDoors(false, false, false, false);
		rocks.createRocks();
	}
	
	public void addMonster (int monsterID, float x, float y) {
		Entity monster = new Entity(new Monster(monsterID));
		monster.body.createFly(x, y, roomMonsterID);
		monsters.put(roomMonsterID, monster);
		++roomMonsterID;
	}
	
	public void idle () {
		for (Map.Entry<Integer, Entity> i : monsters.entrySet()) {
			EntityManager.movement.idle(i.getValue().body);
		}
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
