package de.cdlemmi.vte.input;

import static org.lwjgl.glfw.GLFW.*;

import de.cdlemmi.vte.util.DebugPrinting;


public class InputHandler implements DebugPrinting {

    private boolean pressedForward;
    private boolean pressedBack;
    private boolean pressedRight;
    private boolean pressedLeft;
    private boolean pressedDown;
    private boolean pressedUp;

    private double turnX;
    private double turnY;

    double mouseX;
    double mouseY;

    private boolean turning;

    public InputHandler(long window) {
        glfwSetKeyCallback(window, (w, k, s, a, m) -> handleKey(k, s, a, m));
        glfwSetCursorPosCallback(window, (w, x, y) -> handleCursorPos(x, y));
        glfwSetMouseButtonCallback(window, (w, b, a, m) -> handleMouseButton(w, b, a, m));
    }

    public PlayerInputAction pollPlayerInputAction() {
        var action = new PlayerInputAction(pressedForward, pressedBack, pressedLeft, pressedRight, pressedUp, pressedDown, turnX, turnY);
        turnX = 0;
        turnY = 0;
        return action;
    }


    public void handleKey(int key, int scancode, int action, int mods) {
        switch(key) {
            case GLFW_KEY_W -> pressedForward = action != GLFW_RELEASE;
            case GLFW_KEY_S -> pressedBack = action != GLFW_RELEASE;
            case GLFW_KEY_A -> pressedLeft = action != GLFW_RELEASE;
            case GLFW_KEY_D -> pressedRight = action != GLFW_RELEASE;
            case GLFW_KEY_LEFT_SHIFT -> pressedDown = action != GLFW_RELEASE;
            case GLFW_KEY_SPACE -> pressedUp = action != GLFW_RELEASE;
        }
    }

    public void handleCursorPos(double x, double y) {
        if(turning) {
            turnX += x - mouseX;
            turnY -= y - mouseY;
        } else {
            turnX = 0;
            turnY = 0;
        }
        mouseX = x;
        mouseY = y;
    }

    public void handleMouseButton(long window, int button, int action, int mods) {
        if(button == GLFW_MOUSE_BUTTON_MIDDLE && action == GLFW_PRESS) {
            if(!turning) {
                turning = true;
                glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
            } else {
                turning = false;
                glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
            }
        }
    }


    @Override
    public void printDebug() {
        System.out.println("player input: " + pollPlayerInputAction());
    }


}
