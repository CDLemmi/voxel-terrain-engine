package de.cdlemmi.vte.rendering;


import de.cdlemmi.vte.rendering.abstractions.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.*;


public class Renderer {
    private long window;

    Shader shader;
    VertexArray vertexArray;
    VertexBuffer vb;

    public Renderer(long window) {
        this.window = window;

        createCapabilities();

        initDebugCallback();

        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        shader = new Shader();
        vb = new VertexBuffer(new float[] {
                 0.f,  0.5f, 1.0f, 0.0f, 0.0f,
                 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
                -0.5f, -0.5f, 0.0f, 0.0f, 1.0f,

        });
        VertexBufferLayout layout = new VertexBufferLayout();
        layout.pushFloat(2);
        layout.pushFloat(3);
        vertexArray = new VertexArray(vb, layout);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        shader.bind();
        vertexArray.bind();

        glDrawArrays(GL_TRIANGLES, 0, 3);

        /*glBegin(GL_TRIANGLES);
        glColor3d(1.0, 0.1, 0.1);
        glVertex2d(0.0, 0.5);
        glVertex2d(0.5, -0.5);
        glVertex2d(-0.5, -0.5);
        glEnd();*/

        glfwSwapBuffers(window); // swap the color buffers
    }

    private void initDebugCallback() {
        glDebugMessageCallback((source, type, id, severity, length, message, userParam) -> {
            System.out.println("OpenGL debug callback message: ");
            System.out.println("source: " + source);
            System.out.println("type: " + type);
            System.out.println("id: " + id);
            System.out.println("severity" + severity);
            String msg = memUTF8(message, length);
            System.out.println("message: " + msg);
        }, 0);
    }

}
