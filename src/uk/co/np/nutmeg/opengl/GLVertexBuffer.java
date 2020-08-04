package uk.co.np.nutmeg.opengl;
import static org.lwjgl.opengl.GL46.*;

import java.util.Arrays;

import uk.co.np.nutmeg.api.rendering.VertexBuffer;
import uk.co.np.nutmeg.util.Logger;
public class GLVertexBuffer extends VertexBuffer {

	private int ID;
	private int index;
	public void Bind() { glBindBuffer(GL_ARRAY_BUFFER, ID); }
	public void Delete() { glDeleteBuffers(ID); }
	public static VertexBuffer Create(int _Index, float[] _Data, int _Size, int _Stride, int _Offset) {
		if(_Data.length % _Size != 0) { System.err.println("ERROR | VERTEX BUFFER LENGTH IS NOT A MULTIPLE OF SIZE..."); }
		VertexBuffer buffer = new GLVertexBuffer(glGenBuffers());
		buffer.Bind();
		buffer.SetFloatData(_Data);
		buffer.LinkToVAO(_Index, _Size, GL_FLOAT, _Stride, _Offset);
		glEnableVertexAttribArray(_Index);
		Logger.Debug("GLVertexBuffer/CONSTRUCTOR", "Created VBO #"+buffer.getID()+" (Data: "+Arrays.toString(_Data)+")");
		return buffer;
	}
	
	public void SetFloatData(float[] data) {
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
	}
	
	public static VertexBuffer Create(int _Index, float[] _Data, int _Size) {
		return Create(_Index, _Data, _Size, 0, 0);
	}
	
	private GLVertexBuffer(int _ID) {
		ID = _ID;
		//Logger.Debug("GLVertexBuffer/CONSTRUCTOR", "Created VBO #"+ID);
	}
	@Override
	public void Unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0); 
	}
	@Override
	public void Invalidate() {
		glInvalidateBufferData(ID);
	}
	@Override
	public void LinkToVAO(int index, int size, int format, int stride, int offset) {
		glVertexAttribPointer(index, size, format, false, stride, offset);
	}

	
	
	

}
