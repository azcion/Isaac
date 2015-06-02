package resources;

import com.badlogic.gdx.Gdx;


public class Vars {
	
	public static Seed seed;
	public static final int CHARACTER = 0; ///////////////////////
	public static final int MAPS = 4;
	
// pixels per meter
	public static final float R = 64;
	
// category bits
	public static final short bGROUND = 1;  // 0000 0000 0000 0001
	public static final short bWALL = 2;    // 0000 0000 0000 0010
	public static final short bROCK = 4;    // 0000 0000 0000 0100
	public static final short bDOOR = 8;    // 0000 0000 0000 1000
	public static final short bPLAYER = 16; // 0000 0000 0001 0000
	public static final short bENTITY = 32; // 0000 0000 0010 0000
	public static final short bTEAR = 64;   // 0000 0000 0100 0000
	
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
