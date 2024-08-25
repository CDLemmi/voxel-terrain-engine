#version 330 core


in float greenTone;
out vec4 FragColor;



void main()
{
    FragColor = vec4(0.0, greenTone, 0.0, 0.5);
}
