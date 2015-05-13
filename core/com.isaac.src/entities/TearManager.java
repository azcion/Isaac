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
			if (Math.abs(tear.position.x - MainScreen.eManager.player.getPosition().x)
					> Vars.w/Vars.R/2) {
				t.remove();
			}
		}
	}
	
	public void shoot () {
		Tear tear = new Tear();
		tear.position.set(MainScreen.eManager.player.getPosition());
		tears.add(tear);
		
		System.out.println(tears.size);
	}
	
	
}
