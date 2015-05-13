package entities;

import resources.Vars;
import main.MainScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static resources.Vars.R;
import static resources.Vars.h;
import static resources.Vars.w;
import static resources.Vars.x;
import static resources.Vars.y;


public class DynamicBody {
	
	private static float X;
	private static float Y;
	
	private Body body;
	
	private static BodyDef bdef = new BodyDef();
	private static FixtureDef fdef = new FixtureDef();
	
	public DynamicBody () {
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
	
	public void createFly (float a, float b) {
		CircleShape fly = new CircleShape();
		bdef.type = BodyType.DynamicBody;
		fdef.shape = fly;
		fdef.filter.categoryBits = Vars.bENTITY;
		fdef.filter.maskBits = Vars.bDOOR | Vars.bWALL | Vars.bPLAYER | Vars.bENTITY;
		fdef.isSensor = false;
		
		bdef.position.set(X+b/R, Y+a/R);
		body = MainScreen.world.createBody(bdef);
		fly.setRadius(x/8/R);
		body.createFixture(fdef).setUserData("M");
		body.setGravityScale(0);
	}

	public Vector2 getLinearVelocity () {
		return this.body.getLinearVelocity();
	}
	
	public void setLinearVelocity (Vector2 v) {
		this.body.setLinearVelocity(v);
	}

	public Vector2 getPosition () {
		return this.body.getPosition();
	}
}
