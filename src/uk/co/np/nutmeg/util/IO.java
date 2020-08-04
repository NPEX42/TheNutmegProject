package uk.co.np.nutmeg.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IO {
	public static String LoadString(String _FilePath) {
		try { 
			return LoadString(new FileInputStream(_FilePath));
		} catch(IOException ioex) {
			return null;
		}
	}
	
	public static String LoadString(InputStream _Stream) {
		if(_Stream == null) return null;
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(_Stream))) {
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			return buffer.toString();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String LoadStringJAR(String _ResourcePath) {
		return LoadString(GetResourceStream(_ResourcePath));
	}
	
	public static InputStream GetResourceStream(String _ResourcePath) {
		InputStream stream;
		stream = IO.class.getResourceAsStream(_ResourcePath);
		if(stream  != null) return stream;
		stream = IO.class.getClassLoader().getResourceAsStream(_ResourcePath);
		if(stream  != null) return stream;
		return null;
	}
}
