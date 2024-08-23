package de.cdlemmi.vte.rendering;

import de.cdlemmi.vte.rendering.abstractions.VertexArray;
import de.cdlemmi.vte.rendering.abstractions.VertexBuffer;
import de.cdlemmi.vte.rendering.abstractions.VertexBufferLayout;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11C.glDrawElements;

public class ChunkMesh {

    private float[] vertices = {
             0.5f,  0.5f,  0.5f, 1.0f,
             0.5f,  0.5f, -0.5f, 1.0f,
             0.5f, -0.5f, -0.5f, 1.0f,
             0.5f,  0.5f,  0.5f, 1.0f,
             0.5f, -0.5f,  0.5f, 1.0f,
             0.5f, -0.5f, -0.5f, 1.0f,

             0.5f,  0.5f, -0.5f, 0.8f,
            -0.5f,  0.5f, -0.5f, 0.8f,
            -0.5f, -0.5f, -0.5f, 0.8f,
             0.5f,  0.5f, -0.5f, 0.8f,
             0.5f, -0.5f, -0.5f, 0.8f,
            -0.5f, -0.5f, -0.5f, 0.8f,

             0.5f,  0.5f,  0.5f, 0.7f,
            -0.5f,  0.5f,  0.5f, 0.7f,
            -0.5f, -0.5f,  0.5f, 0.7f,
             0.5f,  0.5f,  0.5f, 0.7f,
             0.5f, -0.5f,  0.5f, 0.7f,
            -0.5f, -0.5f,  0.5f, 0.7f,

             0.5f,  0.5f,  0.5f, 0.9f,
            -0.5f,  0.5f,  0.5f, 0.9f,
            -0.5f,  0.5f, -0.5f, 0.9f,
             0.5f,  0.5f,  0.5f, 0.9f,
             0.5f,  0.5f, -0.5f, 0.9f,
            -0.5f,  0.5f, -0.5f, 0.9f,

             0.5f, -0.5f,  0.5f, 0.6f,
             0.5f, -0.5f, -0.5f, 0.6f,
            -0.5f, -0.5f, -0.5f, 0.6f,
             0.5f, -0.5f,  0.5f, 0.6f,
            -0.5f, -0.5f,  0.5f, 0.6f,
            -0.5f, -0.5f, -0.5f, 0.6f,

            -0.5f,  0.5f,  0.5f, 0.5f,
            -0.5f,  0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f, 0.5f,
            -0.5f,  0.5f,  0.5f, 0.5f,
            -0.5f, -0.5f,  0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f, 0.5f,
    };

    private VertexBuffer vertexBuffer;
    private VertexArray vertexArray;

    public ChunkMesh() {
        vertexBuffer = new VertexBuffer(vertices);
        VertexBufferLayout layout = new VertexBufferLayout();
        layout.pushFloat(3);
        layout.pushFloat(1);
        vertexArray = new VertexArray(vertexBuffer, layout);
    }

    public void render() {
        vertexArray.bind();
        glDrawArrays(GL_TRIANGLES, 0, 6 * 6 * 4);
    }

}
