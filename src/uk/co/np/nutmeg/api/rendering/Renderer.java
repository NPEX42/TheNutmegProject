package uk.co.np.nutmeg.api;

public abstract class Renderer {
	public abstract void ClearColorBuffer(float r, float g, float b, float a);
	public abstract void Draw(VertexArray vao, IndexBuffer ibo);
}
