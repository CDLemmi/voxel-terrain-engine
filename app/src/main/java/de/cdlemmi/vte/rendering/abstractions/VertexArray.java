package de.cdlemmi.vte.rendering.abstractions;

import static org.lwjgl.opengl.GL33.*;

public class VertexArray {

    private int va;

    public VertexArray(VertexBuffer buffer, VertexBufferLayout layout) {
        va = glGenVertexArrays();
        glBindVertexArray(va);
        buffer.bind();
        var elements = layout.getElements();
        long offset = 0;
        for(int i = 0; i < elements.size(); i++) {
            glEnableVertexAttribArray(i);
            glVertexAttribPointer(i, elements.get(i).count(), elements.get(i).type(), elements.get(i).normalized(), layout.getStride(), offset);
            offset += elements.get(i).count() * elements.get(i).sizeOfType();
        }
    }

    public void bind() {
        glBindVertexArray(va);
    }

}
