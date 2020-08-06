package uk.co.np.nutmeg.api;

import uk.co.np.nutmeg.api.events.EventSystem;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.Shader;
import uk.co.np.nutmeg.api.rendering.Texture;
import uk.co.np.nutmeg.glfw.DisplayManager;
import uk.co.np.nutmeg.opengl.GLRenderer;
import uk.co.np.nutmeg.opengl.GLShader;
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
	
	public void LoadShader(String vsPath, String fsPath) {
		Shader shader = GLShader.LoadShader(vsPath, fsPath);
		if(shader == null) { Logger.Error("Application/LoadShader", "Unable To Load Shader... ["+vsPath+", "+fsPath+"]"); return; }
		shader.SetAttribute(0, "_LocalPosition");
		shader.SetAttribute(1, "_UV0");
		shader.SetAttribute(2, "_UV1");
		shader.SetAttribute(3, "_UV2");
		shader.SetAttribute(4, "_UV3");
		renderer.SetActiveShader(shader);
	}
	
	public void SetShaderFloat(String name, float value) {
		if(renderer.GetActiveShader() == null) return;
		renderer.GetActiveShader().UploadFloat(name, value);
	}
	
	public void SetShaderInt(String name, int value) {
		if(renderer.GetActiveShader() == null) return;
		renderer.GetActiveShader().UploadInt(name, value);
	}
	
	public void SetShaderTexture(String name, Texture tex, int slot) {
		if(renderer.GetActiveShader() == null) return;
		renderer.GetActiveShader().UploadTexture(name, tex, slot);
	}
	
	public Texture LoadTexture(String path) {
		return Texture.LoadTexture(path);
	}
	
	public void SaveToDisk(Texture tex, String path) {
		tex.SaveToDisk(path);
	}
}
