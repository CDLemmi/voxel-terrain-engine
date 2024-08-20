package de.cdlemmi.vte.rendering.abstractions;

import static org.lwjgl.opengl.GL33.*;

public class VertexBuffer {

    private int vb;

    public VertexBuffer(float[] vertices) {
        vb = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vb);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }

    void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, vb);
    }

}
