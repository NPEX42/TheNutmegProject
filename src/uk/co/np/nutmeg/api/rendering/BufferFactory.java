package uk.co.np.nutmeg.api.rendering;

import uk.co.np.nutmeg.opengl.GLIndexBuffer;
import uk.co.np.nutmeg.opengl.GLVertexArray;
import uk.co.np.nutmeg.opengl.GLVertexBuffer;

public class BufferFactory {
	public static VertexArray NewVertexArray() {
		return GLVertexArray.Create();
	}
	
	public static VertexBuffer NewVertexBuffer(int index, float[] data, int size) {
		return GLVertexBuffer.Create(index, data, size);
	}
	
	public static IndexBuffer NewIndexBuffer(int[] triangles) {
		return GLIndexBuffer.Create(triangles);
	} 
}
