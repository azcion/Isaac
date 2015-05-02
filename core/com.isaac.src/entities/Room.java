package entities;

import java.util.Map;
import java.util.HashMap;

import logic.Monster;
import main.MainScreen;



public class Room {
	
	public float X, Y;
	private StaticBody walls, floor, doors, rocks;
	public Map<Integer, Entity> MONSTERS = new HashMap<Integer, Entity>();
	
	private static int roomMonsterID = 0x00;
	
	
	public Room (float x, float y) {
		X = x;
		Y = y;
		
		//MainScreen.cam.setToOrtho(true, X, Y);
		
		walls = new StaticBody();
		floor = new StaticBody();
		doors = new StaticBody();
		rocks = new StaticBody();
	}
	
	public void update () {
		createRoom();
	}
	
	private void createRoom () {
		walls.createWalls(X, Y);
		floor.createGround(X, Y);
		doors.createDoors(X, Y, false, false, false, false);
		rocks.createRocks(X, Y);
	}
	
	public void addMonster (int monsterID, float x, float y) {
		Entity monster = new Entity(new Monster(monsterID));
		monster.body.createFly(x, y);			////////////////////////////
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
