package handlers;

import main.MainScreen;
import resources.Vars;

import com.badlogic.gdx.math.Vector2;

import entities.DynamicBody;
import entities.Entity;



public class Movement {
	
	private static double angle;
	private static double sin;
	private static double cos;
	
	public static void handleInput (Entity playerEntity, DynamicBody playerBody) {
		Vector2 p = new Vector2();
		p.x = 0;
		p.y = 0;
		float speed = playerEntity.PLAYER.getSpeed();
		
		playerBody.setLinearVelocity(p);
		
		// moving
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.A)) {
			p.x = -speed * 0.8f;
			p.y = -speed * 0.8f;
		} else
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.D)) {
			p.x =  speed * 0.8f;
			p.y = -speed * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.A)) {
			p.x = -speed * 0.8f;
			p.y =  speed * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.D)) {
			p.x =  speed * 0.8f;
			p.y =  speed * 0.8f;
		} else
		if (Controls.isDown(Controls.W)) {
			p.y = -speed;
		} else
		if (Controls.isDown(Controls.A)) {
			p.x = -speed;
		} else
		if (Controls.isDown(Controls.S)) {
			p.y =  speed;
		} else
		if (Controls.isDown(Controls.D)) {
			p.x =  speed;
		}
		
		// shooting
		if (Controls.isDown(Controls.UP)) {
			MainScreen.tManager.shoot(0);
		} else
		if (Controls.isDown(Controls.LEFT)) {
			MainScreen.tManager.shoot(1);
		} else
		if (Controls.isDown(Controls.DOWN)) {
			MainScreen.tManager.shoot(2);
		} else
		if (Controls.isDown(Controls.RIGHT)) {
			MainScreen.tManager.shoot(3);
		} 
		
		playerBody.setLinearVelocity(p);
	}
	
	public static void linear (Entity ent) {
		Vector2 e = new Vector2();
		float speed = ent.MONSTER.getSpeed();
		
		switch (ent.MONSTER.getDirection()) {
			case 0:
				e.x =  speed;
				e.y =  0;
				break;
			case 1:
				e.x =  speed * 0.8f;
				e.y =  speed * 0.8f;
				break;
			case 2:
				e.x =  0;
				e.y =  speed;
				break;
			case 3:
				e.x = -speed * 0.8f;
				e.y =  speed * 0.8f;
				break;
			case 4:
				e.x = -speed;
				e.y =  0;
				break;
			case 5:
				e.x = -speed * 0.8f;
				e.y = -speed * 0.8f;
				break;
			case 6:
				e.x =  0;
				e.y = -speed;
				break;
			case 7:
				e.x =  speed * 0.8f;
				e.y = -speed * 0.8f;
				break;
		}
		
		ent.body.setLinearVelocity(e);
	}
	
	public static void buzz (Entity ent) {
		Vector2 e = new Vector2();
		float speed = ent.MONSTER.getSpeed();
		
		switch (Vars.seed.nI(4)) {
			case 0:
				e.x =  speed;
				e.y =  speed;
				break;
			case 1:
				e.x =  speed;
				e.y = -speed;
				break;
			case 2:
				e.x = -speed;
				e.y =  speed;
				break;
			case 3:
				e.x = -speed;
				e.y = -speed;
				break;
		}
		
		ent.body.setLinearVelocity(e);
	}
	
	public static void chase (DynamicBody player, Entity monster) {
		Vector2 p = player.getPosition();
		Vector2 e = monster.body.getPosition();
		float speed = monster.MONSTER.getSpeed()/Vars.R;
		
		if (Vars.seed.nI(4) == 0) {
			angle = Math.toDegrees(Math.atan2(p.y - e.y, p.x - e.x));
			cos = Math.cos(angle) * speed;
			sin = Math.sin(angle) * speed;
		} else {
			cos = (p.x > e.x) ? speed : -speed;
			sin = (p.y > e.y) ? speed : -speed;
		}
		
		e.x += cos;
		e.y += sin;
		
		monster.body.setPosition(e);
	}
	
}
