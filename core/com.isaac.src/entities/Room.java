package entities;

import graphics.Skin;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import resources.Vars;
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
		//System.out.printf("%f\t%f\n%f\t%f", X, Y, Vars.x, Vars.y);
		
		//MainScreen.cam.setToOrtho(true, X, Y);
		
		walls = new StaticBody();
		floor = new StaticBody();
		doors = new StaticBody();
		rocks = new StaticBody();
	}
	
	public void update () {
		for (Entry<Integer, Entity> e : monsters.entrySet()) {
			ent = e.getValue();
			if (ent.eMONSTER.isDead()) {
				monsters.remove(e.getKey());
				ent.body.destroyBody();
				break;
			}
		}
	}
	
	public void createRoom () {
		StaticBody.setCoords(X/2, Y);
		walls.createWalls();
		floor.createGround();
		doors.createDoors(false, false, true, true);
		rocks.createRocks();
	}
	
	public void addMonster (int monsterID, float x, float y) {
		Entity monster = new Entity(new Monster(monsterID));
		monster.body.createFly(x, y, roomMonsterID);
		monsters.put(roomMonsterID, monster);
		
		//Skin monsterSkin = new Skin()
		
		++roomMonsterID;
	}
	
	public void idle () {
		for (Map.Entry<Integer, Entity> i : monsters.entrySet()) {
			EntityManager.movement.idle(i.getValue().body);
		}
	}
	
	private void cleanupBodies () {
		// TODO
	}
}
