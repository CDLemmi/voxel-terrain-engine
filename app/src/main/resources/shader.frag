#version 330 core


in vec2 greenTone;
out vec4 FragColor;



void main()
{
    FragColor = vec4(greenTone.y, greenTone.x, 0.0, 0.5);
}
