package uk.co.np.nutmeg.sandbox;

import org.lwjgl.opengl.GL46;

import uk.co.np.nutmeg.api.Application;

public class Sandbox extends Application {
	public void OnUpdate() {
		GL46.glClearColor(1, 0, 0, 1);
		GL46.glClear(GL46.GL_COLOR_BUFFER_BIT);
	}
	
	public static void main(String[] args) {
		new Sandbox().Start(1080, 720, "Sandbox");
	}
}
