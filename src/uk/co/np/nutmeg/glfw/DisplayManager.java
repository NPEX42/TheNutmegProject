package uk.co.np.nutmeg.glfw;

import static org.lwjgl.glfw.GLFW.*;
import uk.co.np.nutmeg.api.Logger;

import org.lwjgl.opengl.GL;

public class DisplayManager {
	private static long windowID;
	public static void Open(int width, int height, String title) {
		if(!glfwInit()) { Logger.Error("Nutmeg/DisplayManager", "Unable To Init GLFW..."); System.exit(-1); }
		windowID = glfwCreateWindow(width, height, title, 0, 0);
		if(windowID == 0) { Logger.Error("Nutmeg/DisplayManager", "Unable To Open A Window..."); System.exit(-2); }
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		glfwSwapInterval(1);
		Logger.Log("Nutmeg/DisplayManager", "Created Window 0x"+String.format("%04x", windowID));
		
	}
	
	public static boolean Update() {
		glfwSwapBuffers(windowID);
		glfwPollEvents();
		return !glfwWindowShouldClose(windowID);
	}
	
	public static void Close() {
		glfwDestroyWindow(windowID);
		glfwTerminate();
	}
	
	public static long GetWindowID() { return windowID; }
	
	public static void RequestClose() {
		glfwSetWindowShouldClose(windowID, true);
	}
	
}
