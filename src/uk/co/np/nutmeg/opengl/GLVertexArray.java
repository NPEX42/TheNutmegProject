package uk.co.np.nutmeg.opengl;
import static org.lwjgl.opengl.GL46.*;

import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.util.Logger;
public class GLVertexArray extends VertexArray {
	
	private int ID;
	public void Bind  () { glBindVertexArray(ID); }
	public void Unbind() { glBindVertexArray(0);  }
	private GLVertexArray() {
		ID = glGenVertexArrays();
		Logger.Debug("GLVertexArray/CONSTRUCTOR", "Created VAO #"+ID);
	}
	public static VertexArray Create() {
		VertexArray array = new GLVertexArray();
		array.Bind();
		return array;
	}
	public void Delete() { glDeleteVertexArrays(ID); }
	@Override
	public void Invalidate() {}

}
