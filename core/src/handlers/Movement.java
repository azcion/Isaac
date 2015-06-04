package handlers;

import main.MainScreen;
import resources.Vars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import entities.DynamicBody;
import entities.Entity;



public class Movement {
	
	public static void handleInput (Entity playerEntity, DynamicBody playerBody) {
		Vector2 p = new Vector2();
		p.x = 0;
		p.y = 0;
		float speed = playerEntity.PLAYER.getSpeed();
		
		playerBody.setLinearVelocity(p);

		// exit
		if (Controls.isDown(Controls.ESC)) {
			Gdx.app.exit();
		}
		
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
			MainScreen.tManager.shoot(0, 0, 0);
		} else
		if (Controls.isDown(Controls.LEFT)) {
			MainScreen.tManager.shoot(1, 0, 0);
		} else
		if (Controls.isDown(Controls.DOWN)) {
			MainScreen.tManager.shoot(2, 0, 0);
		} else
		if (Controls.isDown(Controls.RIGHT)) {
			MainScreen.tManager.shoot(3, 0, 0);
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
		
		e.x += (p.x > e.x) ? speed : -speed;
		e.y += (p.y > e.y) ? speed : -speed;
		
		monster.body.setPosition(e);
	}
	
}
