package entities;

import static resources.Vars.R;
import static resources.Vars.h;
import static resources.Vars.w;
import static resources.Vars.x;
import main.MainScreen;
import resources.Vars;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;


public class DynamicBody {
	
	private static float X;
	private static float Y;
	
	public boolean dead;
	
	Body body;
	private static BodyDef bdef = new BodyDef();
	private static FixtureDef fdef = new FixtureDef();
	
	public DynamicBody () {
		dead = false;
		bdef = new BodyDef();
		fdef = new FixtureDef();
	}
	
	public static void setCoords (float x, float y) {
		DynamicBody.X = x;
		DynamicBody.Y = y;
	}
	
	public void createPlayer () {
		CircleShape head = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = head;
		fdef.filter.categoryBits = Vars.bPLAYER;
		fdef.filter.maskBits = Vars.bDOOR | Vars.bWALL | Vars.bROCK | Vars.bENTITY;
		fdef.isSensor = false;
		
		bdef.position.set(X+w/2/R, Y+(h/2+x/2)/R);
		body = MainScreen.world.createBody(bdef);
		head.setRadius(x/3/R);
		body.createFixture(fdef).setUserData("P");
		body.setGravityScale(0);
		
		/*
		PolygonShape body = new PolygonShape();
		fdef.shape = body;
		
		bdef.position.set(w/2/R, (h/2)/R);
		//entityBody = MainScreen.world.createBody(bdef);
		body.setAsBox(x/10/R, y/10/R);
		entityBody.createFixture(fdef).setUserData("P");
		entityBody.setGravityScale(0);*/
	}
	
	public void createTear (int ID) {
		CircleShape tear = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = tear;
		fdef.filter.categoryBits = Vars.bTEAR;
		fdef.filter.maskBits = Vars.bDOOR | Vars.bWALL | Vars.bENTITY | Vars.bROCK;
		fdef.isSensor = false;
		
		bdef.position.set(
				MainScreen.eManager.playerBody.getPosition().x,
				MainScreen.eManager.playerBody.getPosition().y);
		body = MainScreen.world.createBody(bdef);
		tear.setRadius(x/10/R);
		body.createFixture(fdef).setUserData("T_" + ID);
		body.setGravityScale(0);
	}
	
	public void createFly (float a, float b, int ID) {
		CircleShape fly = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = fly;
		fdef.filter.categoryBits = Vars.bENTITY;
		fdef.filter.maskBits = Vars.bDOOR | Vars.bWALL | Vars.bPLAYER | Vars.bENTITY | Vars.bTEAR;
		fdef.isSensor = false;
		
		bdef.position.set(X+b/R, Y+a/R);
		body = MainScreen.world.createBody(bdef);
		fly.setRadius(x/8/R);
		body.createFixture(fdef).setUserData("M_" + ID);
		body.setGravityScale(0);
	}
	
	public Vector2 getLinearVelocity () {
		return body.getLinearVelocity();
	}
	
	public void setLinearVelocity (Vector2 v) {
		body.setLinearVelocity(v);
	}

	public Vector2 getPosition () {
		return body.getPosition();
	}
	
	public void setPosition (Vector2 v) {
		body.setLinearVelocity(v);
	}
	
	public void destroy () {
		dead = true;
	}
	
	public void destroyBody () {
		for (Fixture f : body.getFixtureList()) {
			body.destroyFixture(f);
		}
		body.getWorld().destroyBody(body);
		body.setUserData(null);
		body = null;
		dead = true;
	}
}
