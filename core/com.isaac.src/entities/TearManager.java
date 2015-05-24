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
	private Array<Tear> tearsPending;
	private Array<DynamicBody> tearBodiesRemoval;
	
	double delay;
	double grace;
	boolean isGrace;
	
	int tID;
	
	public TearManager () {
		tears = new Array<Tear>();
		tearBodies = new Array<DynamicBody>();
		tearsPending = new Array<Tear>();
		tearBodiesRemoval = new Array<DynamicBody>();
		delay = 0.3;
	}
	
	public void update () {
		Iterator<DynamicBody> b = tearBodies.iterator();
		Iterator<Tear> t = tears.iterator();
		while (t.hasNext()) {
			Tear tear = t.next();
			DynamicBody body = b.next();
			//tear.update();
			if (tear.hit
					|| body.getLinearVelocity().x != tear.vec.x
					|| body.getLinearVelocity().y != tear.vec.y
					//|| System.currentTimeMillis() - tear.born > 750
					//|| Math.abs(body.getLinearVelocity().x - tear.position.x) > 0
					//|| Math.abs(body.getLinearVelocity().y - tear.position.y) > 0
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
	
	public void shoot () {
		if (isGrace) {
			if (System.currentTimeMillis()/1000. - grace > delay) {
				isGrace = false;
			}
			return;
		}		
		Tear tear = new Tear(tID);
		tear.vec = MainScreen.eManager.player.getPosition();
		//tear.setLinearVelocity(new Vector2(10, 0));
		
		DynamicBody tearBody = new DynamicBody();
		tearBody.createTear(tID);
		tearBody.setLinearVelocity(new Vector2(10, 0));
		
		tears.add(tear);
		tearBodies.add(tearBody);
		
		grace = System.currentTimeMillis()/1000.;
		isGrace = true;
		++tID;
	}
}