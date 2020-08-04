package uk.co.np.nutmeg.api.rendering;

public abstract class Texture {
	protected int ID, width, height;
	
	public abstract void Bind(int slot);
	public abstract void Unbind();
	public abstract void Delete();
	public abstract void Invalidate();
}
