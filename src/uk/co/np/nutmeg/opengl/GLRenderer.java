package uk.co.np.nutmeg.opengl;

import org.lwjgl.opengl.GL46;

import uk.co.np.nutmeg.api.rendering.IndexBuffer;
import uk.co.np.nutmeg.api.rendering.Renderer;
import uk.co.np.nutmeg.api.rendering.Texture;
import uk.co.np.nutmeg.api.rendering.VertexArray;
import uk.co.np.nutmeg.util.Logger;

import static org.lwjgl.opengl.GL46.*;

import org.joml.Matrix4f;

public class GLRenderer extends Renderer {

	@Override
	public void ClearColorBuffer(float r, float g, float b, float a) {
		glClearColor(r,g,b,a);
		glClear(GL46.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void Draw(VertexArray vao, IndexBuffer ibo, Matrix4f mvp, Texture tex) {
		vao.Bind();
		if(activeShader != null) {
			activeShader.Bind();
			activeShader.UploadMat4f("_MVP", mvp);
			activeShader.UploadInt("_MainTex", 0);
			activeShader.UploadInt("_Custom0", 1);
			activeShader.UploadInt("_Custom1", 2);
			activeShader.UploadInt("_Custom2", 3);
			activeShader.UploadInt("_Custom3", 4);
			
			activeShader.UploadTexture("_MainTex", tex, 0);
		}
		Logger.Debug("Nutmeg/GLRenderer", "Drawing "+ibo.GetVertexCount()+" Vertices...");
		glDrawElements(GL_TRIANGLES, ibo.GetVertexCount(), GL_UNSIGNED_INT, 0);
		vao.Unbind();
	}

}
