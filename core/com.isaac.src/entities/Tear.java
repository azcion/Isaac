package entities;

import resources.Vars;

import com.badlogic.gdx.math.Vector2;



public class Tear {
	
	public Vector2 position;
	public float velocity;
	
	public Tear () {
		position = new Vector2();
		velocity = 5/Vars.R; /////////////////
	}
	
	public void update () {
		position.x += velocity;
	}
}
