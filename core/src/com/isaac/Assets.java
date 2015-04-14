package com.isaac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	private static int w = Gdx.graphics.getWidth();
	private static int h = Gdx.graphics.getHeight();
	
	public static Texture tBack;
	public static Sprite sBack;
	public static Sprite sBackFloor0;
	public static Sprite sBackFloor1;
	
	public static Texture tDoorHole;
	public static Sprite sDoorHole;
	
	public static boolean[][] rockMap;
	public static int[][] rockMapR;
	public static Texture tRockBasement;
	public static Sprite[] sRockBasement;
	
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
		sRockBasement = new Sprite[9];
		
		for (int i = 0; i < 9; ++i) {
			sRockBasement[i] = (i < 4) ? new Sprite(tRockBasement, i*32, 0, 32, 32)
					: (i < 6) ? new Sprite(tRockBasement, i*32, 32, 32, 32)
					: new Sprite(tRockBasement, i*32, 64, 32, 32);
			sRockBasement[i].flip(false, true);
		}
		rockMap = new boolean[7][13];
		rockMapR = new int[7][13];
		
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				rockMap[i][j] = true;
				rockMapR[i][j] = (Math.random() > 0.66) ? 3 : 
						(Math.random() > 0.66) ? 1 :
						(Math.random() > 0.66) ? 2 : 0;
			}
		}
		
	}
}
