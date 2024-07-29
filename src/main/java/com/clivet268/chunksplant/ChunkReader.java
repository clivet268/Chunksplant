package com.clivet268.chunksplant;
import com.clivet268.chunksplant.chunks.Chunk;
import com.clivet268.chunksplant.chunks.OnePointZero;
import com.clivet268.chunksplant.regions.Region;

public class ChunkReader {
    public Chunk read(Region r){
        return new OnePointZero(2, 0,0);
    }
}
