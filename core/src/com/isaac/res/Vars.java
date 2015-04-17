package com.isaac.res;

import com.badlogic.gdx.Gdx;
import com.isaac.main.MainScreen;


public class Vars {
	
	public static Seed seed;
	
// pixels per meter
	public static final float PM = 64;
	
// category bits
	public static final short bGROUND = 1;	// 0000 0000 0000 0001
	public static final short bWALL = 2;	// 0000 0000 0000 0010
	public static final short bROCK = 4;	// 0000 0000 0000 0100
	public static final short bPLAYER = 8;	// 0000 0000 0000 1000
	
// limits
	public static float SPEED = 5;
	
// window
	public static int w = Gdx.graphics.getWidth();
	public static int h = Gdx.graphics.getHeight();
	public static float x = w / 9 * 7 / 13;
	public static float y = h / 6 * 4 / 7;
	
// coordinates
	public static float x (int row) {
		// x coordinate of a row
		return w / 9 + x * row;
	}
	
	public static float y (int col) {
		// y coordinate of a column
		return h / 6 + y * col;
	}
	
	
	
}
