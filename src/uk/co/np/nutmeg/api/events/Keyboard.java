package uk.co.np.nutmeg.api.events;

import static uk.co.np.nutmeg.api.input.InputCodes.NM_HELD;
import static uk.co.np.nutmeg.api.input.InputCodes.NM_PRESS;

public class Keyboard {
	static {
		EventSystem.RegisterKeyEvent(Keyboard::OnKeyEvent);
	}
	
	private static boolean[] keyStates = new boolean[65536];
	
	private static boolean OnKeyEvent(int key, int action) {
		if(action == NM_PRESS || action == NM_HELD) {
			keyStates[key] = true;
		} else {
			keyStates[key] = false;
		}
		return false;
	}
	
	public static boolean IsButtonDown(int key) {
		return keyStates[key];
	}
	
	public static String String() {
		String out = "Keys: ";
		for(int i = 0; i < 128; i++) {
			if(keyStates[i]) {
				out += ""+(char)i;
			}
		}
		return out;
	}
}
