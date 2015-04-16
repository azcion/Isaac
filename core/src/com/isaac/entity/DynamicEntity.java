package com.isaac.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.isaac.res.Vars;

import static com.isaac.res.Vars.PM;
import static com.isaac.res.Vars.h;
import static com.isaac.res.Vars.w;
import static com.isaac.res.Vars.x;
import static com.isaac.res.Vars.y;


public class DynamicEntity {
	
	public static Body player;
	
	private static BodyDef bdef = new BodyDef();
	private static FixtureDef fdef = new FixtureDef();
	
	public DynamicEntity () {
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public static void createPlayer () {
		CircleShape head = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = head;
		fdef.filter.categoryBits = Vars.bPLAYER;
		fdef.filter.maskBits = Vars.bWALL | Vars.bROCK;
		fdef.isSensor = false;
		
		bdef.position.set(w/2/PM, h/2/PM);
		player = Vars.world.createBody(bdef);
		head.setRadius(x/10*3/PM);
		player.createFixture(fdef).setUserData("P");
		player.setGravityScale(0);
	}
}
