package uk.co.np.nutmeg.api;

public class Logger {
	public static void Log(String location, String message) {
		System.out.println("["+location+"]: "+message);
	}
	
	public static void Error(String location, String message) {
		System.err.println("["+location+"]: "+message);
	}
	
	public static void Debug(String location, String message) {
		System.err.println("["+location+"]: "+message);
	}
}
