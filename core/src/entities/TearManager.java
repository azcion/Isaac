package entities;

import graphics.Skin;
import main.MainScreen;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



public class TearManager {
	
	private Array<Tear> tears;
	private Array<DynamicBody> bodies;
	private Array<Skin> skins;
	
	private Array<Tear> tearsPending;
	private Array<DynamicBody> bodiesPending;
	private Array<Skin> skinsPending;
	
	private double delay;
	private static double grace;
	private static boolean isGrace;
	private Vector2 vec;
	private int tID;
	private Tear tear;
	private DynamicBody body;
	private Skin skin;

	public TearManager () {
		tears = new Array<>();
		bodies = new Array<>();
		skins = new Array<>();

		tearsPending = new Array<>();
		bodiesPending = new Array<>();
		skinsPending = new Array<>();
		
		delay = 0.3;
		vec = new Vector2();
	}
	
	public void update () {
		if (tears.size == 0 && tearsPending.size == 0) {
			return;
		}
		if (tearsPending.size > 0) {
			Iterator<Tear> tP = tearsPending.iterator();
			Iterator<DynamicBody> bP = bodiesPending.iterator();
			Iterator<Skin> sP = skinsPending.iterator();
			
			if (tP.hasNext()) {
				tears.add(tP.next());
				bodies.add(bP.next());
				skins.add(sP.next());
				tP.remove();
				bP.remove();
				sP.remove();
			}
		}
		Iterator<Tear> t = tears.iterator();
		Iterator<DynamicBody> b = bodies.iterator();
		Iterator<Skin> s = skins.iterator();

		while (t.hasNext()) {
			tear = t.next();
			body = b.next();
			skin = s.next();
			if (tear.hit
					|| System.currentTimeMillis() - tear.born > 750
					|| body.getLinearVelocity().x != tear.vec.x
					|| body.getLinearVelocity().y != tear.vec.y) {
				t.remove();
				b.remove();
				s.remove();
				body.destroyBody();
				//break;
			}
		}
	}
	
	public void render () {
		for (Skin s : skins) {
			s.drawTear();
		}
	}
	
	private void shoot (int direction, Tear tear) {
		body = new DynamicBody();
		skin = new Skin(body);
		body.createTear(tID);
		
		switch (direction) {
			case 0:
				vec.x =   0;
				vec.y = -10;
				break;
			case 1:
				vec.x = -10;
				vec.y =   0;
				break;
			case 2:
				vec.x =   0;
				vec.y =  10;
				break;
			case 3:
				vec.x =  10;
				vec.y =   0;
				break;
			case 4:
				vec.x = -10;
				vec.y = -10;
				break;
			case 5:
				vec.x = -10;
				vec.y =  10;
				break;
			case 6:
				vec.x =  10;
				vec.y = -10;
				break;
			case 7:
				vec.x =  10;
				vec.y =  10;
		}
		tear.vec = vec;
		body.setLinearVelocity(vec);
		
		tearsPending.add(tear);
		bodiesPending.add(body);
		skinsPending.add(skin);
		
		++tID;
	}
	
	public void shoot (int direction, int mask, int type) {
		/* mask = {player, monster, boss}    type = {forward, plus, cross} */
		
		if (isGrace) {
			if (System.currentTimeMillis()/1000. - grace > delay) {
				isGrace = false;
			}
			return;
		}
		tear = (mask != 0) ? new Tear(tID, mask, 0)
				: new Tear(tID, mask, MainScreen.eManager.getPlayer().getDamage());
		
		switch (type) {
			case 0:
				shoot(direction, tear);
				break;
			case 1:
				for (int i = 0; i < 4; ++i) {
					shoot(i, tear);
				}
				break;
			case 2:
				for (int i = 4; i < 8; ++i) {
					shoot(i, tear);
				}
				break;
		}
		grace = System.currentTimeMillis()/1000.;
		isGrace = true;
	}
}







