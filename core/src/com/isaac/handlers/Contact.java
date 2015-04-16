package com.isaac.handlers;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Contact implements ContactListener{

	@Override	// called when two fixtures start to collide
	public void beginContact(com.badlogic.gdx.physics.box2d.Contact c) {
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		String fas = (String) fa.getUserData();
		String fbs = (String) fb.getUserData();
		
		if (fbs != null && fbs.equals("P")) {
			
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
