package uk.co.np.nutmeg.api.input;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
public class InputCodes {
	public static final int
	NM_MOUSE_LEFT = GLFW_MOUSE_BUTTON_LEFT,
	NM_MOUSE_RIGHT = GLFW_MOUSE_BUTTON_RIGHT,
	NM_MOUSE_MIDDLE = GLFW_MOUSE_BUTTON_MIDDLE,
	NM_PRESS = GLFW_PRESS,
	NM_HELD = GLFW_REPEAT,
	NM_RELEASE = GLFW_RELEASE;
}
