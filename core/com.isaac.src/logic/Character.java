package logic;

import resources.Seed;


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