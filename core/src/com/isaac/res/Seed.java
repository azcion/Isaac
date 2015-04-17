package com.isaac.res;

import java.util.Random;

class Seed {
	
	public static Random r;
	
	public Seed (long seed) {
		Seed.r = new Random();
		Seed.r.setSeed(seed);
	}
	
	public double nD () {
		return r.nextDouble();
	}
	
	public int nI (int i) {
		return r.nextInt(i);
	}
}