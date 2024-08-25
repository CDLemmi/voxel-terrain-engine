package de.cdlemmi.vte;

import de.cdlemmi.vte.input.InputHandler;
import de.cdlemmi.vte.input.PlayerInputAction;
import de.cdlemmi.vte.util.DeltaTimer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import de.cdlemmi.vte.rendering.Renderer;
import org.joml.Matrix4f;

public class Game {

    private long window;
    private Renderer renderer;
    private InputHandler inputHandler;
    private Player player;

    Game() {
        //init glfw stuff
        initWindow();

        //init opengl stuff
        renderer = new Renderer(window);

        //init input handling
        inputHandler = new InputHandler(window);

        //init player
        player = new Player();

    }

    void start() {
        DeltaTimer deltaTimer = new DeltaTimer(10, inputHandler, player);
        while (!glfwWindowShouldClose(window)) {
            double dt = deltaTimer.getDeltaTime();

            PlayerInputAction inputAction = inputHandler.pollPlayerInputAction();
            player.handleInput(inputAction);
            player.doStep(dt);

            //var view = player.getView();
            var view = new Matrix4f().translate(0.0f,0.0f,-10.0f);

            renderer.render(view);

            glfwPollEvents();
        }
    }

    private void initWindow() {
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        window = glfwCreateWindow(800, 800, "Voxel Terrain Engine", NULL, NULL);


        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
    }


}

