package uk.co.np.nutmeg.sandbox;
import java.awt.Color;

import org.joml.Matrix4f;

import uk.co.np.nutmeg.api.Application;
import uk.co.np.nutmeg.api.rendering.BufferFactory;
import uk.co.np.nutmeg.api.rendering.IndexBuffer;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.Texture;
import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.api.rendering.VertexBuffer;
import uk.co.np.nutmeg.glfw.DisplayManager;
import uk.co.np.nutmeg.opengl.GLTexture;
import uk.co.np.nutmeg.util.Logger;

public class Sandbox extends Application {
	Renderer renderer;
	
	VertexArray vao;
	VertexBuffer vbo, uvbo;
	IndexBuffer ibo;
	
	Texture texture;
	
	public void OnUpdate(float ts) {
		renderer.ClearColorBuffer(1, 0, 0, 1);
		renderer.Draw(vao, ibo, new Matrix4f().identity(), texture);
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
		
		float[] uvs = {
			 0, 0,
			 1, 0,
			 1, 1,
			 0, 1,
		};
		
		vbo = BufferFactory.NewVertexBuffer(0, pos, 3);
		uvbo = BufferFactory.NewVertexBuffer(1, uvs, 2);
		
		ibo = BufferFactory.NewIndexBuffer(new int[] {0,1,2,2,3,0});
		
		LoadShader("uk/co/np/nutmeg/res/BasicVS.glsl", "uk/co/np/nutmeg/res/BasicFS.glsl");
		
		texture = GLTexture.LoadTexture("assets/testAlbedo.png");
		texture.Bind(0);
	}
	
	@Override
	public boolean OnCloseEvent() {
		vao.Delete();
		vbo.Delete();
		uvbo.Delete();
		ibo.Delete();
		DisplayManager.RequestClose();
		return true;
	}
}
