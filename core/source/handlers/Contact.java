package handlers;

import logic.Monster;
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
			} else
			if ("WD".contains(aUD[0]) && bUD[0].equals("M")) {
				Monster monster = MainScreen.eManager.getMonster(bUD[1]);
				if (monster.getName().equals("Red Boom Fly")) {
					bounce(monster, Integer.parseInt(aUD[1]));
				}
			} else
			if (aUD[0].equals(bUD[0]) && bUD[0].equals("M")) {
				Monster monsterA = MainScreen.eManager.getMonster(aUD[1]);
				Monster monsterB = MainScreen.eManager.getMonster(bUD[1]);
				if (monsterA.getName().equals("Red Boom Fly")
						&& monsterB.getName().equals("Red Boom Fly")) {
					int dir = monsterA.getDirection();
					monsterA.setDirection(monsterB.getDirection());
					monsterB.setDirection(dir);
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
	
	private void bounce (Monster monster, int side) {
		switch (side) {
		case 0:
			monster.setDirection((monster.getDirection() == 3) ? 1 : 7);
			break;
		case 1:
			monster.setDirection((monster.getDirection() == 7) ? 5 : 3);
			break;
		case 2:
			monster.setDirection((monster.getDirection() == 5) ? 3 : 1);
			break;
		case 3:
			monster.setDirection((monster.getDirection() == 1) ? 7 : 5);
			break;
		}
	}
}
