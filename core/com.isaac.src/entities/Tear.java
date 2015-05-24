package entities;

import resources.Vars;
import com.badlogic.gdx.math.Vector2;



public class Tear {
	
	public int ID;
	public Vector2 vec;
	public long born;
	public double damage;
	public boolean hit;
	
	public Tear (int ID) {
		this.ID = ID;
		vec = new Vector2();
		born = System.currentTimeMillis();
		damage = 3.5; ///////////
		hit = false;
	}
	
	public void setLinearVelocity (int x, int y) {
		vec.x = x;
		vec.y = y;
	}
}

/*
public class Tear {
	
	public int ID;
	public Vector2 position;
	public Vector2 spawnPos;
	public float velocity;
	public double damage;
	public boolean hit;
	
	public Tear (int ID) {
		this.ID = ID;
		position = new Vector2();
		velocity = 5;///Vars.R; /////////////////
		damage = 3.5; ///////////
		hit = false;
	}
	
	public void update () {
		position.x += velocity;
	}
	
	public void setSpawnPosition () {
		spawnPos = position;
	}
}
*/