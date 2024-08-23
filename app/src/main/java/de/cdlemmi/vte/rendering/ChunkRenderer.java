package de.cdlemmi.vte.rendering;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL33.*;

public class ChunkRenderer {

    ChunkMesh[] meshes;

    ChunkRenderer() {

        meshes = new ChunkMesh[] {new ChunkMesh()};
    }

    void renderChunks() {
        for(ChunkMesh mesh : meshes) {
            mesh.render();
        }

    }

}
