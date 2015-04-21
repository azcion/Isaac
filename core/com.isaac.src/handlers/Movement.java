package handlers;

import resources.Vars;

import com.badlogic.gdx.math.Vector2;
import entities.DynamicBody;



public class Movement {
	
	public Movement () {
		
	}
	
	public static void handleInput (DynamicBody user) {
		Vector2 v = user.getLinearVelocity();
		
		v.x = 0;
		v.y = 0;
		
		user.setLinearVelocity(v);
		
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.A)) {
			v.x = -Vars.SPEED * 0.8f;
			v.y = -Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.W) && Controls.isDown(Controls.D)) {
			v.x = Vars.SPEED * 0.8f;
			v.y = -Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.A)) {
			v.x = -Vars.SPEED * 0.8f;
			v.y = Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.S) && Controls.isDown(Controls.D)) {
			v.x = Vars.SPEED * 0.8f;
			v.y = Vars.SPEED * 0.8f;
		} else
		if (Controls.isDown(Controls.W)) {
			v.y = -Vars.SPEED;
		} else
		if (Controls.isDown(Controls.A)) {
			v.x = -Vars.SPEED;
		} else
		if (Controls.isDown(Controls.S)) {
			v.y = Vars.SPEED;
		} else
		if (Controls.isDown(Controls.D)) {
			v.x = Vars.SPEED;
		}
		
		user.setLinearVelocity(v);
	}	
	
	public void idle (DynamicBody ent) {
		Vector2 v = ent.getLinearVelocity();
		
		switch (Vars.seed.nI(4)) {
			case 0:
				v.x = Vars.AFS;
				v.y = Vars.AFS;
				break;
			case 1:
				v.x = Vars.AFS;
				v.y = -Vars.AFS;
				break;
			case 2:
				v.x = -Vars.AFS;
				v.y = Vars.AFS;
				break;
			case 3:
				v.x = -Vars.AFS;
				v.y = -Vars.AFS;
				break;
		}
		
		ent.setLinearVelocity(v);
	}
	
	public void chase (DynamicBody user, DynamicBody ent) {
		Vector2 p = user.getPosition();
		Vector2 e = ent.getPosition();
		//Vector2 e = DynamicEntity.attackFly.getLinearVelocity();
		
		ent.setLinearVelocity(e);
		
		e.x += (p.x > e.x) ? Vars.AFS : -Vars.AFS;
		e.y += (p.y > e.y) ? Vars.AFS : -Vars.AFS;
		
		ent.setLinearVelocity(e);
	}
	
}
