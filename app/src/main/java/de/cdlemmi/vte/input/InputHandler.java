package de.cdlemmi.vte.input;

import static org.lwjgl.glfw.GLFW.*;

import de.cdlemmi.vte.util.DebugPrinting;
import org.lwjgl.glfw.GLFWKeyCallbackI;


public class InputHandler implements GLFWKeyCallbackI, DebugPrinting {

    public boolean pressedForward;
    public boolean pressedBack;
    public boolean pressedRight;
    public boolean pressedLeft;
    public boolean pressedDown;
    public boolean pressedUp;



    public InputHandler(long window) {
        glfwSetKeyCallback(window, this);
    }


    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        switch(key) {
            case GLFW_KEY_W -> pressedForward = action != GLFW_RELEASE;
            case GLFW_KEY_S -> pressedBack = action != GLFW_RELEASE;
            case GLFW_KEY_A -> pressedLeft = action != GLFW_RELEASE;
            case GLFW_KEY_D -> pressedRight = action != GLFW_RELEASE;
            case GLFW_KEY_LEFT_SHIFT -> pressedDown = action != GLFW_RELEASE;
            case GLFW_KEY_SPACE -> pressedUp = action != GLFW_RELEASE;
        }
    }

    @Override
    public void printDebug() {
        System.out.println(String.format("currently pressed: forward=%B;back=%B;right=%B;left=%B;up=%B;down=%B",
                pressedForward, pressedBack, pressedRight, pressedLeft, pressedUp, pressedDown));
    }
}
