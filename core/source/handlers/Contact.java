package handlers;

import main.MainScreen;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import entities.EntityManager;


public class Contact implements ContactListener {
	
	double grace;
	boolean isGrace;
	String target;

	@Override	// called when two fixtures start to collide
	public void beginContact (com.badlogic.gdx.physics.box2d.Contact c) {
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		String[] aUD = fa.getUserData().toString().split("_");
		String[] bUD = fb.getUserData().toString().split("_");
		
		if (aUD != null && bUD != null) {
			if (aUD[0].equals("P") && bUD[0].equals("M")) {
				if (isGrace) {
					if (System.currentTimeMillis()/1000. - grace > 1) {
							isGrace = false;
					}
					return;
				}
				MainScreen.eManager.playerEntity.damagePlayer(1);
				grace = System.currentTimeMillis()/1000.;
				isGrace = true;
			} else
			if (aUD[0].equals("T") ^ bUD[0].equals("T")) {
				if (aUD[0].equals("M") ^ bUD[0].equals("M")) {
					target = (aUD[0].equals("M")) ? aUD[1] : bUD[1];
					MainScreen.eManager.damage(Integer.valueOf(target));
				}
			}
		}
	}

	@Override	// called when two fixtures no longer collide
	public void endContact (com.badlogic.gdx.physics.box2d.Contact c) {
		
	}

	@Override
	public void preSolve (com.badlogic.gdx.physics.box2d.Contact c,
			Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve (com.badlogic.gdx.physics.box2d.Contact c,
			ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
