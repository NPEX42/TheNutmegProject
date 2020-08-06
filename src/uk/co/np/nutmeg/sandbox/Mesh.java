package uk.co.np.nutmeg.sandbox;

import uk.co.np.nutmeg.api.rendering.BufferFactory;
import uk.co.np.nutmeg.api.rendering.IRendererPrimitive;
import uk.co.np.nutmeg.api.rendering.IndexBuffer;
import uk.co.np.nutmeg.api.rendering.Texture;
import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.api.rendering.VertexBuffer;

public class Mesh implements IRendererPrimitive {
	private VertexArray vao;
	private VertexBuffer positions, texCoords;
	private IndexBuffer triangles;
	private Texture texture;
	
	public Mesh(float[] pos, float[] uv, int[] tris, Texture albedo) {
		vao = BufferFactory.NewVertexArray();
		positions = BufferFactory.NewVertexBuffer(0, pos, 3);
		texCoords = BufferFactory.NewVertexBuffer(1, uv, 2);
		triangles = BufferFactory.NewIndexBuffer(tris);
		texture = albedo;
	}
	
	public Mesh(float[] pos, float[] uv, int[] tris) {
		this(pos, uv, tris, Texture.GetWhite());
	}

	@Override
	public void Bind() { 
		vao.Bind();
	}

	@Override
	public void Unbind() {
		vao.Unbind();
	}

	@Override
	public void Delete() {
		positions.Delete();
		texCoords.Delete();
		triangles.Delete();
		vao.Delete();
	}

	@Override
	public void Invalidate() {}
	
	public int GetVertexCount() {
		return triangles.GetVertexCount();
	}

	public VertexArray getVao() {
		return vao;
	}

	public VertexBuffer getPositions() {
		return positions;
	}

	public VertexBuffer getTexCoords() {
		return texCoords;
	}

	public IndexBuffer getTriangles() {
		return triangles;
	}
	
	public Texture GetAlbedo() {
		return texture;
	}
	
	
	
}
