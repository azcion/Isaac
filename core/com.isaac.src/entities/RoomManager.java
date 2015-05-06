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
		
		X = Vars.w/2;
		Y = Vars.h/2;
		RgenerateRooms();
	}
	
	private void RgenerateRooms () {
		for (int i = 0; i < roomCount; ++i) {
			if (tempC[i]) {
				rooms[i] = new Room(X+X*i, Y);
			}
			X += Vars.w;
		}
	}
	
}
