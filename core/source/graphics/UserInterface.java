package graphics;

import static resources.Vars.R;
import main.MainScreen;
import resources.Assets;
import resources.Vars;
import entities.EntityManager;



public class UserInterface {
	
	private static float X;
	private static float Y;
	
	static int[] order;
	
	protected static int health;
	protected static int fullRed;
	protected static int halfRed;
	protected static int nullRed;
	protected static int fullSoul;
	protected static int halfBlue;
	protected static int fullDark;
	protected static int halfDark;
	
	protected static int coins;
	protected static int bombs;
	protected static int keys;
	
	public static void update () {
		UserInterface.X = EntityManager.currentRoom.X / 2;
		UserInterface.Y = EntityManager.currentRoom.Y;
		
		health = MainScreen.eManager.getPlayer().getHealth().fullHealth();
		order =MainScreen.eManager.getPlayer().getHealth().getOrder();
		
		fullRed  = order[1];
		fullSoul = order[2];
		fullDark = order[3];
		
		coins = MainScreen.eManager.getPlayer().getCoins();
		bombs = MainScreen.eManager.getPlayer().getBombs();
		keys  = MainScreen.eManager.getPlayer().getKeys();
	}
	
	public static void render () {
		float height = Vars.y/2;		
		int i = 0;
		while (i < fullRed) {
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[0], 
					X+(Vars.x(0)+35*(i%5))/R, Y+height/R, 
					44/R, 44/R);
			++i;
		}
		while (i < fullSoul + fullRed) {
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[4], 
					X+(Vars.x(0)+35*(i%5))/R, Y+height/R, 
					44/R, 44/R);
			++i;
		}
		while (i < fullDark + fullSoul + fullRed) {;
			if (i == 5) height += Vars.y*0.5;
			MainScreen.batch.draw(
					Assets.sUI_Hearts[6], 
					X+(Vars.x(0)+35*(i%5))/R, Y+height/R, 
					44/R, 44/R);
			++i;
		}
		
	}
	
}
