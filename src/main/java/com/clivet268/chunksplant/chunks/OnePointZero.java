package com.clivet268.chunksplant.chunks;

import java.awt.*;

public class OnePointZero extends Chunk{
    public OnePointZero(int version, int x, int y) {
        super(version, false, x, y);
        this.lowestRes = new Color(30, 255, 30);
    }
}
