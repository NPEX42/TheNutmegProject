#version 330 core
in vec4 _LocalPosition;
in vec2 _UV0;
in vec2 _UV1;
in vec2 _UV2;
in vec2 _UV3;

out vec2 p_UV0;
out vec2 p_UV1;
out vec2 p_UV2;
out vec2 p_UV3;

uniform mat4 _MVP;

void main() {
	gl_Position = _MVP * _LocalPosition;
	p_UV0 = _UV0;
}