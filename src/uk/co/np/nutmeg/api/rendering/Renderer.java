package uk.co.np.nutmeg.api.rendering;

public abstract class Renderer {
	
	protected Shader activeShader;
	
	public abstract void ClearColorBuffer(float r, float g, float b, float a);
	public abstract void Draw(VertexArray vao, IndexBuffer ibo);
	
	public void SetActiveShader(Shader shader) {
		activeShader = shader;
	}
}
