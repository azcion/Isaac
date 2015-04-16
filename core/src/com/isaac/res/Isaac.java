package com.isaac.res;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Isaac {
	
	
	public static void main (String[] args) {
		
		Character P = new Character (9, 23745261);
		
		System.out.print(P.toString());
	}
}

class Character {
	/*
	 * 	Health represents the amount of damage the player can take before dying.
	 * 	Damage refers to the amount of damage each tear does to enemies.
	 * 	Tears determine the rate of fire of the character.
	 * 	Range determines how long tears will travel before landing.
	 * 	Shot Speed governs how quickly an individual tear travels.
	 * 	Speed determines how quickly the character moves.
	 * 	Luck affects a variety of chance-based effects.
	 */
	
	private static final String[]	__Characters = {
			"Isaac", "Magdalene", "Cain", "Judas", "???", "Eve", 
			"Samson", "Azazel", "Lazarus", "Eden", "The Lost"
	};
	private static final int[]  	__Health = {
			3,    4,    2,    1,    0,    2,    0,    3,    3,    2,    0
	};
	private static final double[]   __Damage = {
			1.0,  1.0,  1.2,  1.35, 1.05, 0.75, 1.0,  1.5,  1.0,  1.0,  1.0
	};
	private static final double[]   __Tears = {
			0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.05, 0.5,  0.0,  0.0,  0.0
	};
	private static final double[]   __ShotSpeed = {
			1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.31, 1.0,  1.0,  1.0,  1.0
	};
	private static final int[]  	__Range = {
			24,   24,   18,   24,   24,   24,   19,   18,   18,   24,   24 
	};
	private static final double[]   __Speed = {
			1.0,  0.85, 1.3,  1.0,  1.1,  1.23, 1.1,  1.25, 1.0,  1.0,  1.0
	};
	private static final int[]  	__Luck = {
			0,    0,    0,    0,    0,    0,    0,    0,   -1,    0,    0
	};
	private static final int[][]	__StartingPickups = {
			{1,0,0}, {0,0,0}, {0,1,0}, {0,0,3}, {0,0,0},
			{0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}, {0,0,1}
	};
	private static final int[]  	__StartingItem = {
			0,    0,    0,    0,    0,    0,    0,    0x10, 0,    0,    0
	};
	
	private final Seed R;
	private String	_Name;
	private Health	_Health;
	private double	_Damage;
	private double	_Tears;
	private double	_ShotSpeed;
	private double	_Range;
	private double	_Speed;
	private int 	_Luck;
	private Carry	_Carry;
	
	public Character (int ch, long seed) {
		this.R = new Seed(seed);
		this._Name = __Characters[ch];
		this._Health = new Health(ch, __Health[ch]);
		if (ch != 9) {
			this._Damage = 3.5 * __Damage[ch];
			this._Tears = (__Tears[ch] > 0) ? 
					16 - Math.sqrt(1 + __Tears[ch] * 1.3) * 6 :
					16 - __Tears[ch] * 6;
			this._ShotSpeed = __ShotSpeed[ch];
			this._Range = __Range[ch] - 0.25;
			this._Speed = __Speed[ch];
			this._Luck = __Luck[ch];
			this._Carry = new Carry(__StartingPickups[ch], __StartingItem[ch]);
		} else {
			double p = R.nD(); double q = R.nD(); double r = R.nD();
			double s = R.nD(); double t = R.nD(); double u = R.nD();
			this._Damage = 3.5 * __Damage[ch] + p * 0.58 - 0.29;
			this._Tears = (__Tears[ch] > 0) ?
					16 - Math.sqrt(1 + (__Tears[ch] + q * 1.4 - 0.7) * 1.3) * 6 :
					16 - (__Tears[ch] + q * 1.4 - 0.7) * 6;
			this._ShotSpeed = __ShotSpeed[ch] + r * 0.5 - 0.25;
			this._Range = __Range[ch] - 0.25 + s * 10 - 5;
			this._Speed = __Speed[ch] + t * 0.4 - 0.2;
			this._Luck = (u < 0.5) ? ++__Luck[ch] : --__Luck[ch];
			int[] pu = {(int) ((p+q)/2*3), (int) ((r+s)/2*3), (int) ((t+u)/2*3)};
			this._Carry = new Carry(pu, __StartingItem[ch]);
		}
	}
	
	public void setHealth (int ID, int val) {
		this._Health.setHealth(ID, val);
	}
	
	public void setDamage (double val) {
		this._Damage += val;
	}
	
	public void setTears (double val) {
		this._Tears -= (val > 0) ? Math.sqrt(1 + val * 1.3) * 6 : val * 6;
	}
	
	public void setShotSpeed (double val) {
		this._ShotSpeed += val;
	}
	
	public void setRange (double val) {
		this._Range += val;
	}
	
	public void setSpeed (double val) {
		this._Speed += val;
	}
	
	public void setLuck (int val) {
		this._Luck += val;
	}
	
