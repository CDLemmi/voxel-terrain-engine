#version 330 core

layout (location = 0) in vec3 pos;
layout (location = 1) in float aColor;

out float greenTone;

uniform mat4 proj;
uniform mat4 view;

void main() {
    greenTone = aColor;
    gl_Position = proj * view * vec4(pos.x, pos.y, 0.5, 1.0);
}
