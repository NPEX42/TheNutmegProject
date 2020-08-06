package uk.co.np.nutmeg.api.events;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import uk.co.np.nutmeg.glfw.DisplayManager;

public class EventSystem {
	//Callbacks
	private static List<ICloseEvent> closeCB = new ArrayList<ICloseEvent>();
	private static List<IMouseMovedEvent> mouseMovedCB = new ArrayList<IMouseMovedEvent>();
	private static List<IMouseClickedEvent> mouseClickedCB = new ArrayList<IMouseClickedEvent>();
	private static List<IKeyPressedEvent> keyPressedCB = new ArrayList<IKeyPressedEvent>();
	
	static {
		long ID = DisplayManager.GetWindowID();
		GLFW.glfwSetWindowCloseCallback(ID, EventSystem::DispatchCloseEvent);
		GLFW.glfwSetCursorPosCallback(ID, EventSystem::DispatchMouseMovedEvent);
		GLFW.glfwSetMouseButtonCallback(ID, EventSystem::DispatchMouseClickedEvent);
		GLFW.glfwSetKeyCallback(ID, EventSystem::DispatchKeyPressedEvent);
	}
	
	//Event Registering
	public static void RegisterCloseEvent(ICloseEvent cb) { closeCB.add(cb); }
	public static void RegisterMouseMovedEvent(IMouseMovedEvent cb) { mouseMovedCB.add(cb); }
	public static void RegisterMouseClickedEvent(IMouseClickedEvent cb) { mouseClickedCB.add(cb); }
	public static void RegisterKeyEvent(IKeyPressedEvent cb) {  keyPressedCB.add(cb); }
	
	//Event Dispatching
	public static void DispatchCloseEvent(long windowID) {
		for(ICloseEvent cb : closeCB) {
			if(cb.handle()) break;
		}
	}
	
	public static void DispatchMouseMovedEvent(long windowID, double x, double y) {
		for(IMouseMovedEvent cb : mouseMovedCB) {
			if(cb.Handle((int)x, (int)y)) break;
		}
	}
	
	public static void DispatchMouseClickedEvent(long windowID, int btn, int action, int mods) {
		for(IMouseClickedEvent cb : mouseClickedCB) {
			if(cb.Handle(btn, action)) break;
		}
	}
	
	public static void DispatchKeyPressedEvent(long windowID, int key, int scancode, int action, int mods) {
		for(IKeyPressedEvent cb : keyPressedCB) {
			if(cb.KeyPressed(key, action)) break;
		}
	}

	
	
}
