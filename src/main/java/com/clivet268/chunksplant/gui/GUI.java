package com.clivet268.chunksplant.gui;

import com.clivet268.chunksplant.Mapper2D;
import com.clivet268.chunksplant.chunks.Chunk;
import com.clivet268.chunksplant.chunks.OnePointSixPointFour;
import com.clivet268.chunksplant.chunks.OnePointZero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GUI extends JFrame{
    private JPanel main;
    private WorkingArea workingArea;
    private Menu menu = new Menu();
    private Random random = new Random();
    private static int cacheRadius = 600;
    private Mapper2D<Chunk> cache = new Mapper2D<>(cacheRadius);
    private static int defaultRadiusScale = 30;
    private static int radiusScale = defaultRadiusScale;
    private static int maxRadiusScale = cacheRadius;
    private static int screensize = 550;
    private static int visibleScreensize = (int) (screensize - Math.sqrt(screensize));
    private static int squareSize = screensize/radiusScale;

    private static int screenFocusX = 0;
    private static int screenFocusY = 0;

    private static int validResolutions[];

    public static boolean[][] ugh = new boolean[cacheRadius][cacheRadius];

    public GUI(){
        //TODO temporary test filler
        for (int i = -cacheRadius; i <= cacheRadius; i++) {
            for (int j = -cacheRadius; j <= cacheRadius; j++) {
                if(random.nextInt(5) == 1) {
                    cache.put(i, j, new OnePointZero(2, i, j));
                } else if(random.nextInt(5) == 1) {
                    cache.put(i, j, new OnePointSixPointFour(2, i, j));
                }
            }
        }
        this.workingArea = new WorkingArea(cache, screensize);


        this.setLocationRelativeTo(null);
        //TODO custom
        this.setLayout(new GridLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(menu);
        this.add(workingArea);
        this.setSize(new Dimension(1000, 540));
        this.setVisible(true);
    }


    private class Menu extends JPanel{
        private ResetPosButton rp= new ResetPosButton();
        private ResetScaleButton rs = new ResetScaleButton();
        public Menu(){
            this.add(rp);
            this.add(rs);
        }
        private class ResetPosButton extends JButton{
            public ResetPosButton(){
                this.setName("(0,0)");
                this.addMouseListener(ml);
            }
            MouseListener ml = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    screenFocusX = 0;
                    screenFocusY = 0;
                    workingArea.repaint();
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
        }
        private class ResetScaleButton extends JButton{
            public ResetScaleButton(){
                this.setName("Default Scale");
                this.addMouseListener(ml);
            }
            MouseListener ml = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radiusScale = defaultRadiusScale;
                    squareSize = screensize/radiusScale;
                    workingArea.repaint();
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
        }

    }

    //TEST
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