	public void setAll () {
		this._Health.setHealth(0x09, 1);
		this._Health.setHealth(0x10, 1);
		this._Damage++;
		this._Tears -= Math.sqrt(2.3) * 6;
		this._ShotSpeed++;
		this._Range++;
		this._Speed++;
		this._Luck++;
	}
	
	public void setBombs (int val) {
		this._Carry.setBombs(val);
	}
	
	public void setKeys (int val) {
		this._Carry.setKeys(val);
	}
	
	public void setCoins (int val) {
		this._Carry.setCoins(val);
	}
	
	public String getName () {
		return this._Name;
	}
	
	public int getHealth () {
		return this._Health.fullHealth();
	}
	
	public double getDamage () {
		return this._Damage;
	}
	
	public double getTears () {
		return this._Tears;
	}
	
	public double getShotSpeed () {
		return this._ShotSpeed;
	}
	
	public double getRange () {
		return this._Range;
	}
	
	public double getSpeed () {
		return this._Speed;
	}
	
	public int getLuck () {
		return this._Luck;
	}
	
	public int getBombs () {
		return this._Carry.getBombs();
	}
	
	public int getKeys () {
		return this._Carry.getKeys();
	}
	
	public int getCoins () {
		return this._Carry.getCoins();
	}
	
	public String toString() {
		return String.format(
				 "Character:   %s\n"
				+"Health:      %d\n"
				+"Damage:      %.2f\n"
				+"Tears:       %.2f\n"
				+"Shot speed:  %.2f\n"
				+"Range:       %.2f\n"
				+"Speed:       %.2f\n"
				+"Luck:        %d\n"
				+"Bombs:       %d\n"
				+"Keys:        %d\n"
				+"Coins:       %d\n",
				getName(), getHealth(), getDamage(), getTears(),
				getShotSpeed(), getRange(), getSpeed(), getLuck(),
				getBombs(), getKeys(), getCoins());
	}
}

class Health {
	/*
	 * 	There are four different types of health, each with their own pickups.
	 *	The order in which hearts are placed is: Red, Soul, Black.
	 *	Hearts are generally lost from right to left.
	 *	The character can hold a maximum of twelve hearts.
	 */
	
	private int Container;
	private int RedHeart;
	private int SoulHeart;
	private int DarkHeart;
	private int[] Order;
	
	public Health (int ch, int cont) {
		this.Order = new int[4];
		if (ch != 9) {
			this.Container = cont;
			this.RedHeart = cont;
			this.SoulHeart = (cont != 0) ? 0 : (ch == 4) ? 3 : 0;
			this.DarkHeart = (cont != 0) ? 0 : (ch == 7) ? 3 : 0;
		} else {
			int total = 3;
			this.Container = (int) (Math.random() * (total + 1));
			total -= this.Container;
			this.SoulHeart = (int) (Math.random() * (total + 1));
			this.DarkHeart = 0;
		}
		setOrder();
	}

	public void setHealth (int ID, int val) {
		switch (ID) {
			case 0x09:	this.Container += val; break;
			case 0x0A:	this.RedHeart += val; break;
			case 0x0B:	this.SoulHeart += val; break;
			case 0x0C:	this.DarkHeart += val; break;
		}
		trimHealth();
	}
	
	public void setOrder () {
		this.Order[0] = Container;
		this.Order[1] = RedHeart;
		this.Order[2] = SoulHeart;
		this.Order[3] = DarkHeart;
	}
	
	public int[] getOrder () {
		return this.Order;
	}
	
	public int fullHealth () {
		return this.RedHeart + this.SoulHeart + this.DarkHeart;
	}
	
	public boolean isDead () {
		return fullHealth() == 0;
	}
	
	public void trimHealth () {
		while (fullHealth() > 12) {
			if (this.Order[3] > 0) {--this.DarkHeart;
			} else
			if (this.Order[2] > 0) {--this.SoulHeart;
			} else
			if (this.Order[0] > 12) {--this.Container;
			} else
			if (this.Order[1] > 1) {--this.RedHeart;
			}
		}
		if (this.RedHeart > this.Container) {
			this.RedHeart = this.Container;
		}
		setOrder();
	}
}

class Carry {
	/*
	 *	Carried pickups are tools the player can use to interact with the game.
	 *	Bombs produce blast damage, Keys unlock doors and chests, and Coins
	 *	can be used to purchase items in the store room.
	 *	Items are special pickups that produce other effects, such as increase
	 *	player's Health, decrease his speed and more.
	 */
	
	private int Bombs;
	private int Keys;
	private int Coins;
	private int Item;
	
	public Carry (int[] tools, int item) {
		this.Bombs = tools[0];
		this.Keys = tools[1];
		this.Coins = tools[2];
		this.Item = item;
	}
	
	public int getBombs () {
		return this.Bombs;
	}
	
	public int getKeys () {
		return this.Keys;
	}
	
