package logic;

import java.util.HashMap;
import java.util.Map;


class Item {
	/*
	 *	Items are an integral part of game-play in this game. They modify player's
	 *	Attributes, grant or modify Tear Effects and much more.
	 */
	
	private static final Map<Integer, String> Items =
			new HashMap<Integer, String>();
	static {
		Items.put(0x30, "THE SAD ONION | Tears up");
		Items.put(0x31, "THE INNER EYE | Triple shot");
		Items.put(0x32, "SPOON BENDER | Homing shots");
		Items.put(0x33, "CRICKET'S HEAD | DMG up");
		Items.put(0x34, "MY REFLECTION | Boomerang tears");
		Items.put(0x35, "NUMBER ONE | Tears up");
		Items.put(0x36, "BLOOD OF THE MARTYR | DMG up");
		Items.put(0x37, "BROTHER BOBBY | Friends 'till the end");
		Items.put(0x38, "SKATOLE | Fly love");
		Items.put(0x39, "HALO OF FLIES | Projectile protection");
		Items.put(0x3A, "1UP | Extra life");
		Items.put(0x3B, "MAGIC MUSHROOM | All stats up!");
		Items.put(0x3C, "THE VIRUS | Poison touch");
		Items.put(0x3D, "ROID RAGE | Speed and range up");
		Items.put(0x3E, "<3 | HP up");
		Items.put(0x3F, "RAW LIVER | HP up");
		Items.put(0x40, "SKELETON KEY | 99 keys");
		Items.put(0x41, "A DOLLAR | $$$");
		Items.put(0x42, "BOOM! | 10 bombs");
	}
	
	public String getItem (int ID) {
		return Items.get(ID);
	}
	
	public void useItem (Player P, int ID) {
		System.out.printf("\n<  < < %s > >  >\n", getItem(ID));
		switch (ID) {
			case 0x30:	P.setTears(0.7); 
						break;
			case 0x31:	P.setTears(P.getTears() * 2.1 + 3); 
						/*TODO*/ break;
			case 0x32:	/*TODO*/ break;
			case 0x33:	P.setDamage(0.5 + P.getDamage() / 2); 
						/*TODO*/ break;
			case 0x34:	P.setRange(1.5); 
						P.setShotSpeed(0.6);
						/*TODO*/ break;
			case 0x35:	P.setTears(1.5); 
						P.setRange(-15.78); 
						break;
			case 0x36:	P.setDamage(1); 
						break;
			case 0x37:	/*TODO*/ break;
			case 0x38:	/*TODO*/ break;
			case 0x39:	/*TODO*/ break;
			case 0x3A:	/*TODO*/ break;
			case 0x3B:	P.setHealth(0x09, 1);
						P.setHealth(0x0A, 1);
						P.setDamage(0.3 * P.getDamage() / 2);
						P.setRange(5.25);
						P.setSpeed(0.3);
						break;
			case 0x3C:	/*TODO*/ break;
			case 0x3D:	P.setSpeed(0.6);
						P.setRange(5.25);
						break;
			case 0x3E:	P.setHealth(0x09, 1);
						P.setHealth(0x0A, 12);
						break;
			case 0x3F:	P.setHealth(0x09, 2);
						P.setHealth(0x0A, 12);
						break;
			case 0x40:	P.setKeys(99);
						break;
			case 0x41:	P.setCoins(99);
						break;
			case 0x42:	P.setBombs(10);
						break;
		}
	}
}