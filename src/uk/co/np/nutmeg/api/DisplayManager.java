package uk.co.np.nutmeg.api;

import static org.lwjgl.glfw.GLFW.*;
import static uk.co.np.nutmeg.api.Logger.*;

import org.lwjgl.opengl.GL;

import static uk.co.np.nutmeg.api.Errors.*;

public class DisplayManager {
	private static long windowID;
	public static void Open(int width, int height, String title) {
		if(!glfwInit()) { Logger.Error("Nutmeg/DisplayManager", "Unable To Init GLFW..."); System.exit(SOURCE_GLFW | NONE | ACTION_INITIALIZE); }
		windowID = glfwCreateWindow(width, height, title, 0, 0);
		if(windowID == 0) { Logger.Error("Nutmeg/DisplayManager", "Unable To Open A Window..."); System.exit(SOURCE_GLFW | ITEM_WINDOW | ACTION_CREATION); }
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
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
}
