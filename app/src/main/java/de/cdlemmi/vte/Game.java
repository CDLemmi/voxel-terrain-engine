package de.cdlemmi.vte;

import de.cdlemmi.vte.input.InputHandler;
import de.cdlemmi.vte.util.DeltaTimer;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import de.cdlemmi.vte.rendering.Renderer;

public class Game {

    private long window;
    private Renderer renderer;
    private InputHandler inputHandler;

    Game() {
        //init glfw stuff
        initWindow();

        //init opengl stuff
        renderer = new Renderer(window);

        //init input handling
        inputHandler = new InputHandler(window);

    }

    void start() {
        DeltaTimer deltaTimer = new DeltaTimer(inputHandler);
        while (!glfwWindowShouldClose(window)) {
            double dt = deltaTimer.getDeltaTime();

            renderer.render();

            glfwPollEvents();
        }
    }

    private void initWindow() {
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        window = glfwCreateWindow(800, 800, "Voxel Terrain Engine", NULL, NULL);


        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
    }


}

