package uk.co.np.nutmeg.api.rendering;

import static org.lwjgl.opengl.GL46.*;

public abstract class IndexBuffer implements IBuffer {
	protected int ID, vertexCount;

	public int GetVertexCount() {
		return vertexCount;
	}
	
	public int GetID() { return ID; }
	
	
}
