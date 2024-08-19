package de.cdlemmi.vte;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private long window;

    Game() {
        //init glfw stuff
        initWindow();

        //init opengl stuff
        createCapabilities();
    }

    void start() {
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            glfwSwapBuffers(window); // swap the color buffers

            glfwPollEvents();
        }
    }

    private void initWindow() {
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        window = glfwCreateWindow(800, 800, "Voxel Terrain Engine", NULL, NULL);


        glfwSwapInterval(0);
        glfwMakeContextCurrent(window);
    }


}

