package uk.co.np.nutmeg.opengl;
import static org.lwjgl.opengl.GL46.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.joml.*;

import java.util.*;

import uk.co.np.nutmeg.api.*;
public class GLShader {
	private int programID, vertexID, fragmentID;
	private Map<String, Integer> uniforms;
	private GLShader(int _ProgramID, int _VertexID, int _FragmentID) {
		programID = _ProgramID;
		fragmentID = _FragmentID;
		vertexID = _VertexID;
		uniforms = new HashMap<String, Integer>();
	}
	
	public static GLShader CompileShader(String _VertexSource, String _FragmentSource) {
		int programID, vertexID, fragmentID;
		programID = glCreateProgram();
		vertexID = glCreateShader(GL_VERTEX_SHADER);
		fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vertexID, _VertexSource);
		glShaderSource(fragmentID, _FragmentSource);
		
		glCompileShader(vertexID);
		if(glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("==== Error Compiling Vertex Shader ====");
			System.err.println(glGetShaderInfoLog(vertexID));
			System.err.println("=======================================");
			return null;
		}
		
		glCompileShader(fragmentID);
		if(glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("==== Error Compiling Fragment Shader ====");
			System.err.println(glGetShaderInfoLog(fragmentID));
			System.err.println("=========================================");
			return null;
		}
		
		glAttachShader(programID, vertexID);
		glAttachShader(programID, fragmentID);
		
		glLinkProgram(programID);
		if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("==== Error Linking Program ====");
			System.err.println(glGetProgramInfoLog(programID));
			System.err.println("===============================");
			return null;
		}
		
		return new GLShader(programID, vertexID, fragmentID);
	}
	
	public static GLShader LoadShader(String _VertexPath, String _FragmentPath) {
		String vertexSource = IO.LoadString(_VertexPath);
		String fragmentSource = IO.LoadString(_FragmentPath);
		if(vertexSource == null || fragmentSource == null) return LoadJAR(_VertexPath, _FragmentPath);
		return CompileShader(vertexSource, fragmentSource);
	}
	
	private static GLShader LoadJAR(String _VertexPath, String _FragmentPath) {
		String vertexSource = IO.LoadStringJAR(_VertexPath);
		String fragmentSource = IO.LoadStringJAR(_FragmentPath);
		if(vertexSource == null || fragmentSource == null) return null;
		return CompileShader(vertexSource, fragmentSource);
	}

	public void Bind() { glUseProgram(programID); }
	
	public void Delete() {
		glDeleteProgram(programID);
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
	}
	
	private int GetUniform(String _Name) {
		if(uniforms.containsKey(_Name)) {
			return uniforms.get(_Name);
		} else {
			int loc = glGetUniformLocation(programID, _Name);
			if(loc > -1 ) {
				uniforms.put(_Name, loc);
			}
			return loc;
		}
	}
	
	public void UploadFloat(String _Name, float _X) {
		Bind();
		glUniform1f(GetUniform(_Name), _X);
	}
	
	public void UploadVec2f(String _Name, float _X, float _Y) {
		Bind();
		glUniform2f(GetUniform(_Name), _X, _Y);
	}
	
	public void UploadVec3f(String _Name, float _X, float _Y, float _Z) {
		Bind();
		glUniform3f(GetUniform(_Name), _X, _Y, _Z);
	}
	
	public void UploadVec4f(String _Name, float _X, float _Y, float _Z, float _W) {
		Bind();
		glUniform4f(GetUniform(_Name), _X, _Y, _Z, _W);
	}
	
	public void UploadVec2f(String _Name, Vector2f _Vec) {
		Bind();
		glUniform2f(GetUniform(_Name), _Vec.x, _Vec.y);
	}
	
	public void UploadVec3f(String _Name, Vector3f _Vec) {
		Bind();
		glUniform3f(GetUniform(_Name), _Vec.x, _Vec.y, _Vec.z);
	}
	
	public void UploadVec4f(String _Name, Vector4f _Vec) {
		Bind();
		glUniform4f(GetUniform(_Name), _Vec.x, _Vec.y, _Vec.z, _Vec.w);
	}
	
	public void UploadVec4fArray(String _Name, Vector4f... _Vec) {
		for(int i = 0; i < _Vec.length; i++) {
			UploadVec4f(_Name + "["+i+"]", _Vec[i]);
		}
	}
	
	public void UploadMat2f(String _Name, Matrix2f _Mat) {
		Bind();
		glUniformMatrix2fv(GetUniform(_Name), false, _Mat.get(new float[4]));
	}
	public void UploadMat3f(String _Name, Matrix3f _Mat) {
		Bind();
		glUniformMatrix3fv(GetUniform(_Name), false, _Mat.get(new float[9]));
	}
	public void UploadMat4f(String _Name, Matrix4f _Mat) {
		Bind();
		glUniformMatrix4fv(GetUniform(_Name), false, _Mat.get(new float[16]));
	}
	
	public void UploadMat4fArray(String _Name, List<Matrix4f> _Mat) {
		for(int i = 0; i < _Mat.size(); i++) {
			UploadMat4f(_Name + "["+i+"]", _Mat.get(i));
		}
	}
	
	public void UploadFloats(String _Name, float[] _Values) {
		Bind();
		glUniform1fv(GetUniform(_Name), _Values);
	}
	
	public void UploadInts(String _Name, int[] _Values) {
		Bind();
		glUniform1iv(GetUniform(_Name), _Values);
	}
	
	public void UploadColor(String _Name, Color _C) {
		UploadVec4f(_Name,
				_C.getRed() / 255f,
				_C.getGreen() / 255f,
				_C.getBlue() / 255f,
				_C.getAlpha() / 255f);
	}
	
	public void UploadColorArray(String _Name, List<Color> _Colors) {
		for(int i = 0; i < _Colors.size(); i++) {
			UploadColor(_Name + "["+i+"]", _Colors.get(i));
		}
	}
	
	public void SetAttribute(int _Index, String _Name) {
		glBindAttribLocation(programID, _Index, _Name);
	}
	
	
}

