package entities;

import graphics.Skin;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



public class TearManager {
	
	public Array<Tear> tears;
	public Array<DynamicBody> tearBodies;
	private Array<Skin> tearSkins;
	
	private double delay;
	private double grace;
	private boolean isGrace;
	private Vector2 vec;
	private int tID;
	private Tear tear;
	private DynamicBody body;
	private Skin skin;
	
	public TearManager () {
		tears = new Array<Tear>();
		tearBodies = new Array<DynamicBody>();
		tearSkins = new Array<Skin>();
		
		delay = 0.3;
		vec = new Vector2();
	}
	
	public void update () {
		if (tears.size == 0) {
			tears.clear();
			tearBodies.clear();
			return;
		}
		Iterator<Tear> t = tears.iterator();
		Iterator<DynamicBody> b = tearBodies.iterator();
		Iterator<Skin> s = tearSkins.iterator();
		while (t.hasNext()) {
			tear = t.next();
			body = b.next();
			skin = s.next();
			if (tear.hit
					|| System.currentTimeMillis() - tear.born > 750
					|| body.getLinearVelocity().x != tear.vec.x
					|| body.getLinearVelocity().y != tear.vec.y
					) {
				t.remove();
				b.remove();
				s.remove();
				body.destroyBody();
				break;
			}
		}
	}
	
	public void render () {
		for (Skin s : tearSkins) {
			s.drawTear();
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
		tear = new Tear(tID);
		body = new DynamicBody();
		skin = new Skin(body);
		body.createTear(tID);
		
		switch (b) {
			case 0:    vec.x = 0; vec.y = -10; break;
			case 1:    vec.x = -10; vec.y = 0; break;
			case 2:    vec.x = 0; vec.y = 10; break;
			case 3:    vec.x = 10; vec.y = 0; break;
		}
		tear.vec = vec;
		body.setLinearVelocity(vec);
		
		tears.add(tear);
		tearBodies.add(body);
		tearSkins.add(skin);
		
		grace = System.currentTimeMillis()/1000.;
		isGrace = true;
		++tID;
	}
}