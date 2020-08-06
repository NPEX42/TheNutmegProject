package uk.co.np.nutmeg.api.input;

import uk.co.np.nutmeg.api.events.EventSystem;
import static uk.co.np.nutmeg.api.input.InputCodes.*;

import java.util.Arrays;
public class Mouse {
	private static int mouseX, mouseY;
	private static boolean[] btnState = new boolean[8];
	
	static {
		EventSystem.RegisterMouseMovedEvent(Mouse::UpdateMousePos);
		EventSystem.RegisterMouseClickedEvent(Mouse::UpdateMouseButtons);
	}
	
	private static boolean UpdateMousePos(int x, int y) {
		mouseX = x;
		mouseY = y;
		return false;
	}
	
	private static boolean UpdateMouseButtons(int button, int action) {
		if(action == NM_PRESS || action == NM_HELD) {
			btnState[button] = true;
		} else {
			btnState[button] = false;
		}
		return false;
	}
	
	public static int[] GetIPos() {
		return new int[] {mouseX, mouseY};
	}
	
	public static float[] GetFPos() {
		return new float[] {mouseX, mouseY};
	}
	
	public static String String() {
		return "(MX: "+mouseX+", MY:"+mouseY+") Button States: "+Arrays.toString(btnState);
	}
	
	public static boolean IsButtonDown(int button) {
		return btnState[button];
	}
	
	public static void RunStaticInitializer() {}
}
