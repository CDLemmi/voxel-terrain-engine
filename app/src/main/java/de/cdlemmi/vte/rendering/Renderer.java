package de.cdlemmi.vte.rendering;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.NULL;



public class Renderer {
    private long window;

    public Renderer(long window) {
        this.window = window;

        createCapabilities();
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glBegin(GL_TRIANGLES);
        glColor3d(1.0, 0.1, 0.1);
        glVertex2d(0.0, 0.5);
        glVertex2d(0.5, -0.5);
        glVertex2d(-0.5, -0.5);
        glEnd();

        glfwSwapBuffers(window); // swap the color buffers
    }

}
