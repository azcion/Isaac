package entities;

import resources.Vars;




public class RoomManager {
	
	Room[] rooms;
	private int roomCount;
	
	//private boolean[][] coordinates;
	private boolean[] tempC = {true, true, true, true};
	private float X, Y;
	
	public RoomManager (int roomCount) {
		this.roomCount = roomCount;
		rooms = new Room[roomCount];
		
		X = Vars.w;
		Y = Vars.h;
		RgenerateRooms();
	}
	
	private void RgenerateRooms () {
		for (int i = 0; i < roomCount; ++i) {
			if (tempC[i]) {
				rooms[i] = new Room(X, Y);
			}
			X += Vars.w;
		}
	}
	
}
