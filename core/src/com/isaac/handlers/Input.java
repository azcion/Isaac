package com.isaac.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class Input extends InputAdapter {
	
	@Override
	public boolean keyDown (int k) {
		switch (k) {
			case Keys.W:	Controls.setKey(Controls.W, true); break;
			case Keys.A:	Controls.setKey(Controls.A, true); break;
			case Keys.S:	Controls.setKey(Controls.S, true); break;
			case Keys.D:	Controls.setKey(Controls.D, true); break;
		}
		return true;
	}
	
	@Override
	public boolean keyUp (int k) {
		switch (k) {
			case Keys.W:	Controls.setKey(Controls.W, false); break;
			case Keys.A:	Controls.setKey(Controls.A, false); break;
			case Keys.S:	Controls.setKey(Controls.S, false); break;
			case Keys.D:	Controls.setKey(Controls.D, false); break;
		}
		return true;
	}
	
}
