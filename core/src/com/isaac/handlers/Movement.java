package com.isaac.handlers;

import com.badlogic.gdx.math.Vector2;
import com.isaac.entity.DynamicEntity;
import com.isaac.entity.StaticEntity;
import com.isaac.res.Vars;

public class Movement {
	
	public static void handleInput () {
		Vector2 v = DynamicEntity.player.getLinearVelocity();
		
		v.x = 0;
		v.y = 0;
		
		DynamicEntity.player.setLinearVelocity(v);
		
		
		v = DynamicEntity.player.getLinearVelocity();
		
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
		
		DynamicEntity.player.setLinearVelocity(v);
	}
	
}
