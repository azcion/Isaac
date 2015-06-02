package resources;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Assets {
	
	
	
	private static Texture tBack;
	public static Sprite sBack, sBackFloor0, sBackFloor1;
	
	private static Texture tDoorHole;
	public static Sprite sDoorHole;
	
	public static boolean[][][] rockMaps;
	public static int[][][] rockMapsS;
	
	public static boolean[][][] monsterMaps;
	public static int[][][] monsterMapsS;
	
	private static Texture tRock01;
	public static Sprite[] sRock01;
	
	private static Texture tTear;
	public static Sprite sTear;
	
	private static Texture tPlayer;
	public static Sprite[] sPlayer;
	
	private static Texture tAttackFly;
	public static Sprite[] sAttackFly;
	
	private static Texture tRedBoomFly;
	public static Sprite[] sRedBoomFly;
	
	private static Texture tUI_Hearts;
	public static Sprite[] sUI_Hearts;  // fr, hr nr, s, fs, hs, fd, hd
	
	
	
	public static void load () throws IOException {
		load(System.currentTimeMillis());
	}
	
	public static void load (long seed) throws IOException {
		Vars.seed = new Seed(seed);
		
		int maps = 2;
		
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
		tRock01 = new Texture(Gdx.files.internal("scene/01_R.png"));
		sRock01 = new Sprite[9];
		
		for (int i = 0; i < 4; ++i) {
			sRock01[i] = new Sprite(tRock01, i*32, 0, 32, 32);
			sRock01[i].flip(false, true);
		}
		sRock01[4] = new Sprite(tRock01, 64, 32, 32, 32);
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
		
		rockMaps = new boolean[maps][7][13];
		rockMapsS = new int[maps][7][13];
		monsterMaps = new boolean[maps][7][13];
		monsterMapsS = new int[maps][7][13];
		
		for (int i = 0; i < maps; ++i) {
			generateRockMap(i, Gdx.files.local("txt/01_0" + i +"_R_bool.txt"));
			generateRockMapS(i, Gdx.files.local("txt/01_0" + i +"_R_type.txt"));	
			generateMonsterMap(i, Gdx.files.local("txt/01_0" + i +"_M_bool.txt"));
			generateMonsterMapS(i, Gdx.files.local("txt/01_0" + i +"_M_type.txt"));
		}
		
	}
	
	public static boolean obstructed (int i, int j) {
		return ((i==0 && j==6) || (i==6 && j==6) || 
				(i==3 && j==0) || (i==3 && j==12));
	}
	
	public static void generateRockMap (int map, FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				rockMaps[map][i][j] = "1".equals(tiles[i][j]);
			}
		}
	}
	
	public static void generateRockMapS (int map, FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				if (rockMaps[map][i][j]) {
					rockMapsS[map][i][j] = Integer.valueOf(tiles[i][j]);
				}
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
	
	public static void generateMonsterMap (int map, FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				monsterMaps[map][i][j] = "1".equals(tiles[i][j]);
			}
		}
	}
	
	public static void generateMonsterMapS (int map, FileHandle file) throws IOException {
		String[] lines = file.readString().split("\\r?\\n");
		String[][] tiles = new String[7][13];
		
		for (int i = 0; i < 7; ++i) {
			tiles[i] = lines[i].split(" ");
			for (int j = 0; j < 13; ++j) {
				if (monsterMaps[map][i][j]) {
					monsterMapsS[map][i][j] = Integer.valueOf(tiles[i][j]);
				}
			}
		}
	}
	
	
}
