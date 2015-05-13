package entities;

import java.util.Iterator;
import main.MainScreen;
import com.badlogic.gdx.utils.Array;
import resources.Vars;



public class TearManager {
	
	Array<Tear> tears;
	double power;
	
	public TearManager () {
		tears = new Array<Tear>();
		power = 3.5;
	}
	
	public void update () {
		for (Iterator<Tear> t = tears.iterator(); t.hasNext(); ) {
			Tear tear = t.next();
			tear.update();
			
			if (tear.position.x > Vars.w) {
				t.remove();
			}
		}
	}
	
	public void shoot () {
		Tear tear = new Tear();
		tear.position.set(MainScreen.manager.player.getPosition());
		tears.add(tear);
	}
	
	
}
