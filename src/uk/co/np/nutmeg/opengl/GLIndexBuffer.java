package uk.co.np.nutmeg.opengl;

import static org.lwjgl.opengl.GL46.*;

import java.util.Arrays;

import uk.co.np.nutmeg.api.IndexBuffer;
import uk.co.np.nutmeg.api.Logger;

import static org.lwjgl.opengl.GL43.glInvalidateBufferData;

public class GLIndexBuffer extends IndexBuffer {

	private int ID, vertexCount;
	public void Bind() { glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID); }
	public void Delete() { glDeleteBuffers(ID); }
	public static IndexBuffer Create(int[] _Data) {
		IndexBuffer buffer = new GLIndexBuffer(glGenBuffers(), _Data.length);
		buffer.Bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, _Data, GL_STATIC_DRAW);
		System.err.println("[Nutmeg] Creating IBO, Data: "+Arrays.toString(_Data));
		return buffer;
	}
	
	private GLIndexBuffer(int _ID, int _VertexCount) {
		ID = _ID;
		vertexCount = _VertexCount;
		System.err.println("[Nutmeg] Creating IBO #"+ID);
	}
	
	public int GetVertexCount() { return vertexCount; }
	@Override
	public void Unbind() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Invalidate() {
		// TODO Auto-generated method stub
		
	}
	
}
