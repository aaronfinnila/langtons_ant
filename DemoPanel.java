import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DemoPanel extends JPanel implements Runnable {

    public Thread dpThread;
    public Ant ant;
    private UI ui;
    public KeyHandler kh = new KeyHandler(this);

    public final int maxCol = 176;
    public final int maxRow = 120;
    public final int nodeSize = 6;
    public final int screenWidth = nodeSize * maxCol;
    public final int screenHeight = nodeSize * maxRow;

    int[][] nodeState = new int[maxCol][maxRow];
    
    public int steps = 0;
    public int maxSteps = 15000;
    public boolean animationStarted = false;
    public boolean animationEnded = false;
    public boolean endAtEdge = true;
    public long animationDelay = 1;
    public String cycleType = "LR";
    public String colorThemeStr = "";
    
    public DemoPanel() {
        this.setPreferredSize(new Dimension(1050, 820));
        this.setBackground(Color.BLACK);
        this.addKeyListener(kh);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        
        dpThread = new Thread(this);
        ui = new UI(this);
        ant = new Ant(80, 65, this);
        
        for (int i = 0; i < nodeState.length; i++) {
            for (int j = 0; j < nodeState[i].length; j++) {
                nodeState[i][j] = 0;
            }
        }
    }
    
    public void startDpThread() {
        if (dpThread == null || dpThread.getState() != Thread.State.NEW) {
            dpThread = new Thread(this);
        }
        dpThread.start();
    }

    public void resetSimulation() {
        steps = 0;
        ant = new Ant(80, 65, this);
        switch (colorThemeStr) {
            case "ORANGE":
                ant.currentColorTheme = ant.orangeColorTheme; break;
            case "GREEN":
                ant.currentColorTheme = ant.greenColorTheme; break;
            case "WHITE":
                ant.currentColorTheme = ant.whiteColorTheme; break;
        }
        for (int i = 0; i < nodeState.length; i++) {
            for (int j = 0; j < nodeState[i].length; j++) {
                nodeState[i][j] = 0;
            }
        }
        dpThread.interrupt();
        steps = 0;
        animationStarted = false;
        animationEnded = false;
        kh.menuRow = 0;
        kh.menuCol = 0;
        SwingUtilities.invokeLater(this::repaint);
    }
    
    public void run() {
        while (steps < maxSteps && animationEnded == false) {

            int col = ant.getCol();
            int row = ant.getRow();

            int currentNodeState = nodeState[col][row];
            ant.rotate(currentNodeState, cycleType);
            if (nodeState[col][row]+1 < cycleType.length()) {
                nodeState[col][row]++;
            } else {
                nodeState[col][row] = 1; 
            }
            ant.moveForward();

            SwingUtilities.invokeLater(this::repaint);

            try {
                Thread.sleep(animationDelay);
            } catch (Exception e) {
                e.printStackTrace();
            }
            steps++;
        }
        animationEnded = true;
    }

    public void renderPng() {
        BufferedImage img = new BufferedImage(getWidth(), getHeight()-125, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        try {
            printAll(g2);
            ImageIO.write(img, "png", new File("drawing_" + cycleType + "_" + steps + "_" + colorThemeStr + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            g2.dispose();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (animationStarted == true) {   
            for (int col = 0; col < maxCol; col++) {
                for (int row = 0; row < maxRow; row++) {
                    g2.setColor(ant.getCurrentColor(nodeState[col][row]));
                    int x = col * nodeSize;
                    int y = row * nodeSize;
                    g2.fillRect(x, y, nodeSize, nodeSize);
                }
            }
        }

        ui.draw(g2);
        
        if (ant != null) {
            g2.setColor(Color.RED);
            g2.fillOval(ant.getCol() * nodeSize, ant.getRow() * nodeSize, nodeSize, nodeSize);
        }
    }
}
