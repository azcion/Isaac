package entities;

import logic.Monster;
import logic.Player;



public class Entity {
	
	public Player PLAYER;
	public Monster MONSTER;
	
	public DynamicBody body;
	
	public Entity (Player player) {
		PLAYER = player;
	}
	
	public Entity (Monster monster) {
		MONSTER = monster;
		body = new DynamicBody();
	}
	
	public void damagePlayer (int damage) {
		PLAYER.setHealth(PLAYER.getHealth().getOrderID(), -damage);
		/*
		if (ePLAYER.getHealth().isDead()) {
			System.out.println(ePLAYER.toString());
		} else {
			System.out.println(ePLAYER.getFullHealth());
		}*/
	}
	
	public void damageMonster (double damage) {
		MONSTER.damage(damage);
	}
}
