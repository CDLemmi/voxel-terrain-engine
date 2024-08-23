package de.cdlemmi.vte;

import de.cdlemmi.vte.input.PlayerInputAction;
import de.cdlemmi.vte.util.DebugPrinting;
import org.joml.Matrix4f;

public class Player implements DebugPrinting {

    public final static double MOVE_SPEED = 1.0;

    private double posX;
    private double posY;
    private double posZ;
    private double angleVer;
    private double angleHor;

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
        Matrix4f trans = new Matrix4f().translate((float) posX, (float) posY, (float) posZ);
        Matrix4f rot = new Matrix4f()
                .rotate((float)angleVer, 0.0f, 0.0f, 1.0f)
                .rotate((float)angleVer, 1.0f, 0.0f, 0.0f);
        return trans.mul(rot);
    }

    public void handleInput(PlayerInputAction action) {
        pressedForward = action.forward();
        pressedBack = action.back();
        pressedRight = action.right();
        pressedLeft = action.left();
        pressedUp = action.up();
        pressedDown = action.down();
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
            velX += MOVE_SPEED * Math.sin(angleHor);
            velY += MOVE_SPEED * -Math.cos(angleHor);
        }
        if(pressedLeft) {
            velX -= MOVE_SPEED * Math.sin(angleHor);
            velY -= MOVE_SPEED * -Math.cos(angleHor);
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
