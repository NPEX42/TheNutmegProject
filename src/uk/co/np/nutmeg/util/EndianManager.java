package uk.co.np.nutmeg.util;

import java.nio.ByteOrder;

public class EndianManager {
	public static EndianMode GetNativeEndianMode() {
		if(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
			return EndianMode.BIG_ENDIAN;
		} else {
			return EndianMode.LITTLE_ENDIAN;
		}
	}
}
