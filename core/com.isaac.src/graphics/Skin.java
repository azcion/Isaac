package graphics;

import resources.Assets;
import resources.Vars;
import main.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

import entities.DynamicEntity;
import static resources.Vars.R;
import static resources.Vars.h;
import static resources.Vars.w;
import static resources.Vars.x;
import static resources.Vars.y;


public class Skin {
	
	protected DynamicEntity body;
	protected Assets assets;
	
	protected float a, b;
	protected int width, height;
	
	private int pos = 0; ///////////////////////////////////////////////
	
	public Skin (DynamicEntity ent) {
		this.body = ent;
		a = this.body.getPosition().x * R - width / 2;
		b = this.body.getPosition().y * R - height / 2;
	}
	
	public static void drawWalls () {
		MainScreen.batch.draw(Assets.sBack, 0, 0, w/2/R, h/2/R);
		MainScreen.batch.draw(Assets.sBack, w/R, 0, -w/2/R, h/2/R);
		MainScreen.batch.draw(Assets.sBack, 0, h/R, w/2/R, -h/2/R);
		MainScreen.batch.draw(Assets.sBack, w/R, h/R, -w/2/R, -h/2/R);
	}
	
	public static void drawFloor () {
		MainScreen.batch.draw(
				Assets.sBackFloor1, w/9/R, h/6/R, (w/2-w/9)/R, (h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor0, w/9*8/R, h/6/R, -(w/2-w/9)/R, (h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor0, w/9/R, h/6*5/R, (w/2-w/9)/R, -(h/2-h/6)/R);
		MainScreen.batch.draw(
				Assets.sBackFloor1, w/9*8/R, h/6*5/R, -(w/2-w/9)/R, -(h/2-h/6)/R);
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
							rock, Vars.x(j)/R, Vars.y(i)/R, x/R, y/R);
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
	
	public void drawFly () {
		float xc = body.getPosition().x;
		float yc = body.getPosition().y;
		
		MainScreen.batch.draw(
				Assets.sFly[pos], xc-x/2/R, yc-y/2/R, x/R, y/R);
		
		pos = (Gdx.graphics.getDeltaTime() % 0.25 < 1/15f) ? (pos == 3) ? 0 : pos+1 : pos;
	}
	
}
