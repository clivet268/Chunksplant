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
    private WorkingArea workingArea = new WorkingArea();
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
                //ugh[i][j] = random.nextBoolean();
                if(random.nextInt(5) == 1) {
                    cache.put(i, j, new OnePointZero(2, i, j));
                } else if(random.nextInt(5) == 1) {
                    cache.put(i, j, new OnePointSixPointFour(2, i, j));
                }
            }
        }
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
            this.setMinimumSize(new Dimension(visibleScreensize, visibleScreensize));
            //TODO redundant?
            this.setPreferredSize(new Dimension(visibleScreensize, visibleScreensize));
            this.addMouseListener(ml);
            this.addMouseMotionListener(mml);
            this.addMouseWheelListener(mwl);
            this.setBackground(Color.BLACK);
        }
        @Override
        public void paint(Graphics g){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            drawrectangelse(g);
        }
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Chunk chunk = cache.get(e.getX()+(screenFocusX/(squareSize*2)),e.getX()+(screenFocusY/(squareSize*2)));
                if(chunk!=null) {
                    System.out.println(chunk.getLR().getGreen());
                } else {
                    System.out.println(0);
                }
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
                screenFocusX +=(lastx - e.getX()) * (1+(5/squareSize));
                screenFocusY += (lasty - e.getY()) * (1+(5/squareSize));
                //screenFocusX = Math.clamp(screenFocusX + (lastx - e.getX()) * (1+(5/squareSize)), 0, cacheRadius*squareSize);
                //screenFocusY = Math.clamp(screenFocusY + (lasty - e.getY()) * (1+(5/squareSize)), 0, cacheRadius*squareSize);

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
                System.out.println(radiusScale);
                if(e.getWheelRotation() > 0){
                    radiusScale= Math.clamp(radiusScale + 1 + radiusScale/(2*squareSize), 1, cacheRadius);
                    squareSize = (int) Math.ceil((double) screensize/radiusScale);
                    //radiusScale = (int) Math.ceil((double) radiusScale+1+((double) 1 /squareSize));
                    //screenFocusX = Math.clamp(screenFocusX - squareSize, -cacheRadius*squareSize, cacheRadius*squareSize);
                    //screenFocusY = Math.clamp(screenFocusY - squareSize, -cacheRadius*squareSize, cacheRadius*squareSize);
                } else {
                    radiusScale= Math.clamp(radiusScale - (1 + radiusScale/(2*squareSize)), 1, cacheRadius);
                    squareSize = (int) Math.ceil((double) screensize/radiusScale);
                    //screenFocusX = Math.clamp(screenFocusX + squareSize, -cacheRadius*squareSize, cacheRadius*squareSize);
                    //screenFocusY = Math.clamp(screenFocusY + squareSize, -cacheRadius*squareSize, cacheRadius*squareSize);
                }
                repaint();

            }
        };

    }

    private void drawrectangelse(Graphics g){
        for(int i = -radiusScale; i <= radiusScale; i++){
            for (int j = -radiusScale; j <= radiusScale; j++){
                Chunk chunk = cache.get(i+(screenFocusX/(squareSize)),j+(screenFocusY/(squareSize)));
                if(chunk != null) {

                    g.setColor(chunk.getLR());
                    g.fillRect((i*squareSize), (j*squareSize), squareSize, squareSize);
                    g.setColor(Color.lightGray);
                    g.drawRect((i*squareSize), (j*squareSize), squareSize, squareSize);
                }
            }
        }
    }


    //TEST
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
