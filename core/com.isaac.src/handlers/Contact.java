package handlers;

import main.MainScreen;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import entities.Entity;
import entities.EntityManager;


public class Contact implements ContactListener{
	
	double grace;
	boolean isGrace;

	@Override	// called when two fixtures start to collide
	public void beginContact(com.badlogic.gdx.physics.box2d.Contact c) {
		
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
				MainScreen.eManager.PLAYER.damagePlayer(1);
				grace = System.currentTimeMillis()/1000.;
				isGrace = true;
			} else
			if ("ERWD".contains(aUD[0]) && bUD[0].equals("T")) {
				MainScreen.tManager.setHit(Integer.valueOf(bUD[1]));
				Entity e = EntityManager.currentRoom.MONSTERS.get(aUD[1]);
				e.eMONSTER.damage(MainScreen.eManager.PLAYER.ePLAYER.getDamage());
				if (e.eMONSTER.isDead()) {
					e.body.destroyBody();
				}
				EntityManager.currentRoom.MONSTERS.put(Integer.valueOf(aUD[1]), e);
				System.out.println(e.eMONSTER.getHP());
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
