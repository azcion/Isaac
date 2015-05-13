package entities;

import java.util.Map;
import java.util.HashMap;

import resources.Vars;
import logic.Monster;



public class Room {
	
	public float X, Y;
	private StaticBody walls, floor, doors, rocks;
	public Map<Integer, Entity> MONSTERS = new HashMap<Integer, Entity>();
	
	private static int roomMonsterID = 0x00;
	
	
	public Room (float x, float y) {
		X = x;
		Y = y;
		//System.out.printf("%f\t%f\n%f\t%f", X, Y, Vars.x, Vars.y);
		
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
		StaticBody.setCoords(X/2, Y);
		walls.createWalls();
		floor.createGround();
		doors.createDoors(false, false, true, true);
		rocks.createRocks();
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
		// TODO
	}
}
