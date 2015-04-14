package com.isaac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static Texture tBack;
	public static Sprite sBack;
	public static Sprite sBackFloor0;
	public static Sprite sBackFloor1;
	
	public static Texture tDoorHole;
	public static Sprite sDoorHole;
	
	public static Texture tRockBasement;
	public static Sprite sRockBasement0;
	/*public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;
	public static Sprite sRockBasement1;*/
	
	public static void load () {
		tBack = new Texture(Gdx.files.internal("png/basement.png"));
		sBack = new Sprite(tBack, 0, 0, 234, 156);
		sBackFloor0 = new Sprite(tBack, 52, 364, 182, 104);
		sBackFloor1 = new Sprite(tBack, 286, 364, 182, 104);
		sBack.flip(false, true);
		sBackFloor0.flip(false, true);
		sBackFloor1.flip(false, true);
		
		tDoorHole = new Texture(Gdx.files.internal("png/door_hole.png"));
		sDoorHole = new Sprite(tDoorHole);
		
		tRockBasement = new Texture(Gdx.files.internal("png/rocks_basement.png"));
		sRockBasement0 = new Sprite(tRockBasement, 0, 0, 32, 32);
		sRockBasement0.flip(false, true);
		
	}
}
