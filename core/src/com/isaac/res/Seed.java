package com.isaac.res;

import java.util.Random;

class Seed {
	
	public static Random r;
	
	public Seed (long seed) {
		this.r = new Random();
		this.r.setSeed(seed);
	}
	
	public double nD () {
		return r.nextDouble();
	}
	
	public int nI (int i) {
		return r.nextInt(i);
	}
}