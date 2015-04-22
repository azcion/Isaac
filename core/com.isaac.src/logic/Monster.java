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
		this._Name = __Monsters[ID];
		this._Health = __Health[ID];
		this._Speed = __Speed[ID];
	}
	
	public String getName () {
		return this._Name;
	}
	
	public int getHP () {
		return this._Health;
	}
	
	public double getSpeed () {
		return this._Speed;
	}
}
