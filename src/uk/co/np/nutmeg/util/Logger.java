package uk.co.np.nutmeg.util;

public class Logger {
	private static boolean debugMode;
	public static void Log(String location, String message) {
		System.out.println("["+location+"]: "+message);
	}
	
	public static void Error(String location, String message) {
		System.err.println("["+location+"]: "+message);
	}
	
	public static void Debug(String location, String message) {
		if(debugMode) System.err.println("["+location+"]: "+message);
	}
}
