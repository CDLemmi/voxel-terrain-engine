package de.cdlemmi.vte.rendering;

import de.cdlemmi.vte.rendering.abstractions.VertexArray;
import de.cdlemmi.vte.rendering.abstractions.VertexBuffer;
import de.cdlemmi.vte.rendering.abstractions.VertexBufferLayout;

import javax.management.relation.RelationServiceNotRegisteredException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glDrawElements;

public class ChunkMesh {

    private float[] vertices = {
             0.5f,  0.5f,  0.5f, 1.0f, 0.1f, //x, y, z, green, red
             0.5f,  0.5f, -0.5f, 1.0f, 0.1f,
             0.5f, -0.5f, -0.5f, 1.0f, 0.1f,
             0.5f,  0.5f,  0.5f, 1.0f, 0.1f,
             0.5f, -0.5f,  0.5f, 1.0f, 0.1f,
             0.5f, -0.5f, -0.5f, 1.0f, 0.1f,

             0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
            -0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.8f, 0.1f,
             0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
             0.5f, -0.5f, -0.5f, 0.8f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.8f, 0.1f,

             0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
            -0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
            -0.5f, -0.5f,  0.5f, 0.7f, 0.1f,
             0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
             0.5f, -0.5f,  0.5f, 0.7f, 0.1f,
            -0.5f, -0.5f,  0.5f, 0.7f, 0.1f,

             0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
            -0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
            -0.5f,  0.5f, -0.5f, 0.9f, 0.1f,
             0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
             0.5f,  0.5f, -0.5f, 0.9f, 0.1f,
            -0.5f,  0.5f, -0.5f, 0.9f, 0.1f,

             0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
             0.5f, -0.5f, -0.5f, 0.6f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.6f, 0.1f,
             0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
            -0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.6f, 0.1f,

            -0.5f,  0.5f,  0.5f, 0.5f, 0.1f,
            -0.5f,  0.5f, -0.5f, 0.5f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.5f, 0.1f,
            -0.5f,  0.5f,  0.5f, 0.5f, 0.1f,
            -0.5f, -0.5f,  0.5f, 0.5f, 0.1f,
            -0.5f, -0.5f, -0.5f, 0.5f, 0.1f,
    };

    private VertexBuffer vertexBuffer;
    private VertexArray vertexArray;

    private int triangleCount;

