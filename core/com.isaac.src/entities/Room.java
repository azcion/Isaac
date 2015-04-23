package entities;

import java.util.Map;
import java.util.HashMap;

import logic.Monster;



public class Room {
	
	private float x0, y0;
	private StaticBody walls, floor, doors, rocks;
	public Map<Integer, Entity> MONSTERS = new HashMap<Integer, Entity>();
	
	private static int roomMonsterID = 0x00;
	
	
	public Room (float x0, float y0) {
		this.x0 = x0;
		this.y0 = y0;
		
		walls = new StaticBody();
		floor = new StaticBody();
		doors = new StaticBody();
		rocks = new StaticBody();
	}
	
	public void update () {
		createRoom();
	}
	
	private void createRoom () {
		walls.createWalls(x0, y0);
		floor.createGround(x0, y0);
		doors.createDoors(x0, y0, false, true, true, true);
		rocks.createRocks(x0, y0);
	}
	
	public void addMonster (int monsterID, float x, float y) {
		Entity monster = new Entity(new Monster(monsterID));
		monster.body.createFly(x, y);
		MONSTERS.put(roomMonsterID, monster);
		++roomMonsterID;
	}
	
	public void idle () {
		for (Map.Entry<Integer, Entity> i : MONSTERS.entrySet()) {
			EntityManager.movement.idle(i.getValue().body);
		}
	}
	
	private void cleanupBodies () {
		
	}
}
