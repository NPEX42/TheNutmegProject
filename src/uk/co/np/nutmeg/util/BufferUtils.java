package uk.co.np.nutmeg.util;

import java.nio.*;

public class BufferUtils {

	public static String ToHexString(ByteBuffer buffer) {
		String out = "[";
		for(int i = 0; i < buffer.capacity() && i < 128; i++) {
			byte b = buffer.get(i);
			out += String.format("$%02x, ", b);
		}
		out = out.substring(0,out.length() - 2);
		return out + "]";
	}
	
	public static String ToHexString(ShortBuffer buffer) {
		String out = "[";
		for(int i = 0; i < buffer.capacity() && i < 128; i++) {
			short b = buffer.get(i);
			out += String.format("$%04x, ", b);
		}
		out = out.substring(0,out.length() - 2);
		return out + "]";
	}
	
	public static String ToHexString(IntBuffer buffer) {
		String out = "[";
		for(int i = 0; i < buffer.capacity() && i < 128; i++) {
			int b = buffer.get(i);
			out += String.format("$%08x, ", b);
		}
		out = out.substring(0,out.length() - 2);
		return out + "]";
	}
	
}
