package uk.co.np.nutmeg.api;

public abstract class Application {
	public abstract void OnUpdate();
	public void OnDestroy() {};
	
	public void Start(int width, int height, String title) {
		DisplayManager.Open(width, height, title);
		while(DisplayManager.Update()) {
			OnUpdate();
		}
		DisplayManager.Close();
	}
}
