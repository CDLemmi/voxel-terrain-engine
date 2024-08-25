#version 330 core

layout (location = 0) in vec3 pos;
layout (location = 1) in vec2 aColor;

out vec2 greenTone;

uniform mat4 proj;
uniform mat4 view;

void main() {
    greenTone = aColor;
    gl_Position = proj * view * vec4(pos.x, pos.y, pos.z, 1.0);
}
