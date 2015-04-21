package entities;

import java.util.Map;
import java.util.HashMap;



public class Room {
	
	private float x0, y0;
	private StaticBody walls, floor, doors, rocks;
	private Map<Entity, String> MONSTERS = new HashMap<Entity, String>();
	
	
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
	
	
	
}
