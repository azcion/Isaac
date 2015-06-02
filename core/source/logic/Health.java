package logic;


public class Health {
	/*
	 * 	There are four different types of health, each with their own pickups.
	 *	The order in which hearts are placed is: Red, Soul, Black.
	 *	Hearts are generally lost from right to left.
	 *	The character can hold a maximum of twelve hearts.
	 */
	
	protected int Container;
	protected int RedHeart;
	protected int SoulHeart;
	protected int DarkHeart;
	protected int[] Order;
	private static final int[] OrderID = {0x09, 0x0A, 0x0B, 0x0C};
	
	public Health (int ch, int cont) {
		Order = new int[4];
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
			case 0x09:	Container += val; break;
			case 0x0A:	RedHeart  += val; break;
			case 0x0B:	SoulHeart += val; break;
			case 0x0C:	DarkHeart += val; break;
		}
		trimHealth();
		setOrder();
	}
	
	public void setOrder () {
		Order[0] = Container;
		Order[1] = RedHeart;
		Order[2] = SoulHeart;
		Order[3] = DarkHeart;
	}
	
	public int[] getOrder () {
		return Order;
	}
	
	public int getOrderID () {
		if (Order[3] > 0) return OrderID[3];
		if (Order[2] > 0) return OrderID[2];
		return OrderID[1];
	}
	
	public int fullHealth () {
		return this.RedHeart + this.SoulHeart + this.DarkHeart;
	}
	
	public boolean isDead () {
		return fullHealth() == 0;
	}
	
	public void trimHealth () {
		while (fullHealth() > 10) {
			if (this.Order[3] > 0) {--this.DarkHeart;
			} else
			if (this.Order[2] > 0) {--this.SoulHeart;
			} else
			if (this.Order[0] > 10) {--this.Container;
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