package uk.co.np.nutmeg.api;

import static org.lwjgl.opengl.GL46.*;

import uk.co.np.nutmeg.opengl.IBuffer;

public abstract class IndexBuffer implements IBuffer {
	protected int ID, vertexCount;

	public int GetVertexCount() {
		return vertexCount;
	}
	
	
}
