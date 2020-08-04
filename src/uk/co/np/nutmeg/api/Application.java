package uk.co.np.nutmeg.api;

import uk.co.np.nutmeg.api.events.EventSystem;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.glfw.DisplayManager;
import uk.co.np.nutmeg.opengl.GLRenderer;
import uk.co.np.nutmeg.util.Logger;

public abstract class Application {
	public abstract void OnUpdate(float ts);
	public abstract void OnCreate(        );
	public void OnDestroy() {};
	
	private Renderer renderer;
	
	public final void Start(int width, int height, String title) {
		DisplayManager.Open(width, height, title);
		EventSystem.RegisterCloseEvent(this::OnCloseEvent);
		renderer = new GLRenderer();
		long tp1, tp2;
		float tpf = 1;
		OnCreate();
		while(DisplayManager.Update()) {
			tp1 = System.currentTimeMillis();
			OnUpdate(tpf);
			tp2 = System.currentTimeMillis();
			tpf = (tp2 - tp1) / 1000f;
			Logger.Debug("Application/Start/UpdateLoop", "TPF: "+(tp2 - tp1)+"ms");
		}
		OnDestroy();
		DisplayManager.Close();
	}
	
	protected final Renderer GetAppRenderer() {
		return renderer;
	}
	
	public boolean OnCloseEvent() {
		Logger.Debug("Application/OnCloseEvent", "Window Closed");
		return false;
	}
}