	public int getCoins () {
		return this.Coins;
	}
	
	public int getItem () {
		return this.Item;
	}
	
	public void setBombs (int val) {
		this.Bombs = val;
	}
	
	public void setKeys (int val) {
		this.Keys = val;
	}
	
	public void setCoins (int val) {
		this.Coins = val;
	}
}

class Pickup {
	/*
	 *	Pickups are minor items that can be found around the game.
	 *	There are eight sub-categories within three categories of pickups:
	 *	Hearts (Red, Soul, Black), 
	 *	Tools (Coins, Bombs, Keys) and 
	 *	Carried (Pills, Cards).	
	 */
	
	private static final int RedHeart	= 0x0A;
	private static final int SoulHeart	= 0x0B;
	private static final int DarkHeart	= 0x0C;
	
	private static final int Bomb		= 0x0D;
	private static final int Key		= 0x0E;
	private static final int Coin		= 0x0F;
	
	private static final Map<Integer, String> Cards = 
			new HashMap<Integer, String>();
	static {
		Cards.put(0x10, "The Fool | Where the journey begins");
		Cards.put(0x11, "The Magician | May you never miss your goal");
		Cards.put(0x12, "The High Priestess | Mother is watching you");
		Cards.put(0x13, "The Empress | May your rage bring power");
		Cards.put(0x14, "The Emperor | Challenge me!");
		Cards.put(0x15, "The Hierophant | Two prayers for the lost");
		Cards.put(0x16, "The Lovers | May you prosper and be in good health");
		Cards.put(0x17, "The Chariot | May nothing stand before you");
		Cards.put(0x18, "Justice | May your future become balanced");
		Cards.put(0x19, "The Hermit | May you see what life has to offer");
		Cards.put(0x1A, "Wheel of Fortune | Spin the wheel of destiny");
		Cards.put(0x1B, "Strength | May your power bring rage");
		Cards.put(0x1C, "The Hanged Man | May you find enlightenment");
		Cards.put(0x1D, "Death | Lay waste to all who oppose you");
		Cards.put(0x1E, "Temperance | May you be pure in heart");
		Cards.put(0x1F, "The Devil | Revel in the power of darkness");
		Cards.put(0x20, "The Tower | Destruction brings creation");
		Cards.put(0x21, "The Stars | May you find what you desire");
		Cards.put(0x22, "The Moon | May you find all you have lost");
		Cards.put(0x23, "The Sun | May the light heal and enlighten you");
		Cards.put(0x24, "Judgement | Judge lest ye be judged");
		Cards.put(0x25, "The World | Open your eyes and see");
		Cards.put(0x26, "Joker | ???");
		Cards.put(0x27, "2 of Clubs | Item multiplier");
		Cards.put(0x28, "2 of Spades | Item multiplier");
		Cards.put(0x29, "2 of Diamonds | Item multiplier");
		Cards.put(0x2A, "2 of Hearts | Item multiplier");
	}
	
	public String getCard (int ID) {
		return Cards.get(ID);
	}
	
	public void useCard (Character P, int ID) {
		System.out.printf("\n<  < < %s > >  >\n", getCard(ID));
		switch (ID) {
			case 0x10:	/*TODO*/ break;
			case 0x11:	/*TODO*/ break;
			case 0x12:	/*TODO*/ break;
			case 0x13:	P.setDamage(1.5); P.setSpeed(0.3); break;
			case 0x14:	/*TODO*/ break;
			case 0x15:	/*TODO*/ break;
			case 0x16:	/*TODO*/ break;
			case 0x17:	/*TODO*/ break;
			case 0x18:	/*TODO*/ break;
			case 0x19:	/*TODO*/ break;
			case 0x1A:	/*TODO*/ break;
			case 0x1B:	P.setAll(); /*TODO*/ break;
			case 0x1C:	/*TODO*/ break;
			case 0x1D:	/*TODO*/ break;
			case 0x1E:	/*TODO*/ break;
			case 0x1F:	P.setDamage(2); /*TODO*/ break;
			case 0x20:	/*TODO*/ break;
			case 0x21:	/*TODO*/ break;
			case 0x22:	/*TODO*/ break;
			case 0x23:	P.setHealth(0x0A, 12); /*TODO*/ break;
			case 0x24:	/*TODO*/ break;
			case 0x25:	/*TODO*/ break;
			case 0x26:	/*TODO*/ break;
			case 0x27:	int b = (P.getBombs() != 0) ? P.getBombs() * 2 : 2;
						P.setBombs(b); break;
			case 0x28:	int k = (P.getKeys() != 0) ? P.getKeys() * 2 : 2;
						P.setCoins(k); break;
			case 0x29:	int c = (P.getCoins() != 0) ? P.getCoins() * 2 : 2;
						P.setCoins(c); break;
			case 0x2A:	P.setHealth(0x0A, P.getHealth());
		}
	}
}

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
	
	public void useItem (Character P, int ID) {
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


















