package com.clivet268.chunksplant.chunks;

public abstract class Chunk {
    private boolean mca;
    private int dataVersion;

    public int x,y;
    public Chunk(int dataVersion, boolean mca, int x, int y){
        this.dataVersion = dataVersion;
        this.mca = mca;
        this.x = x;
        this.y = y;
    }
}
