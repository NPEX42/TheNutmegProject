package uk.co.np.nutmeg.api.rendering;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;

import uk.co.np.nutmeg.opengl.GLTexture;

public abstract class Texture {
	protected int ID, width, height;
	
	private static Texture missing;

	private static Texture white;
	
	public abstract void Bind(int slot);
	public abstract void Unbind();
	public abstract void Delete();
	public abstract void Invalidate();
	public abstract void SaveToDisk(String filePath);
	
	public static Texture LoadTexture(String path) {
		return GLTexture.Load(path);
	}
	
	public static Texture GetMissing() {
		return missing; //TODO: Add Missing Texture
	}
	
	public static Texture GetWhite() {
		if(white == null) {
			ByteBuffer buffer = MemoryUtil.memAlloc(4);
			buffer.put((byte) 255);
			buffer.put((byte) 255);
			buffer.put((byte) 255);
			buffer.put((byte) 255);
			white = GLTexture.CreateRawBinary(buffer, 1, 1);
		} 
		return white;
	}
}
