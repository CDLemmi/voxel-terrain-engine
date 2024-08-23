#version 330 core

layout (location = 0) in vec3 pos;
layout (location = 1) in float aColor;

out vec3 color;

uniform mat4 proj;
uniform mat4 view;

void main() {
    color = vec3(0.0, aColor, 0.0);
    gl_Position = proj * view * vec4(pos, 1.0);
}
