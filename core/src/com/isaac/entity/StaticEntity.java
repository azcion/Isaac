package com.isaac.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.isaac.res.Assets;
import com.isaac.res.Vars;

import static com.isaac.res.Vars.PM;
import static com.isaac.res.Vars.h;
import static com.isaac.res.Vars.w;
import static com.isaac.res.Vars.x;
import static com.isaac.res.Vars.y;


public class StaticEntity {
	
	public static Body walls;
	public static Body ground;
	public static Body rocks;
	
	private static BodyDef bdef = new BodyDef();;
	private static FixtureDef fdef = new FixtureDef();
	
	public StaticEntity () {
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public static void createWalls () {
		
		PolygonShape rect = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rect;
		fdef.filter.categoryBits = Vars.bWALL;
		fdef.filter.maskBits = Vars.bPLAYER;
		fdef.isSensor = false;
		
		bdef.position.set(w/9/2/PM, h/2/PM);
		walls = Vars.world.createBody(bdef);
		rect.setAsBox(w/9/2/PM, h/2/PM);
		walls.createFixture(fdef).setUserData("wL");
		
		bdef.position.set(w/PM-w/9/2/PM, h/2/PM);
		walls = Vars.world.createBody(bdef);
		walls.createFixture(fdef).setUserData("wR");
		
		bdef.position.set(w/2/PM, h/6/2/PM);
		walls = Vars.world.createBody(bdef);
		rect.setAsBox(w/9*7/2/PM, h/6/2/PM);
		walls.createFixture(fdef).setUserData("wU");
		
		bdef.position.set(w/2/PM, h/PM-h/6/2/PM);
		walls = Vars.world.createBody(bdef);
		walls.createFixture(fdef).setUserData("wD");
	}
	
	public static void createGround () {
		PolygonShape floor = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = floor;
		fdef.filter.categoryBits = Vars.bGROUND;
		fdef.isSensor = true;
		
		bdef.position.set(w/2/PM, h/2/PM);
		ground = Vars.world.createBody(bdef);
		floor.setAsBox(w/9*7/2/PM, h/6*4/2/PM);
		ground.createFixture(fdef).setUserData("G");
	}
	
	
	public static void createRocks () {
		PolygonShape rock = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rock;
		fdef.filter.categoryBits = Vars.bROCK;
		fdef.filter.maskBits = Vars.bPLAYER;
		fdef.isSensor = false;
		
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				
				if (!Assets.rockMap[i][j]) {
					continue;
				}
				
				bdef.position.set((Vars.x(j)+x/2)/PM, (Vars.y(i)+y/2)/PM);
				rocks = Vars.world.createBody(bdef);
				rock.setAsBox((x/2-x/10)/PM, (y/2-y/10)/PM);
				rocks.createFixture(fdef).setUserData(
						String.format("R_%d_%d", i, j)); /////
			}
		}
	}
}
