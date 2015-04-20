package graphics;

import resources.Assets;
import resources.Vars;
import main.MainScreen;
import entities.EntityManager;

import static resources.Vars.R;



public class UserInterface {
	
	static int[] order;
	
	protected static int health;
	protected static int fullRed;
	protected int halfRed;
	protected int nullRed;
	protected static int fullSoul;
	protected int halfBlue;
	protected static int fullDark;
	protected int halfDark;
	
	protected static int coins;
	protected static int bombs;
	protected static int keys;
	
	public static void update () {
		health = EntityManager.entity.ePLAYER.getHealth().fullHealth();
		order = EntityManager.entity.ePLAYER.getHealth().getOrder();
		
		fullRed  = order[1];
		fullSoul = order[2];
		fullDark = order[3];
		
		coins = EntityManager.entity.ePLAYER.getCoins();
		bombs = EntityManager.entity.ePLAYER.getBombs();
		keys  = EntityManager.entity.ePLAYER.getKeys();
	}
	
	public static void render () {
		float height = Vars.y/2;
		
		//fullRed = 4; fullSoul = 2; fullDark = 1;
		
		int i = 0;
		while (i < fullRed) {
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[0], 
					(Vars.x(0)+35*(i%5))/R, height/R, 
					44/R, 44/R);
			++i;
		}
		while (i < fullSoul + fullRed) {
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[4], 
					(Vars.x(0)+35*(i%5))/R, height/R, 
					44/R, 44/R);
			++i;
		}
		while (i < fullDark + fullSoul + fullRed) {
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[6], 
					(Vars.x(0)+35*(i%5))/R, height/R, 
					44/R, 44/R);
			++i;
		}
		
	}
	
}
