package entities;

import com.badlogic.gdx.math.Vector2;



public class Tear {

	public int ID;
	public int mask;
	public Vector2 vec;
	public long born;
	public double damage;
	public boolean hit;
	
	public Tear (int ID, int mask, float damage) {
		this.ID = ID;
		this.mask = mask;
		vec = new Vector2();
		born = System.currentTimeMillis();
		this.damage = damage;
		hit = false;
	}
}