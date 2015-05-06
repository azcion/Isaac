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
//import static resources.Vars.x;
//import static resources.Vars.y;


public class StaticBody {
	
	private static float X, Y;
	
	public Body body;
	
	private static BodyDef bdef = new BodyDef();;
	private static FixtureDef fdef = new FixtureDef();
	
	public StaticBody () {
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public static void setCoords (float x, float y) {
		StaticBody.X = x;
		StaticBody.Y = y;
	}
	
	public void createWalls (float a, float b) {
		
		PolygonShape rect = new PolygonShape();
		bdef.type = BodyType.StaticBody;
		fdef.shape = rect;
		fdef.filter.categoryBits = Vars.bWALL;
		fdef.filter.maskBits = Vars.bPLAYER | Vars.bENTITY;
		fdef.isSensor = false;
		
		float[] xCoords = {a/9/2/R, (a-a/9/2)/R, a/2/2/R, (a-a/2/2)/R};
		float[] yCoords = {b/2/2/R, (b-b/2/2)/R, b/6/2/R, (b-b/6/2)/R};
		int[] xc = {0, 1, 0, 1, 2, 2, 3, 3}; // index of vars in xCoords
		int[] yc = {0, 0, 1, 1, 2, 3, 2, 3}; // index of vars in yCoords
		
		for (int i = 0; i < 8; ++i) {
			switch (i) {
			case 0:    rect.setAsBox(a/9/2/R, (b/2/2-X*0.73f)/R); break;
			case 4:    rect.setAsBox((a/2/2-X*0.73f)/R, b/6/2/R); break;
			}
			bdef.position.set(xCoords[yc[i]], yCoords[xc[i]]);
			body = MainScreen.world.createBody(bdef);
			body.createFixture(fdef).setUserData("W");
		}
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
				
				bdef.position.set((Vars.x(j)+X/2)/R, (Vars.y(i)+Y/2)/R);
				body = MainScreen.world.createBody(bdef);
				rock.setAsBox((X/2-X/10)/R, (Y/2-Y/10)/R);
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
		door.setAsBox(X*0.73f/R, X*0.73f/R);
		fdef.filter.categoryBits = Vars.bDOOR;
		fdef.isSensor = false;
		
		short bitPE = Vars.bPLAYER | Vars.bENTITY;
		short[] bits = {
				(passableU) ? Vars.bENTITY : bitPE,
				(passableD) ? Vars.bENTITY : bitPE,
				(passableL) ? Vars.bENTITY : bitPE,
				(passableR) ? Vars.bENTITY : bitPE
		};
		float[] xCoords = {x0/2/R, X*1.16f/R, (x0-X*1.16f)/R};
		float[] yCoords = {1, y0/R-1, y0/2/R};
		int[] xc = {0, 0, 1, 2};
		int[] yc = {0, 1, 2, 2};
		String[] UD = {"U", "D", "L", "R"};
		
		for (int i = 0; i < 4; ++i) {
			fdef.filter.maskBits = bits[i];
			bdef.position.set(xCoords[xc[i]], yCoords[yc[i]]);
			body = MainScreen.world.createBody(bdef);
			body.createFixture(fdef).setUserData("D_" + UD[i]);
		}
	}
}
