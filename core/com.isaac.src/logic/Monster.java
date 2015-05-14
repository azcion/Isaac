package logic;



public class Monster {
	
	private static final String[]	__Monsters = {
		"Attack Fly"
	};
	private static final int[]  	__Health = {
		5
	};
	private static final int[]  	__Speed = {
		1
	};
	
	private String	_Name;
	private int 	_Health;
	private int 	_Speed;
	
	public Monster(int ID) {
		_Name = __Monsters[ID];
		_Health = __Health[ID];
		_Speed = __Speed[ID];
	}
	
	public String getName () {
		return _Name;
	}
	
	public int getHP () {
		return _Health;
	}
	
	public double getSpeed () {
		return _Speed;
	}
	
	public boolean isDead () {
		return _Health <= 0;
	}
	
	public void damage (double strength) {
		_Health -= strength;
	}
}
