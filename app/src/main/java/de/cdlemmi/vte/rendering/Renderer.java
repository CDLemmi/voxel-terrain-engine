package de.cdlemmi.vte.rendering;


import de.cdlemmi.vte.rendering.abstractions.*;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.*;


public class Renderer {
    private long window;

    private Shader shader;
    private Matrix4f proj;

    ChunkRenderer chunkRenderer;

    public Renderer(long window) {
        this.window = window;

        createCapabilities();


        initDebugCallback();

        chunkRenderer = new ChunkRenderer();

        glEnable(GL_DEPTH_TEST);
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        shader = new Shader();
        proj = new Matrix4f().perspective(1.0f, 1.0f, 0.1f, 40.0f);
        //proj = new Matrix4f().translate(0.3f, 0.1f, -1.6f);
        //proj = new Matrix4f().frustum(-1.0f, 1.0f, -1.0f, 1.0f, 0.1f, 0.8f);
        shader.bind();
        shader.setUniform(proj, "proj");
    }

    public void render(Matrix4f view) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        shader.bind();


        shader.setUniform(view, "view");

        chunkRenderer.renderChunks();

        //vertexArray.bind();

        //glDrawArrays(GL_TRIANGLES, 0, 3);

        /*glBegin(GL_TRIANGLES);
        glColor3d(1.0, 0.1, 0.1);
        glVertex2d(0.0, 0.5);
        glVertex2d(0.5, -0.5);
        glVertex2d(-0.5, -0.5);
        glEnd();*/

        glfwSwapBuffers(window); // swap the color buffers
    }

    private void initDebugCallback() {
        glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);
        glDebugMessageCallback((source, type, id, severity, length, message, userParam) -> {
            System.out.println("OpenGL debug callback message: ");
            System.out.println("source: " + source);
            System.out.println("type: " + type);
            System.out.println("id: " + id);
            System.out.println("severity" + severity);
            String msg = memUTF8(message, length);
            System.out.println("message: " + msg);
            if(severity != GL_DEBUG_SEVERITY_NOTIFICATION) {
                throw new RuntimeException();
            }
        }, 0);
    }



}
