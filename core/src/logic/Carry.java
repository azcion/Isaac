package logic;



public class Carry {
	/*
	 *	Carried pickups are tools the player can use to interact with the game.
	 *	Bombs produce blast damage, Keys unlock doors and chests, and Coins
	 *	can be used to purchase items in the store room.
	 *	Items are special pickups that produce other effects, such as increase
	 *	player's Health, decrease his speed and more.
	 */
	
	protected int Bombs;
	protected int Keys;
	protected int Coins;
	protected int Item;
	
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