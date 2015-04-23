package entities;

import logic.Monster;
import logic.Player;



public class Entity {
	
	public Player ePLAYER;
	public Monster eMONSTER;
	
	public DynamicBody body;
	
	public Entity (Player player) {
		ePLAYER = player;
	}
	
	public Entity (Monster monster) {
		eMONSTER = monster;
		body = new DynamicBody();
	}
	
	public void damagePlayer (int damage) {
		ePLAYER.setHealth(ePLAYER.getHealth().getOrderID(), -damage);
		
		if (ePLAYER.getHealth().isDead()) {
			System.out.println(ePLAYER.toString());
		} else {
			System.out.println(ePLAYER.getFullHealth());
		}
	}
	
	public void damageMonster (Monster monster, int damage) {
		
	}
	
}
