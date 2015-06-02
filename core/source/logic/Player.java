package logic;

import static resources.Vars.seed;
import resources.Vars;




public class Player {
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
	
	private String	_Name;
	private Health	_Health;
	private double	_Damage;
	private double	_Tears;
	private double	_ShotSpeed;
	private double	_Range;
	private double	_Speed;
	private int 	_Luck;
	private Carry	_Carry;
	
	private double p, q, r, s, t, u;
	
	public Player () {
		int c = Vars.CHARACTER;
		_Name = __Characters[c];
		_Health = new Health(c, __Health[c]);
		if (c != 9) {
			_Damage = 3.5 * __Damage[c];
			_Tears = (__Tears[c] > 0) ? 
					16 - Math.sqrt(1 + __Tears[c] * 1.3) * 6 :
					16 - __Tears[c] * 6;
			_ShotSpeed = __ShotSpeed[c];
			_Range = __Range[c] - 0.25;
			_Speed = __Speed[c] * 5;
			_Luck = __Luck[c];
			_Carry = new Carry(__StartingPickups[c], __StartingItem[c]);
		} else {
			p = seed.nD(); q = seed.nD(); r = seed.nD();
			s = seed.nD(); t = seed.nD(); u = seed.nD();
			_Damage = 3.5 * __Damage[c] + p * 0.58 - 0.29;
			_Tears = (__Tears[c] > 0) ?
					16 - Math.sqrt(1 + (__Tears[c] + q * 1.4 - 0.7) * 1.3) * 6 :
					16 - (__Tears[c] + q * 1.4 - 0.7) * 6;
			_ShotSpeed = __ShotSpeed[c] + r * 0.5 - 0.25;
			_Range = __Range[c] - 0.25 + s * 10 - 5;
			_Speed = __Speed[c] + t * 0.4 - 0.2;
			_Luck = (u < 0.5) ? ++__Luck[c] : --__Luck[c];
			int[] pu = {(int) ((p+q)/2*3), (int) ((r+s)/2*3), (int) ((t+u)/2*3)};
			_Carry = new Carry(pu, __StartingItem[c]);
		}
	}
	
	public void setHealth (int ID, int val) {
		_Health.setHealth(ID, val);
	}
	
	public void setDamage (double val) {
		_Damage += val;
	}
	
	public void setTears (double val) {
		_Tears -= (val > 0) ? Math.sqrt(1 + val * 1.3) * 6 : val * 6;
	}
	
	public void setShotSpeed (double val) {
		_ShotSpeed += val;
	}
	
	public void setRange (double val) {
		_Range += val;
	}
	
	public void setSpeed (double val) {
		_Speed += val;
	}
	
	public void setLuck (int val) {
		_Luck += val;
	}
	
	public void setAll () {
		_Health.setHealth(0x09, 1);
		_Health.setHealth(0x10, 1);
		_Damage++;
		_Tears -= Math.sqrt(2.3) * 6;
		_ShotSpeed++;
		_Range++;
		_Speed++;
		_Luck++;
	}
	
	public void setBombs (int val) {
		_Carry.setBombs(val);
	}
	
	public void setKeys (int val) {
		_Carry.setKeys(val);
	}
	
	public void setCoins (int val) {
		_Carry.setCoins(val);
	}
	
	public String getName () {
		return _Name;
	}
	
	public int getFullHealth () {
		return _Health.fullHealth();
	}
	
	public Health getHealth () {
		return _Health;
	}
	
	public float getDamage () {
		return (float) _Damage;
	}
	
	public float getTears () {
		return (float) _Tears;
	}
	
	public float getShotSpeed () {
		return (float) _ShotSpeed;
	}
	
	public float getRange () {
		return (float) _Range;
	}
	
	public float getSpeed () {
		return (float) _Speed;
	}
	
	public int getLuck () {
		return _Luck;
	}
	
	public int getBombs () {
		return _Carry.getBombs();
	}
	
	public int getKeys () {
		return _Carry.getKeys();
	}
	
	public int getCoins () {
		return _Carry.getCoins();
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
				getName(), getFullHealth(), getDamage(), getTears(),
				getShotSpeed(), getRange(), getSpeed(), getLuck(),
				getBombs(), getKeys(), getCoins());
	}
}