package de.cdlemmi.vte.rendering.abstractions;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL33.*;

public class VertexBufferLayout {

    record VertexBufferElement (
        int type,
        int count,
        boolean normalized,
        int sizeOfType
    ){};

    private ArrayList<VertexBufferElement> elements = new ArrayList<>();
    private int stride = 0;

    public void pushFloat(int count) {
        elements.add(new VertexBufferElement(GL_FLOAT, count, false, 4));
        stride += 4 * count;
    }

    public ArrayList<VertexBufferElement> getElements() {
        return elements;
    }

    public int getStride() {
        return stride;
    }

}
