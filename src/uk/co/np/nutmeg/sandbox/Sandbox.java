package uk.co.np.nutmeg.sandbox;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.joml.Matrix4f;

import uk.co.np.nutmeg.api.Application;
import uk.co.np.nutmeg.api.events.Keyboard;
import uk.co.np.nutmeg.api.input.Mouse;
import uk.co.np.nutmeg.api.rendering.IndexBuffer;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.Texture;
import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.api.rendering.VertexBuffer;
import uk.co.np.nutmeg.glfw.DisplayManager;
import uk.co.np.nutmeg.util.Logger;

public class Sandbox extends Application {
	Renderer renderer;
	
	VertexArray vao;
	VertexBuffer vbo, uvbo;
	IndexBuffer ibo;
	
	Texture rgb;
	
	Mesh mesh;
	
	public void OnUpdate(float ts) {
		renderer.ClearColorBuffer(1, 0, 0, 1);
		MeshRenderer.Submit(mesh);
		Logger.Debug("Nutmeg/Sandbox","Time Per Frame: "+ts+" sec");
		
		Logger.Log("Sandbox", Keyboard.String());
	}
	
	public static void main(String[] args) {
		new Sandbox().Start(1080, 720, "Sandbox");
	}

	@Override
	public void OnCreate() {
		renderer = GetAppRenderer();
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
		
		
		
		rgb = LoadTexture("assets/rgb.png");
		
		mesh = new Mesh(pos, uvs, new int[] {0, 1, 2, 2, 3, 0}, rgb);
		
		Mouse.RunStaticInitializer();
	}
	
	@Override
	public boolean OnCloseEvent() {
		DisplayManager.RequestClose();
		return true;
	}
}
