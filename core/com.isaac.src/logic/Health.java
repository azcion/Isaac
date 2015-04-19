package logic;


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