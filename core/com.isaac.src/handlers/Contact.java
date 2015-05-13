package handlers;

import main.MainScreen;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


public class Contact implements ContactListener{
	
	double grace;
	boolean isGrace;

	@Override	// called when two fixtures start to collide
	public void beginContact(com.badlogic.gdx.physics.box2d.Contact c) {
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		String aUD = (String) fa.getUserData();
		String bUD = (String) fb.getUserData();
		
		if (aUD != null && bUD != null) {
			if (aUD.equals("P") && bUD.equals("M")) {
				if (isGrace) {
					if (System.currentTimeMillis()/1000. - grace > 1) {
							isGrace = false;
					}
					return;
				}
				MainScreen.eManager.PLAYER.damagePlayer(1);
				grace = System.currentTimeMillis()/1000.;
				isGrace = true;
			}
		}
	}

	@Override	// called when two fixtures no longer collide
	public void endContact(com.badlogic.gdx.physics.box2d.Contact c) {
		
	}

	@Override
	public void preSolve(com.badlogic.gdx.physics.box2d.Contact c,
			Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(com.badlogic.gdx.physics.box2d.Contact c,
			ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
