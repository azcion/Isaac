package resources;

import java.util.Random;


public class Seed {
	
	public Random r;
	
	public Seed (long seed) {
		r = new Random();
		r.setSeed(seed);
	}
	
	public double nD () {
		return r.nextDouble();
	}
	
	public int nI (int i) {
		return r.nextInt(i);
	}
}