package uk.co.np.nutmeg.opengl;

import uk.co.np.nutmeg.api.rendering.Texture;
import java.io.*;
import uk.co.np.nutmeg.util.BufferUtils;
import uk.co.np.nutmeg.util.EndianManager;
import uk.co.np.nutmeg.util.EndianMode;
import uk.co.np.nutmeg.util.Logger;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL46.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;
public class GLTexture extends Texture {

	public void Bind(int slot) {
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, ID);
	}

	@Override
	public void Unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void Delete() {
		glDeleteTextures(ID);
	}

	public void Invalidate() {
		glInvalidateBufferData(ID);
	}
	
	private GLTexture(ByteBuffer pixels, int W, int H, EndianMode mode) {
		ID = glGenTextures();
		System.out.print("Building Texture #"+ID+" W: "+W+" H: "+H+" Pixels: "+BufferUtils.ToHexString(pixels)+" ("+pixels.capacity()+"B) ");
		if(mode == EndianMode.LITTLE_ENDIAN) System.out.println("Format: RGBA");
		if(mode == EndianMode.BIG_ENDIAN) System.out.println("Format: BGRA");
		if(mode == EndianMode.MIDDLE_ENDIAN) System.out.println("Format: ????");
		Bind(0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, W, H, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		Unbind();
	}
	
	private GLTexture(int id) {
		ID = id;
	}
	
	public static Texture Load(String path) {
		int[] 
			channels = new int[1],
			w = new int[1],
			h = new int[1];
		stbi_set_flip_vertically_on_load(true);
		ByteBuffer buffer = stbi_load(path, w, h,channels, 4);
		if(buffer == null) return null;
		return new GLTexture(buffer, w[0], h[0], EndianManager.GetNativeEndianMode());
	}
	
	public static Texture CreateRawBinary(ByteBuffer pixelData, int W, int H) {
		return new GLTexture(pixelData, W, H, EndianManager.GetNativeEndianMode());
	}
	
	public static Texture CreateFromColors(EndianMode mode, Color[] pixels, int W, int H) {
		ByteBuffer buffer = MemoryUtil.memAlloc(pixels.length * Integer.BYTES);
		for(Color pixel : pixels) {
			if(mode == EndianMode.BIG_ENDIAN) {
				buffer.put((byte) pixel.getRed());
				buffer.put((byte) pixel.getGreen());
				buffer.put((byte) pixel.getBlue());
				buffer.put((byte) pixel.getAlpha());
			}
			
			if(mode == EndianMode.LITTLE_ENDIAN) {
				buffer.put((byte) pixel.getAlpha());
				buffer.put((byte) pixel.getBlue());
				buffer.put((byte) pixel.getGreen());
				buffer.put((byte) pixel.getRed());
			}
		}
		return CreateRawBinary(buffer, W, H);
	}
	
	public static Texture CreateFromColors(Color[][] pixels) {
		int W = pixels[0].length;
		int H = pixels.length;
		Color[] pixels1d = new Color[W * H];
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				pixels1d[y * W + x] = pixels[y][x];
			}
		}
		return CreateFromColors(EndianManager.GetNativeEndianMode(), pixels1d, W, H);
	}
	
	public static Texture CreateFromBufferedImage(BufferedImage img) {
		int W = img.getWidth();
		int H = img.getHeight();
		
		Color[] pixels = new Color[W * H];
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				pixels[y * W + x] = new Color(img.getRGB(x, y));
			}
		}
		
		return CreateFromColors(EndianManager.GetNativeEndianMode(), pixels, W, H);
	}
	
	public void SaveToDisk(String filePath) {
		ByteBuffer buffer = MemoryUtil.memAlloc(width * height * 4 * Integer.BYTES);
		glGetnTexImage(GL_TEXTURE_2D, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		File file = new File(filePath);
		try { file.createNewFile(); } catch(Exception ex) {}
		try(DataOutputStream stream = new DataOutputStream(new FileOutputStream(filePath))) {
			for(int i = 0; i < buffer.capacity(); i++) {
				stream.write(buffer.get());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
