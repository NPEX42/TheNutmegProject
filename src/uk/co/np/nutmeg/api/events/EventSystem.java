package uk.co.np.nutmeg.api.events;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import uk.co.np.nutmeg.glfw.DisplayManager;

public class EventSystem {
	//Callbacks
	private static List<ICloseEvent> closeCB = new ArrayList<ICloseEvent>();
	
	static {
		long ID = DisplayManager.GetWindowID();
		GLFW.glfwSetWindowCloseCallback(ID, EventSystem::DispatchCloseEvent);
	}
	
	//Event Registering
	public static void RegisterCloseEvent(ICloseEvent cb) { closeCB.add(cb); }
	
	//Event Dispatching
	public static void DispatchCloseEvent(long windowID) {
		for(ICloseEvent cb : closeCB) {
			if(cb.handle()) break;
		}
	}
	
	
}
