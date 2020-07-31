package uk.co.np.nutmeg.api;

import uk.co.np.nutmeg.opengl.GLRenderer;

public abstract class Application {
	public abstract void OnUpdate(float ts);
	public abstract void OnCreate(        );
	public void OnDestroy() {};
	
	private Renderer renderer;
	
	public void Start(int width, int height, String title) {
		DisplayManager.Open(width, height, title);
		renderer = new GLRenderer();
		long tp1, tp2;
		float tpf = 1;
		OnCreate();
		while(DisplayManager.Update()) {
			tp1 = System.currentTimeMillis();
			OnUpdate(tpf);
			tp2 = System.currentTimeMillis();
			tpf = (tp2 - tp1) / 1000f;
			Logger.Log("", "TPF: "+(tp2 - tp1));
		}
		DisplayManager.Close();
	}
	
	protected Renderer GetAppRenderer() {
		return renderer;
	}
}
