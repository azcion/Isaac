package logic;

import java.util.HashMap;
import java.util.Map;



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
	
	private static final Map<Integer, String> Cards = new HashMap<>();
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
	
	public void useCard (Player P, int ID) {
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
			case 0x2A:	P.setHealth(0x0A, P.getFullHealth());
		}
	}
}