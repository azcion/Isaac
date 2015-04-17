package graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.isaac.entity.DynamicEntity;
import com.isaac.main.MainScreen;
import com.isaac.res.Assets;
import com.isaac.res.Vars;

import static com.isaac.res.Vars.PM;
import static com.isaac.res.Vars.w;
import static com.isaac.res.Vars.h;
import static com.isaac.res.Vars.x;
import static com.isaac.res.Vars.y;


public class Skin {
	
	protected Body body;
	protected Assets assets;
	
	//public static SpriteBatch batch;
	
	protected float a, b;
	protected int width, height;
	
	public Skin (Body body) {
		//batch = new SpriteBatch();
		this.body = body;
		a = this.body.getPosition().x * PM - width / 2;
		b = this.body.getPosition().y * PM - height / 2;
	}
	
	public static void drawWalls () {
		MainScreen.batch.draw(Assets.sBack, 0, 0, w/2/PM, h/2/PM);
		MainScreen.batch.draw(Assets.sBack, w/PM, 0, -w/2/PM, h/2/PM);
		MainScreen.batch.draw(Assets.sBack, 0, h/PM, w/2/PM, -h/2/PM);
		MainScreen.batch.draw(Assets.sBack, w/PM, h/PM, -w/2/PM, -h/2/PM);
	}
	
	public static void drawFloor () {
		MainScreen.batch.draw(
				Assets.sBackFloor1, w/9/PM, h/6/PM, (w/2-w/9)/PM, (h/2-h/6)/PM);
		MainScreen.batch.draw(
				Assets.sBackFloor0, w/9*8/PM, h/6/PM, -(w/2-w/9)/PM, (h/2-h/6)/PM);
		MainScreen.batch.draw(
				Assets.sBackFloor0, w/9/PM, h/6*5/PM, (w/2-w/9)/PM, -(h/2-h/6)/PM);
		MainScreen.batch.draw(
				Assets.sBackFloor1, w/9*8/PM, h/6*5/PM, -(w/2-w/9)/PM, -(h/2-h/6)/PM);
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
							rock, Vars.x(j)/PM, Vars.y(i)/PM, x/PM, y/PM);
				}
			}
		}
	}
	
	public static void drawPlayer () {
		float xCoo = DynamicEntity.player.getPosition().x;
		float yCoo = DynamicEntity.player.getPosition().y;
		
		MainScreen.batch.draw(
				Assets.sPlayer[0], xCoo-x/2/PM, yCoo-y/2/PM, x/PM, y/PM);
	}
	
	
}
