package resources;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Assets {
	
	
	
	private static Texture tBack;
	public static Sprite sBack, sBackFloor0, sBackFloor1;
	
	private static Texture tDoorHole;
	public static Sprite sDoorHole;
	
	public static boolean[][] rockMap;
	public static int[][] rockMapS;
	private static Texture tRockBasement;
	public static Sprite[] sRock01;
	
	private static Texture tTear;
	public static Sprite sTear;
	
	private static Texture tPlayer;
	public static Sprite[] sPlayer;
	
	private static Texture tAttackFly;
	public static Sprite[] sAttackFly;
	
	private static Texture tRedBoomFly;
	public static Sprite[] sRedBoomFly;
	
	public static boolean[][] monsterMap;
	public static int[][] monsterMapS;
	
	private static Texture tUI_Hearts;
	public static Sprite[] sUI_Hearts;  // fr, hr nr, s, fs, hs, fd, hd
	
	
	
	public static void load () throws IOException {
		load(System.currentTimeMillis());
	}
	
	public static void load (long seed) throws IOException {
		Vars.seed = new Seed(seed);
		
		// get backgrounds
		tBack = new Texture(Gdx.files.internal("scene/01_B.png"));
		sBack = new Sprite(tBack, 0, 0, 234, 156);
		sBackFloor0 = new Sprite(tBack, 52, 364, 182, 104);
		sBackFloor1 = new Sprite(tBack, 286, 364, 182, 104);
		sBack.flip(false, true);
		sBackFloor0.flip(false, true);
		sBackFloor1.flip(false, true);
		
		// get doors
		tDoorHole = new Texture(Gdx.files.internal("scene/01_D_H.png"));
		sDoorHole = new Sprite(tDoorHole);
		
		// get rocks
		tRockBasement = new Texture(Gdx.files.internal("scene/01_R.png"));
		sRock01 = new Sprite[9];
		
		for (int i = 0; i < 4; ++i) {
			sRock01[i] = new Sprite(tRockBasement, i*32, 0, 32, 32);
			sRock01[i].flip(false, true);
		}
		sRock01[4] = new Sprite(tRockBasement, 64, 32, 32, 32);
		sRock01[4].flip(false, true);
		
		// get tears
		tTear = new Texture(Gdx.files.internal("tear/tears.png"));
		sTear = new Sprite(tTear, 4*32, 0, 32, 32);
		
		// get players
		tPlayer = new Texture(Gdx.files.internal("char/isaac.png"));
		sPlayer = new Sprite[6];

		for (int i = 0; i < 6; ++i) {
			sPlayer[i] = new Sprite(tPlayer, i*32, 0, 32, 32);
			sPlayer[i].flip(false, true);
		}
		
		// get attack fly
		tAttackFly = new Texture(Gdx.files.internal("monster/010_fly.png"));
		sAttackFly = new Sprite[4];
		
		for (int i = 0; i < 4; ++i) {
			sAttackFly[i] = new Sprite(tAttackFly, i*32, 32, 32, 32);
			sAttackFly[i].flip(false, true);
		}
		
		// get red boom fly
		tRedBoomFly = new Texture(Gdx.files.internal("monster/020_redboomfly.png"));
		sRedBoomFly = new Sprite[2];
		
		sRedBoomFly[0] = new Sprite(tRedBoomFly, 0, 0, 32, 32);
		sRedBoomFly[1] = new Sprite(tRedBoomFly, 32, 0, 32, 32);
		sRedBoomFly[0].flip(false, true);
		sRedBoomFly[1].flip(false, true);
		
		// get UI hearts
		tUI_Hearts = new Texture(Gdx.files.internal("ui/hearts.png"));
		sUI_Hearts = new Sprite[8];
		
		for (int i = 0; i < 4; ++i) {
			sUI_Hearts[i] = new Sprite(tUI_Hearts, i*16, 0, 16, 16);
			sUI_Hearts[i+4] = new Sprite(tUI_Hearts, i*16, 16, 16, 16);
			sUI_Hearts[i].flip(false, true);
			sUI_Hearts[i+4].flip(false, true);
		}
		
		
		
		rockMap = new boolean[7][13];
		rockMapS = new int[7][13];
		monsterMap = new boolean[7][13];
		monsterMapS = new int[7][13];


		generateRockMap(Gdx.files.local("txt/01_01_R_bool.txt"));
		generateRockMapS(Gdx.files.local("txt/01_01_R_int.txt"));	
		generateMonsterMap(Gdx.files.local("txt/01_01_M_bool.txt"));
		generateMonsterMapS(Gdx.files.local("txt/01_01_M_int.txt"));
		
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
	
	/*public static void RgenerateRockMapS (boolean[][] rockMap) {
		for (int i = 0; i < 7; ++i) {
			for (int j = 0; j < 13; ++j) {
				if (rockMap[i][j]) {
					if (obstructed(i, j)) {
						rockMap[i][j] = false;
					}
					switch (Vars.seed.nI(4)) {
						case 0: rockMapS[i][j] = 0; break;
						case 1: rockMapS[i][j] = 1; break;
						case 2: rockMapS[i][j] = 2; break;
						case 3: rockMapS[i][j] = (Vars.seed.nD() > 0.95) ? 4 : 3; break;
					}
				}
			}
		}
	}*/
	
	public static void generateMonsterMap (FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				monsterMap[i][j] = "1".equals(tiles[i][j]);
			}
		}
	}
	
	public static void generateMonsterMapS (FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				monsterMapS[i][j] = Integer.valueOf(tiles[i][j]);
			}
		}
	}
	
	
}
