package uk.co.np.nutmeg.api;

public class Errors {
	public static final int
	SOURCE_GLFW    = 0x1000,
	SOURCE_OPENGL  = 0x2000,
	SOURCE_VULKAN  = 0x3000,
	SOURCE_NUTMEG  = 0x4000,
	SOURCE_METAL   = 0x5000,
	SOURCE_DRCTX   = 0x6000;
	
	public static final int
	ITEM_WINDOW = 0x0100,
	ITEM_BUFFER = 0x0200;
	
	public static final int
	ACTION_INITIALIZE = 0x0010,
	ACTION_CREATION   = 0x0020,
	ACTION_USE        = 0x0030,
	ACTION_DESTROY    = 0x0040;
	
	public static final int
	NONE = 0x0000;
	
	
}
