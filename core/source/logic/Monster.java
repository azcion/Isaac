package logic;



public class Monster {
	
	private static final String[]	__Monsters = {
		"Attack Fly", "Red Boom Fly"
	};
	private static final int[]  	__Health = {
		5, 20
	};
	private static final int[]  	__Speed = {
		1, 3
	};
	private static final boolean[]  __AI = {
		true, false
	};
	
	private final int    ID;
	private final String _Name;
	private int     _Health;
	private int     _Speed;
	private boolean _AI;
	private int     direction;
	
	public Monster(int ID) {
		this.ID = ID;
		_Name = __Monsters[ID];
		_Health = __Health[ID];
		_Speed = __Speed[ID];
		_AI = __AI[ID];
		direction = 1;
	}
	
	public int id () {
		return ID;
	}
	
	public boolean ai () {
		return _AI;
	}
	
	public String getName () {
		return _Name;
	}
	
	public int getHP () {
		return _Health;
	}
	
	public int getSpeed () {
		return _Speed;
	}
	
	public int getDirection () {
		return direction;
	}
	
	public boolean isDead () {
		return (_Health <= 0);
	}
	
	public void setDirection (int direction) {
		this.direction = direction;
	}
	
	public void damage (double strength) {
		_Health -= strength;
	}
}
