package com.isaac.handlers;

import com.badlogic.gdx.math.Vector2;
import com.isaac.entity.DynamicEntity;
import com.isaac.entity.StaticEntity;
import com.isaac.res.Vars;

public class Movement {
	/*
	private StaticEntity SE;
	private DynamicEntity DE;
	
	public Movement () {
		SE = new StaticEntity();
		DE = new DynamicEntity();
	}*/
	
	public static void handleInput () {
		System.out.println("yo");
		if (Controls.isDown(Controls.W)) {
			DynamicEntity.player.applyForceToCenter(0, -20, true);
		} else
		if (Controls.isDown(Controls.A)) {
			DynamicEntity.player.applyForceToCenter(-20, 0, true);
		} else
		if (Controls.isDown(Controls.S)) {
			DynamicEntity.player.applyForceToCenter(0, 20, true);
		} else
		if (Controls.isDown(Controls.D)) {
			DynamicEntity.player.applyForceToCenter(20, 0, true);
		}
	}
	
	public static void drag () {
		Vector2 v = new Vector2();
		float d = (Math.abs(v.x) < Vars.SPEED/10 || 
				Math.abs(v.y) < Vars.SPEED/10) ? 5f : 12.5f;
		v.set(d, 0);
		v.setAngle(DynamicEntity.player.getLinearVelocity().angle());
		v.scl(-1);
		DynamicEntity.player.applyForceToCenter(v, true);
	}
	
	public static void limitSpeed () {
		Vector2 v = DynamicEntity.player.getLinearVelocity();
		
		if (Math.abs(v.x) > Vars.SPEED || Math.abs(v.y) > Vars.SPEED) {
			DynamicEntity.player.applyForceToCenter(-v.x, -v.y, true);
			System.out.println("slow down damn it");
		}
	}
	
}
