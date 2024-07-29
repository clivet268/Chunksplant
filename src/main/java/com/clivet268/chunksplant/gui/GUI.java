package com.clivet268.chunksplant.gui;

import com.clivet268.chunksplant.chunks.Chunk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class GUI extends JFrame{
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel main;
    private WorkingArea workingArea = new WorkingArea();
    private Menu menu = new Menu();
    private Random random = new Random();
    private static int cacheSize = 10000;
    private static int scale = 25;
    private static int screensize = 550;
    private static int rscreensize = (int) (screensize - Math.sqrt(screensize));
    private static int squaresize = screensize/scale;

    private static int screenFocusX = (cacheSize/2+1) * squaresize;
    private static int screenFocusY = (cacheSize/2+1) * squaresize;

    public static boolean[][] ugh = new boolean[cacheSize][cacheSize];

    public GUI(){
        for (int i = 0; i < cacheSize; i++) {
            for (int j = 0; j < cacheSize; j++) {
                ugh[i][j] = random.nextBoolean();
            }
        }
        this.setLocationRelativeTo(null);
        //TODO custom
        this.setLayout(new GridLayout());
        this.add(menu);
        this.add(workingArea);
        this.setSize(new Dimension(1000, 540));
        this.setVisible(true);
    }

    private class Menu extends JPanel{

    }

    private class WorkingArea extends JComponent{
        int lastx, lasty = 0;
        /*private boolean[] ugh ={true, false, false, false, true,
                true, false, false, false, true,
                true, false, false, false, true,
                true, false, false, false, true,
                true, false, false, false, true};

         */

        /*private boolean[][] ugh ={
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true},
                {true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true, true, false, false, false, true}};


         */

        public WorkingArea() {
            this.setMinimumSize(new Dimension(rscreensize, rscreensize));
            this.setPreferredSize(new Dimension(rscreensize, rscreensize));
            this.addMouseListener(ml);
            this.addMouseMotionListener(mml);
            this.addMouseWheelListener(mwl);
        }
        @Override
        public void paint(Graphics g){
            drawrectangelse(g, ugh);
        }
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastx = e.getX();
                lasty = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        MouseMotionListener mml = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println(screenFocusX + " " + screenFocusY);
                screenFocusX = Math.clamp(screenFocusX + (lastx - e.getX()),0,(cacheSize - 5) * squaresize);
                screenFocusY = Math.clamp(screenFocusY + (lasty - e.getY()),0,(cacheSize - 5) * squaresize);
                lastx = e.getX();
                lasty = e.getY();
                repaint();
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
                System.out.println(scale);
                if(e.getWheelRotation() > 0){
                    scale+= (int) (Math.ceil((float)scale/(float)cacheSize) + 1);
                } else {
                    scale-= (int) (Math.ceil((float)scale/(float)cacheSize) + 1);
                }
                screenFocusX = screenFocusX/squaresize;
                screenFocusY = screenFocusY/squaresize;
                scale = Math.clamp(scale, 1, cacheSize);
                squaresize = (int)(Math.ceil((float)screensize/(float)scale));
                screenFocusX = Math.clamp(screenFocusX*squaresize,0,(cacheSize - 5) * squaresize);
                screenFocusY = Math.clamp(screenFocusY*squaresize,0,(cacheSize - 5) * squaresize);
                repaint();

            }
        };
    }

    private void drawrectangelse(Graphics g, boolean[][] chunks){
        g.setColor(Color.red);
        for(int i = 0; i < scale; i++){
            for (int j = 0; j < scale; j++){
                if(chunks[i+((screenFocusX)/squaresize)][j+((screenFocusY)/squaresize)]) {
                    g.fillRect((i*squaresize), (j*squaresize), squaresize, squaresize);
                }
            }
        }
    }


    //TEST
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
