package de.cdlemmi.vte;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import de.cdlemmi.vte.rendering.Renderer;

public class Game {

    private long window;
    private Renderer renderer;

    Game() {
        //init glfw stuff
        initWindow();

        //init opengl stuff
        renderer = new Renderer(window);
    }

    void start() {
        while (!glfwWindowShouldClose(window)) {


            renderer.render();

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

