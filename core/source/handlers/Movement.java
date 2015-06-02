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
	private static Vector2 p;
	private static Vector2 e;
	
	public static void handleInput (DynamicBody player) {
		p = player.getLinearVelocity();
		
		p.x = 0;
		p.y = 0;
		
		player.setLinearVelocity(p);
		
		// moving
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.A)) {
			p.x = -Vars.SPEED * 0.8f;
			p.y = -Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.D)) {
			p.x =  Vars.SPEED * 0.8f;
			p.y = -Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.A)) {
			p.x = -Vars.SPEED * 0.8f;
			p.y =  Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.D)) {
			p.x =  Vars.SPEED * 0.8f;
			p.y =  Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.W)) {
			p.y = -Vars.SPEED;
		} else
		if (Controls.isDown(Controls.A)) {
			p.x = -Vars.SPEED;
		} else
		if (Controls.isDown(Controls.S)) {
			p.y =  Vars.SPEED;
		} else
		if (Controls.isDown(Controls.D)) {
			p.x =  Vars.SPEED;
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
		
		player.setLinearVelocity(p);
	}
	
	public static void linear (Entity ent) {
		e = ent.body.getLinearVelocity();
		
		switch (ent.MONSTER.getDirection()) {
			case 0:
				e.x =  ent.MONSTER.getSpeed();
				e.y =  0;
				break;
			case 1:
				e.x =  ent.MONSTER.getSpeed() * 0.8f;
				e.y =  ent.MONSTER.getSpeed() * 0.8f;
				break;
			case 2:
				e.x =  0;
				e.y =  ent.MONSTER.getSpeed();
				break;
			case 3:
				e.x = -ent.MONSTER.getSpeed() * 0.8f;
				e.y =  ent.MONSTER.getSpeed() * 0.8f;
				break;
			case 4:
				e.x = -ent.MONSTER.getSpeed();
				e.y =  0;
				break;
			case 5:
				e.x = -ent.MONSTER.getSpeed() * 0.8f;
				e.y = -ent.MONSTER.getSpeed() * 0.8f;
				break;
			case 6:
				e.x =  0;
				e.y = -ent.MONSTER.getSpeed();
				break;
			case 7:
				e.x =  ent.MONSTER.getSpeed() * 0.8f;
				e.y = -ent.MONSTER.getSpeed() * 0.8f;
				break;
		}
		
		ent.body.setLinearVelocity(e);
	}
	
	public static void buzz (DynamicBody monster) {
		e = monster.getLinearVelocity();
		
		switch (Vars.seed.nI(4)) {
			case 0:
				e.x =  Vars.AFS;
				e.y =  Vars.AFS;
				break;
			case 1:
				e.x =  Vars.AFS;
				e.y = -Vars.AFS;
				break;
			case 2:
				e.x = -Vars.AFS;
				e.y =  Vars.AFS;
				break;
			case 3:
				e.x = -Vars.AFS;
				e.y = -Vars.AFS;
				break;
		}
		
		monster.setLinearVelocity(e);
	}
	
	public static void chase (DynamicBody player, DynamicBody monster) {
		p = player.getPosition();
		e = monster.getPosition();
		
		if (Vars.seed.nI(4) == 0) {
			angle = Math.toDegrees(Math.atan2(p.y - e.y, p.x - e.x));
			cos = Math.cos(angle) * 0.02;
			sin = Math.sin(angle) * 0.02;
		} else {
			cos = (p.x > e.x) ? 0.02 : -0.02;
			sin = (p.y > e.y) ? 0.02 : -0.02;
		}
		
		e.x += cos;
		e.y += sin;
		
		monster.setPosition(e);
	}
	
}
