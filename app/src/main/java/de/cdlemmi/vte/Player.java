package de.cdlemmi.vte;

import de.cdlemmi.vte.input.PlayerInputAction;
import de.cdlemmi.vte.util.DebugPrinting;
import de.cdlemmi.vte.util.Util;
import org.joml.Matrix4f;

public class Player implements DebugPrinting {

    final static double MOVE_SPEED = 3.0;
    final static double MOUSE_SENSITIVITY = 0.004;

    private double posX = -10;
    private double posY;
    private double posZ;
    private double angleVer = 0.0;
    private double angleHor = 0.3;

    private double velX;
    private double velY;
    private double velZ;

    private boolean pressedForward;
    private boolean pressedBack;
    private boolean pressedRight;
    private boolean pressedLeft;
    private boolean pressedUp;
    private boolean pressedDown;


    public Matrix4f getView() {
        var correction = new Matrix4f().rotate((float) (Math.PI/2.0), 0.0f, -1.0f, 0.0f)
                .rotate((float) (Math.PI/2.0), 1.0f, 0.0f, 0.0f);
        var trans = new Matrix4f()
                .translate((float) posX, (float) posY, (float) posZ);
        var rot = new Matrix4f()
                .rotate((float)angleVer, 0.0f, 1.0f, 0.0f)
                .rotate((float)angleHor, 0.0f, 0.0f, -1.0f);
        return correction.mul(rot.mul(trans));
    }

    public void handleInput(PlayerInputAction action) {
        pressedForward = action.forward();
        pressedBack = action.back();
        pressedRight = action.right();
        pressedLeft = action.left();
        pressedUp = action.up();
        pressedDown = action.down();
        angleHor += action.turnX() * MOUSE_SENSITIVITY;
        angleHor = Util.wrap(angleHor, -Math.PI, Math.PI);
        angleVer += action.turnY() * MOUSE_SENSITIVITY;
        angleVer = Util.limit(angleVer, -Math.PI/2, Math.PI/2);
    }

    public void doStep(double dt) {
        velX = 0;
        velY = 0;
        if(pressedForward) {
            velX += MOVE_SPEED * Math.cos(angleHor);
            velY += MOVE_SPEED * Math.sin(angleHor);
        }
        if(pressedBack) {
            velX -= MOVE_SPEED * Math.cos(angleHor);
            velY -= MOVE_SPEED * Math.sin(angleHor);
        }
        if(pressedRight) {
            velX -= MOVE_SPEED * Math.sin(angleHor);
            velY -= MOVE_SPEED * -Math.cos(angleHor);
        }
        if(pressedLeft) {
            velX += MOVE_SPEED * Math.sin(angleHor);
            velY += MOVE_SPEED * -Math.cos(angleHor);
        }

        velZ = 0;
        if(pressedUp) {
            velZ += MOVE_SPEED;
        }
        if(pressedDown) {
            velZ -= MOVE_SPEED;
        }

        move(dt);
    }

    private void move(double dt) {
        posX += velX * dt;
        posY += velY * dt;
        posZ += velZ * dt;
    }


    public Player() {
    }


    @Override
    public void printDebug() {
        System.out.printf("Player status: pos:{x=%.2f;y=%.2f;z=%.2f},\n", posX, posY, posZ);
        System.out.printf("               rot:{hor:%.2f;ver:%.2f},\n", angleHor, angleVer);
        System.out.printf("               vel:{x=%.2f;y=%.2f;z=%.2f}\n", velX, velY, velZ);
    }
}
