package handlers;

public class Controls {
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int KEYS  = 8;
	public static final int W     = 0;
	public static final int A     = 1;
	public static final int S     = 2;
	public static final int D     = 3;
	public static final int UP    = 4;
	public static final int LEFT  = 5;
	public static final int DOWN  = 6;
	public static final int RIGHT = 7;

	
	static {
		keys = new boolean[KEYS];
		pkeys = new boolean[KEYS];
	}
	
	public static void update () {
		for (int i = 0; i < KEYS; ++i) {
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey (int i, boolean b) {
		keys[i] = b;
	}
	
	public static boolean isDown (int i) {
		return keys[i];
	}
	
	public static boolean isPressed (int i) {
		return keys[i] && !pkeys[i];
	}
}
