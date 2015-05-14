package entities;

import java.util.Iterator;

import main.MainScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import resources.Vars;



public class TearManager {
	
	public Array<Tear> tears;
	public Array<DynamicBody> tearBodies;
	
	double delay;
	double grace;
	boolean isGrace;
	
	int tID;
	
	public TearManager () {
		tears = new Array<Tear>();
		tearBodies = new Array<DynamicBody>();
		delay = 0.3;
	}
	
	public void update () {
		Iterator<DynamicBody> b = tearBodies.iterator();
		for (Iterator<Tear> t = tears.iterator(); t.hasNext(); ) {
			Tear tear = t.next();
			DynamicBody body = b.next();
			tear.update();
			//body.setPosition(new Vector2(0, 5));
			body.setLinearVelocity(new Vector2(1, 0));
			if (tear.position.dst(tear.spawnPos) > Vars.w/Vars.R/2 || tear.hit) {
				t.remove();
				b.remove();
				body.destroyBody();
			}
		}
	}
	
	public void shoot () {
		if (isGrace) {
			if (System.currentTimeMillis()/1000. - grace > delay) {
				isGrace = false;
			}
			return;
		}
		Tear tear = new Tear(tID);
		tear.position = MainScreen.eManager.player.getPosition();
		//tear.position.set(tear.position);
		tear.setSpawnPosition();
		tears.add(tear);
		DynamicBody tearBody = new DynamicBody();
		tearBody.createTear(tID);
		tearBodies.add(tearBody);
		grace = System.currentTimeMillis()/1000.;
		isGrace = true;
		++tID;
	}
	
	public void setHit (int ID) {
		for (Iterator<Tear> t = tears.iterator(); t.hasNext(); ) {
			Tear tear = t.next();
			tear.hit = (tear.ID == ID) ? true : tear.hit;
			t.remove();
			tears.add(tear);
		}
	}
}
