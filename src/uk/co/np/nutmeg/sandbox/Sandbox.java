package uk.co.np.nutmeg.sandbox;
import uk.co.np.nutmeg.api.Application;
import uk.co.np.nutmeg.api.rendering.BufferFactory;
import uk.co.np.nutmeg.api.rendering.IndexBuffer;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.api.rendering.VertexBuffer;
import uk.co.np.nutmeg.glfw.DisplayManager;
import uk.co.np.nutmeg.util.Logger;

public class Sandbox extends Application {
	Renderer renderer;
	
	VertexArray vao;
	VertexBuffer vbo;
	IndexBuffer ibo;
	
	public void OnUpdate(float ts) {
		renderer.ClearColorBuffer(1, 0, 0, 1);
		renderer.Draw(vao, ibo);
		Logger.Debug("Nutmeg/Sandbox","Time Per Frame: "+ts+" sec");
	}
	
	public static void main(String[] args) {
		new Sandbox().Start(1080, 720, "Sandbox");
	}

	@Override
	public void OnCreate() {
		renderer = GetAppRenderer();
		
		vao = BufferFactory.NewVertexArray();
		float[] pos = {
				-1, -1, 0,
				 1, -1, 0,
				 1,  1, 0,
				-1,  1, 0
		};
		vbo = BufferFactory.NewVertexBuffer(0, pos, 3);
		
		ibo = BufferFactory.NewIndexBuffer(new int[] {0,1,2,2,3,0});
	}
	
	@Override
	public boolean OnCloseEvent() {
		DisplayManager.RequestClose();
		return true;
	}
}
