package entities;

import resources.Assets;
import resources.Vars;
import main.MainScreen;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static resources.Vars.R;
import static resources.Vars.h;
import static resources.Vars.w;
import static resources.Vars.x;
import static resources.Vars.y;


public class StaticEntity {
	
	public Body body;
	
	private static BodyDef bdef = new BodyDef();;
	private static FixtureDef fdef = new FixtureDef();
	
	public StaticEntity () {
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public void createWalls () {
		
		PolygonShape rect = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rect;
		fdef.filter.categoryBits = Vars.bWALL;
		fdef.filter.maskBits = Vars.bPLAYER | Vars.bENTITY;
		fdef.isSensor = false;
		
		bdef.position.set(w/9/2/R, h/2/R/2);			// left up
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox(w/9/2/R, (h/2/2-x*0.73f)/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(w/9/2/R, (h-h/2/2)/R);		// left down
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((w-w/9/2)/R, h/2/R/2);		// right up
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((w-w/9/2)/R, (h-h/2/2)/R);	// right down
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(w/2/R/2, h/6/2/R);			// up left
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox((w/2/2-x*0.73f)/R, h/6/2/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((w-w/2/2)/R, h/6/2/R);		// up right
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(w/2/R/2, (h-h/6/2)/R);		// down left
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox((w/2/2-x*0.73f)/R, h/6/2/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((w-w/2/2)/R, (h-h/6/2)/R);	// down right
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
	}
	
	public void createGround () {
		PolygonShape floor = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = floor;
		fdef.filter.categoryBits = Vars.bGROUND;
		fdef.isSensor = true;
		
		bdef.position.set(w/2/R, h/2/R);
		body = MainScreen.world.createBody(bdef);
		floor.setAsBox(w/9*7/2/R, h/6*4/2/R);
		body.createFixture(fdef).setUserData("G");
	}
	
	
	public void createRocks () {
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
				
				bdef.position.set((Vars.x(j)+x/2)/R, (Vars.y(i)+y/2)/R);
				body = MainScreen.world.createBody(bdef);
				rock.setAsBox((x/2-x/10)/R, (y/2-y/10)/R);
				body.createFixture(fdef).setUserData(
						String.format("R_%d_%d", i, j)); /////
			}
		}
	}
	
	public void createDoors () {
		PolygonShape door = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = door;
		fdef.filter.categoryBits = Vars.bDOOR;
		fdef.filter.maskBits = Vars.bPLAYER | Vars.bENTITY;
		fdef.isSensor = false;
		
		bdef.position.set(w/2/R, 1);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_U");
		
		bdef.position.set(w/2/R, h/R-1);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_D");
		
		bdef.position.set(x*1.16f/R, h/2/R);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_L");
		
		bdef.position.set((w-x*1.16f)/R, h/2/R);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_R");
	}
}