    enum Direction {XP, XN, YP, YN, ZP, ZN};
    record Side(int x, int y, int z, Direction dir) {
        float[] getVertices(float blockSize, int x, int y, int z) {

            int a, b, c;
            switch(dir) {
                case XP -> {
                    float[] vertices = {
                            0.5f,  0.5f,  0.5f, 1.0f, 0.1f, //x, y, z, green, red
                            0.5f,  0.5f, -0.5f, 1.0f, 0.1f,
                            0.5f, -0.5f, -0.5f, 1.0f, 0.1f,
                            0.5f,  0.5f,  0.5f, 1.0f, 0.1f,
                            0.5f, -0.5f,  0.5f, 1.0f, 0.1f,
                            0.5f, -0.5f, -0.5f, 1.0f, 0.1f
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
                case XN -> {
                    float[] vertices = {
                            -0.5f,  0.5f,  0.5f, 0.5f, 0.1f,
                            -0.5f,  0.5f, -0.5f, 0.5f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.5f, 0.1f,
                            -0.5f,  0.5f,  0.5f, 0.5f, 0.1f,
                            -0.5f, -0.5f,  0.5f, 0.5f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.5f, 0.1f
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
                case YP -> {
                    float[] vertices = {
                            0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
                            -0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
                            -0.5f,  0.5f, -0.5f, 0.9f, 0.1f,
                            0.5f,  0.5f,  0.5f, 0.9f, 0.1f,
                            0.5f,  0.5f, -0.5f, 0.9f, 0.1f,
                            -0.5f,  0.5f, -0.5f, 0.9f, 0.1f,
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
                case YN -> {
                    float[] vertices = {
                            0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
                            0.5f, -0.5f, -0.5f, 0.6f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.6f, 0.1f,
                            0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
                            -0.5f, -0.5f,  0.5f, 0.6f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.6f, 0.1f,
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
                case ZP -> {
                    float[] vertices = {
                            0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
                            -0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
                            -0.5f, -0.5f,  0.5f, 0.7f, 0.1f,
                            0.5f,  0.5f,  0.5f, 0.7f, 0.1f,
                            0.5f, -0.5f,  0.5f, 0.7f, 0.1f,
                            -0.5f, -0.5f,  0.5f, 0.7f, 0.1f
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
                case ZN -> {
                    float[] vertices = {
                            0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
                            -0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.8f, 0.1f,
                            0.5f,  0.5f, -0.5f, 0.8f, 0.1f,
                            0.5f, -0.5f, -0.5f, 0.8f, 0.1f,
                            -0.5f, -0.5f, -0.5f, 0.8f, 0.1f
                    };
                    for(int i = 0; i < 30; i+=5) {
                        vertices[i] *= blockSize;
                        vertices[i] += x * blockSize;
                        vertices[i+1] *= blockSize;
                        vertices[i+1] += y * blockSize;
                        vertices[i+2] *= blockSize;
                        vertices[i+2] += z * blockSize;
                    }
                    return vertices;
                }
            }
            return null;
        }
    }

    private float[] constructMesh(boolean[] blocks, float blockSize, int size) {
        assert blocks.length == size * size * size;

        ArrayList<Side> sides = new ArrayList<>();

        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                for(int z = 0; z < size; z++) {
                    if(blocks[x + y*size + z*size*size]) {
                        if (x > 0
                                && !blocks[x - 1 + y * size + z * size * size]
                        ) {
                            sides.add(new Side(x, y, z, Direction.XN));
                        }
                        if (x == 0) {
                            sides.add(new Side(x, y, z, Direction.XN));
                        }
                        if (x < size - 1
                                && !blocks[x + 1 + y * size + z * size * size]) {
                            sides.add(new Side(x, y, z, Direction.XP));
                        }
                        if (x == size - 1) {
                            sides.add(new Side(x, y, z, Direction.XP));
                        }

                        if (y > 0
                                && !blocks[x + (y - 1) * size + z * size * size]) {
                            sides.add(new Side(x, y, z, Direction.YN));
                        }
                        if (y == 0) {
                            sides.add(new Side(x, y, z, Direction.YN));
                        }
                        if (y < size - 1
                                && !blocks[x + (y + 1) * size + z * size * size]) {
                            sides.add(new Side(x, y, z, Direction.YP));
                        }
                        if (y == size - 1) {
                            sides.add(new Side(x, y, z, Direction.YP));
                        }

                        if (z > 0
                                && !blocks[x + y * size + (z - 1) * size * size]) {
                            sides.add(new Side(x, y, z, Direction.ZN));
                        }
                        if (z == 0) {
                            sides.add(new Side(x, y, z, Direction.ZN));
                        }
                        if (z < size - 1
                                && !blocks[x + y * size + (z + 1) * size * size]) {
                            sides.add(new Side(x, y, z, Direction.ZP));
                        }
                        if (z == size - 1) {
                            sides.add(new Side(x, y, z, Direction.ZP));
                        }
                    }
                }
            }
        }

        int sideCount = sides.size();
        int vertexCount = sideCount * 6 * 5;
        float[] vertices = new float[vertexCount];
        for(int i = 0; i < sideCount; i++) {
            Side side = sides.get(i);
            float[] newVertices = side.getVertices(blockSize, side.x(), side.y(), side.z());
            for(int j = 0; j < 30; j++) {
                vertices[j+i*6*5] = newVertices[j];
            }
        }
        return vertices;
    }

    public ChunkMesh() {
        boolean[] blocks = {
                true, false, false, true, true, false, false, true, false,
                false, true, true, false, true, false, false, true, true,
                false, true, true, true, true, false, false, true, false,
        };
        float[] v = constructMesh(blocks, 1.5f, 3);
        triangleCount = v.length / 15;
        //if(!Arrays.equals(v, vertices)) {
            //throw new RuntimeException();
        //}
        vertexBuffer = new VertexBuffer(v);
        VertexBufferLayout layout = new VertexBufferLayout();
        layout.pushFloat(3);
        layout.pushFloat(2);
        vertexArray = new VertexArray(vertexBuffer, layout);
    }

    public void render() {
        vertexArray.bind();
        glDrawArrays(GL_TRIANGLES, 0, triangleCount*3);
    }

}
