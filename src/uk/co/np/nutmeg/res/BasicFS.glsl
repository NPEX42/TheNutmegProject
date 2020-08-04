#version 330 core
in vec2 p_UV0;
in vec2 p_UV1;
in vec2 p_UV2;
in vec2 p_UV3;
out vec4 _SurfColor;

uniform sampler2D _MainTex; //Slot #0
uniform sampler2D _Custom0; //Slot #1
uniform sampler2D _Custom1; //Slot #2
uniform sampler2D _Custom2; //Slot #3
uniform sampler2D _Custom3; //Slot #4


void main() {
	_SurfColor = texture(_MainTex,p_UV0);
}