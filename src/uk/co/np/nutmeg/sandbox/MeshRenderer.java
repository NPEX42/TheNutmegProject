package uk.co.np.nutmeg.sandbox;

import org.joml.Matrix4f;

import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.Shader;
import uk.co.np.nutmeg.opengl.GLRenderer;
import uk.co.np.nutmeg.opengl.GLShader;

public class MeshRenderer {
	private static Renderer renderer;
	private static Shader shader;
	
	static {
		renderer = Renderer.GetRenderer();
		shader = GLShader.LoadShader("uk/co/np/nutmeg/res/BasicVS.glsl", "uk/co/np/nutmeg/res/BasicFS.glsl");
		renderer.SetActiveShader(shader);
	}
	
	public static void Submit(Mesh mesh) {
		renderer.Draw(mesh.getVao(), mesh.getTriangles(), new Matrix4f(), mesh.GetAlbedo());
	}
}
