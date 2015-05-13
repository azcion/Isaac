package graphics;

import resources.Assets;
import resources.Vars;
import main.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import entities.DynamicBody;
import entities.EntityManager;
import static resources.Vars.R;
import static resources.Vars.h;
import static resources.Vars.w;
import static resources.Vars.x;
import static resources.Vars.y;



public class Skin {
	
	protected DynamicBody body;
	protected Assets assets;
	
	private static float X;
	private static float Y;
	
	private int pos = 0; ////////////////temporary animation var//////////////
	
	public Skin (DynamicBody ent) {
		this.body = ent;
		X = EntityManager.currentRoom.X/2;
		Y = EntityManager.currentRoom.Y;
	}
	
	public static void drawWalls () {
		MainScreen.batch.draw(Assets.sBack, X+0, Y+0, w/2/R, h/2/R);
		MainScreen.batch.draw(Assets.sBack, X+w/R, Y+0, -w/2/R, h/2/R);
		MainScreen.batch.draw(Assets.sBack, X+0, Y+h/R, w/2/R, -h/2/R);
		MainScreen.batch.draw(Assets.sBack, X+w/R, Y+h/R, -w/2/R, -h/2/R);
	}
	
	public static void drawFloor () {
		MainScreen.batch.draw(
				Assets.sBackFloor1, X+w/9/R, Y+h/6/R, (w/2-w/9)/R, (h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor0, X+w/9*8/R, Y+h/6/R, -(w/2-w/9)/R, (h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor0, X+w/9/R, Y+h/6*5/R, (w/2-w/9)/R, -(h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor1, X+w/9*8/R, Y+h/6*5/R, -(w/2-w/9)/R, -(h/2-h/6)/R);
	}
	
	public static void drawRocks () {
		Sprite rock = null;
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (Assets.rockMap[i][j]) {
					switch (Assets.rockMapS[i][j]) {
						case 0: rock = Assets.sRock01[0]; break;
						case 1: rock = Assets.sRock01[1]; break;
						case 2: rock = Assets.sRock01[2]; break;
						case 3: rock = Assets.sRock01[4]; break;
					}
					MainScreen.batch.draw(
							rock, X+Vars.x(j)/R, Y+Vars.y(i)/R, x/R, y/R);
				}
			}
		}
	}
	
	public void drawPlayer () {
		float xc = body.getPosition().x;
		float yc = body.getPosition().y;
		
		MainScreen.batch.draw(
				Assets.sPlayer[0], xc-x/2/R, yc-y/2/R, x/R, y/R);
	}
	
	public void draw () {		
		float xc = body.getPosition().x;
		float yc = body.getPosition().y;
		MainScreen.batch.draw(
				Assets.sFly[pos], xc-x/2/R, yc-y/2/R, x/R, y/R);
		
		// temporary animation thingy
		pos = (Gdx.graphics.getDeltaTime() % 0.25 < 1/15f) ? (pos == 3) ? 0 : pos+1 : pos;
	}
	
}
