package com.isaac;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	private static Seed r;
	
	private static int w = Gdx.graphics.getWidth();
	private static int h = Gdx.graphics.getHeight();
	
	public static Texture tBack;
	public static Sprite sBack, sBackFloor0, sBackFloor1;
	
	public static Texture tDoorHole;
	public static Sprite sDoorHole;
	
	public static boolean[][] rockMap;
	public static int[][] rockMapS;
	public static Texture tRockBasement;
	public static Sprite[] sRock01;
	
	public static void load () throws IOException {
		load(System.currentTimeMillis());
	}
	
	public static void load (long seed) throws IOException {
		r = new Seed(seed);
		
		tBack = new Texture(Gdx.files.internal("png/01_B.png"));
		sBack = new Sprite(tBack, 0, 0, 234, 156);
		sBackFloor0 = new Sprite(tBack, 52, 364, 182, 104);
		sBackFloor1 = new Sprite(tBack, 286, 364, 182, 104);
		sBack.flip(false, true);
		sBackFloor0.flip(false, true);
		sBackFloor1.flip(false, true);
		
		tDoorHole = new Texture(Gdx.files.internal("png/01_D_H.png"));
		sDoorHole = new Sprite(tDoorHole);
		
		tRockBasement = new Texture(Gdx.files.internal("png/01_R.png"));
		sRock01 = new Sprite[9];
		
		for (int i = 0; i < 4; ++i) {
			sRock01[i] = new Sprite(tRockBasement, i*32, 0, 32, 32);
			sRock01[i].flip(false, true);
		}
		sRock01[4] = new Sprite(tRockBasement, 64, 32, 32, 32);
		sRock01[4].flip(false, true);
		
		rockMap = new boolean[7][13];
		rockMapS = new int[7][13];


		generateRockMap(Gdx.files.local("txt/01_01.txt"));
		generateRockMapS(Gdx.files.local("txt/01_01_R.txt"));
		//RgenerateRockMapS(rockMap);
		
	}
	
	public static boolean obstructed (int i, int j) {
		return ((i==0 && j==6) || (i==6 && j==6) || 
				(i==3 && j==0) || (i==3 && j==12));
	}
	
	public static void generateRockMap (FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				rockMap[i][j] = "1".equals(tiles[i][j]);
			}
		}
	}
	
	public static void generateRockMapS (FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				rockMapS[i][j] = Integer.valueOf(tiles[i][j]);
			}
		}
	}
	
	public static void RgenerateRockMapS (boolean[][] rockMap) {
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (rockMap[i][j]) {
					if (obstructed(i, j)) {
						rockMap[i][j] = false;
					}
					switch (r.nI(4)) {
						case 0: rockMapS[i][j] = 0; break;
						case 1: rockMapS[i][j] = 1; break;
						case 2: rockMapS[i][j] = 2; break;
						case 3: rockMapS[i][j] = (r.nD() > 0.95) ? 4 : 3; break;
					}
				}
			}
		}
	}
	
	
}
