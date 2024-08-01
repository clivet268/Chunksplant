package com.clivet268.chunksplant.gui;

import com.clivet268.chunksplant.Mapper2D;
import com.clivet268.chunksplant.chunks.Chunk;
import com.clivet268.chunksplant.chunks.EmptyChunk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorkingArea extends JComponent {
    int visibleScreensize;
    int scale;
    int maxScale = Integer.MAX_VALUE;
    int lastx, lasty = 0;
    int x;
    int xrel;
    int y;
    int yrel;
    int squaresize;
    Mapper2D<Chunk> cache;

    public WorkingArea(Mapper2D<Chunk> cache, int visibleScreensize) {
        this.cache = cache;
        this.visibleScreensize = visibleScreensize;
        this.scale = 5;
        this.setMinimumSize(new Dimension(visibleScreensize, visibleScreensize));
        //TODO redundant?
        this.setPreferredSize(new Dimension(visibleScreensize, visibleScreensize));
        this.addMouseListener(ml);
        this.addMouseMotionListener(mml);
        this.addMouseWheelListener(mwl);
        this.setBackground(Color.BLACK);
    }
    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Chunk clickedChunk = getOffsetClick(e.getX(),e.getY());
            if(clickedChunk != null) {
                System.out.println(getOffsetClick(e.getX(), e.getY()).getLR());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            scrollReset();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };
    MouseMotionListener mml = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            scroll(lastx - e.getX(), lasty - e.getY());
            lastx = e.getX();
            lasty = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            lastx = e.getX();
            lasty = e.getY();
        }
    };

    MouseWheelListener mwl = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            zoom(e.getWheelRotation());

        }
    };

    public void zoom(int outorin){
        if(outorin > 0){
            scale++;
        } else {
            scale--;
        }
        repaint();
    }

    public void scroll(int dx, int dy){
        xrel += dx;
        yrel += dy;
        System.out.println(xrel + " " + yrel);

        while(Math.abs(xrel) / squaresize >= 1){
            if(xrel > 0){
                x++;
                xrel -= squaresize;
            }
            else{
                x--;
                xrel += squaresize;
            }
            repaint();
        }
        while(Math.abs(yrel) / squaresize > 1){
            if(yrel > 0){
                y++;
                yrel -= squaresize;
            }
            else{
                y--;
                yrel += squaresize;
            }
            repaint();
        }
    }

    public void scrollReset(){
        xrel = 0;
        yrel = 0;
    }

    public void viewReset(){
        x = 0;
        y = 0;
        scale = 5;
    }

    @Override
    public void paint(Graphics graphics){
        squaresize = (visibleScreensize/((2 * scale) + 1));
        for (int i = -scale; i < scale; i++) {
            for (int j = -scale; j < scale; j++) {
                Chunk item = cache.get(i+x,j+y);
                if(item != null) {
                    graphics.setColor(item.getLR());
                    graphics.fillRect(i * squaresize, j * squaresize, squaresize, squaresize);
                }
            }
        }
    }

    public Chunk getOffsetClick(int xClick, int yClick){
        xClick = ((visibleScreensize / 2) - xClick)/squaresize;
        yClick = ((visibleScreensize / 2) - yClick)/squaresize;
        Chunk clickedChunk = cache.get(xClick + x, yClick + y);
        return clickedChunk == null ? new EmptyChunk(0,false, xClick + x,yClick + y) : clickedChunk;
    }

}