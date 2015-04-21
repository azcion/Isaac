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
import static resources.Vars.x;
import static resources.Vars.y;


public class StaticBody {
	
	public Body body;
	
	private static BodyDef bdef = new BodyDef();;
	private static FixtureDef fdef = new FixtureDef();
	
	public StaticBody () {
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public void createWalls (float x0, float y0) {
		
		PolygonShape rect = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rect;
		fdef.filter.categoryBits = Vars.bWALL;
		fdef.filter.maskBits = Vars.bPLAYER | Vars.bENTITY;
		fdef.isSensor = false;
		
		bdef.position.set(x0/9/2/R, y0/2/R/2);			// left up
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox(x0/9/2/R, (y0/2/2-x*0.73f)/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(x0/9/2/R, (y0-y0/2/2)/R);		// left down
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((x0-x0/9/2)/R, y0/2/R/2);		// right up
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((x0-x0/9/2)/R, (y0-y0/2/2)/R);	// right down
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(x0/2/R/2, y0/6/2/R);			// up left
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox((x0/2/2-x*0.73f)/R, y0/6/2/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((x0-x0/2/2)/R, y0/6/2/R);		// up right
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set(x0/2/R/2, (y0-y0/6/2)/R);		// down left
		body = MainScreen.world.createBody(bdef);
		rect.setAsBox((x0/2/2-x*0.73f)/R, y0/6/2/R);
		body.createFixture(fdef).setUserData("W");
		
		bdef.position.set((x0-x0/2/2)/R, (y0-y0/6/2)/R);	// down right
		body = MainScreen.world.createBody(bdef);
		body.createFixture(fdef).setUserData("W");
	}
	
	public void createGround (float x0, float y0) {
		PolygonShape floor = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = floor;
		fdef.filter.categoryBits = Vars.bGROUND;
		fdef.isSensor = true;
		
		bdef.position.set(x0/2/R, y0/2/R);
		body = MainScreen.world.createBody(bdef);
		floor.setAsBox(x0/9*7/2/R, y0/6*4/2/R);
		body.createFixture(fdef).setUserData("G");
	}
	
	
	public void createRocks (float x0, float y0) {
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
	
	public void createDoors (float x0, float y0,
			boolean passableU, boolean passableD,
			boolean passableL, boolean passableR) {
		PolygonShape door = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = door;
		fdef.filter.categoryBits = Vars.bDOOR;
		fdef.isSensor = false;
		
		fdef.filter.maskBits = (passableU) ? 
				Vars.bENTITY : Vars.bPLAYER | Vars.bENTITY;
		bdef.position.set(x0/2/R, 1);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_U");
		
		fdef.filter.maskBits = (passableD) ? 
				Vars.bENTITY : Vars.bPLAYER | Vars.bENTITY;
		bdef.position.set(x0/2/R, y0/R-1);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_D");
		
		fdef.filter.maskBits = (passableL) ?
				Vars.bENTITY : Vars.bPLAYER | Vars.bENTITY;
		bdef.position.set(x*1.16f/R, y0/2/R);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_L");
		
		fdef.filter.maskBits = (passableR) ? 
				Vars.bENTITY : Vars.bPLAYER | Vars.bENTITY;
		bdef.position.set((x0-x*1.16f)/R, y0/2/R);
		body = MainScreen.world.createBody(bdef);
		door.setAsBox(x*0.73f/R, x*0.73f/R);
		body.createFixture(fdef).setUserData("D_R");
	}
}
