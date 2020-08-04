package uk.co.np.nutmeg.api.rendering;

public abstract class VertexBuffer implements IBuffer{
	protected int ID;
	public abstract void SetFloatData(float[] data);
	public abstract void LinkToVAO(int index, int size, int format, int stride, int offset);
	
	public int getID() {
		return ID;
	}
}
