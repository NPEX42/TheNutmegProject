package uk.co.np.nutmeg.api.rendering;

import org.joml.Matrix4f;

public abstract class Renderer {
	
	protected Shader activeShader;
	
	public abstract void ClearColorBuffer(float r, float g, float b, float a);
	public abstract void Draw(VertexArray vao, IndexBuffer ibo, Matrix4f mvp, Texture tex);
	
	public void SetActiveShader(Shader shader) {
		activeShader = shader;
	}
}
