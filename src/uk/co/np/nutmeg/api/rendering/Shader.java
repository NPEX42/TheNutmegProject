package uk.co.np.nutmeg.api.rendering;

import java.awt.Color;

import org.joml.Matrix2f;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import uk.co.np.nutmeg.opengl.GLShader;

public interface Shader extends IRendererPrimitive {
	
	public void SetAttribute(int _Index, String _Name);
	
	public void UploadFloat(String _Name, float _X);
	public void UploadVec2f(String _Name, float _X, float _Y);
	public void UploadVec3f(String _Name, float _X, float _Y, float _Z);
	public void UploadVec4f(String _Name, float _X, float _Y, float _Z, float _W);
	
	public void UploadVec2f(String _Name, Vector2f _Vec);
	public void UploadVec3f(String _Name, Vector3f _Vec);
	public void UploadVec4f(String _Name, Vector4f _Vec);
	
	public void UploadMat2f(String _Name, Matrix2f _Mat);
	public void UploadMat3f(String _Name, Matrix3f _Mat);
	public void UploadMat4f(String _Name, Matrix4f _Mat);
	
	public void UploadFloats(String _Name, float[] _Values);
	
	public void UploadInts(String _Name, int[] _Values);
	public void UploadInt(String name, int value);
	
	public void UploadTexture(String name, Texture tex, int slot);
	
	public void UploadColor(String _Name, Color _C);
}
