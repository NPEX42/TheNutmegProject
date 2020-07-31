package uk.co.np.nutmeg.opengl;

import org.lwjgl.opengl.GL46;

import uk.co.np.nutmeg.api.IndexBuffer;
import uk.co.np.nutmeg.api.Logger;
import uk.co.np.nutmeg.api.Renderer;
import uk.co.np.nutmeg.api.VertexArray;

import static org.lwjgl.opengl.GL46.*;

public class GLRenderer extends Renderer {

	@Override
	public void ClearColorBuffer(float r, float g, float b, float a) {
		glClearColor(r,g,b,a);
		glClear(GL46.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void Draw(VertexArray vao, IndexBuffer ibo) {
		vao.Bind();
		Logger.Log("Nutmeg/GLRenderer", "Drawing "+ibo.GetVertexCount()+" Vertices...");
		glDrawElements(GL_TRIANGLES, ibo.GetVertexCount(), GL_UNSIGNED_INT, 0);
		vao.Unbind();
	}

}
