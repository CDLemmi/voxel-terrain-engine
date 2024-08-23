
package de.cdlemmi.vte;


import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        /*var proj = new Matrix4f().perspective((float)Math.PI/4, 1.0f, 0.1f, 100.0f);
        var view = new Matrix4f().translate(0.0f,0,6.0f);
        var point = new Vector4f(0.5f, 0.5f, 0.5f, 1.0f);
        point = point.mul(view);
        point = point.mul(proj);
        var screenPoint = new Vector3f(point.x/point.w, point.y/point.w, point.z/point.w);
        System.out.println(screenPoint);*/
        Game game = new Game();
        game.start();
        System.out.println("program finished");
    }

}
