package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
	private Vector2 vec;
	
	int tID;
	
	public TearManager () {
		tears = new Array<Tear>();
		tearBodies = new Array<DynamicBody>();
		delay = 0.3;
		vec = new Vector2(0, 0);
	}
	
	public void update () {
		if (tears.size == 0) {
			tears.clear();
			tearBodies.clear();
			return;
		}
		Iterator<DynamicBody> b = tearBodies.iterator();
		Iterator<Tear> t = tears.iterator();
		while (t.hasNext()) {
			Tear tear = t.next();
			DynamicBody body = b.next();
			//tear.update();
			if (tear.hit
					|| System.currentTimeMillis() - tear.born > 750
					|| body.getLinearVelocity().x != tear.vec.x
					|| body.getLinearVelocity().y != tear.vec.y
					) {
				t.remove();
				b.remove();
				body.destroyBody();
				break;
			}
		}
	}
	
	public void setHit (int ID) {
		Iterator<Tear> t = tears.iterator();
		while (t.hasNext()) {
			Tear tear = t.next();
			if (tear.ID == ID) {
				tear.hit = (tear.ID == ID);
				t.remove();
				tears.add(tear);
				break;
			}
		}
	}
	
	public void shoot (int b) {
		if (isGrace) {
			if (System.currentTimeMillis()/1000. - grace > delay) {
				isGrace = false;
			}
			return;
		}		
		Tear tear = new Tear(tID);
		//tear.vec = MainScreen.eManager.player.getPosition();
		
		DynamicBody tearBody = new DynamicBody();
		tearBody.createTear(tID);
		
		switch (b) {
			case 0:    vec.x = 0; vec.y = -10; break;
			case 1:    vec.x = -10; vec.y = 0; break;
			case 2:    vec.x = 0; vec.y = 10; break;
			case 3:    vec.x = 10; vec.y = 0; break;
		}
		tearBody.setLinearVelocity(vec);
		tear.vec = vec;
		
		tears.add(tear);
		tearBodies.add(tearBody);
		
		grace = System.currentTimeMillis()/1000.;
		isGrace = true;
		++tID;
	}
}