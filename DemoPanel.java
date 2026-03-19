import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DemoPanel extends JPanel implements Runnable {

    Thread dpThread;
    Ant ant;

    final int maxCol = 176;
    final int maxRow = 120;
    final int nodeSize = 6;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    String[][] nodeColor = new String[maxCol][maxRow];

    
    int steps = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(1050, 700));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        
        dpThread = new Thread(this);

        for (int i = 0; i < nodeColor.length; i++) {
            for (int j = 0; j < nodeColor[i].length; j++) {
                nodeColor[i][j] = "white";
            }
        }
    }

    public void startDpThread() {
        dpThread.start();
    }

    public void run() {
        ant = new Ant(70, 65, this);

        while (steps < 18000) {

            int col = ant.getCol();
            int row = ant.getRow();

            String currentColor = nodeColor[col][row];
            ant.rotate(currentColor);
            nodeColor[col][row] = ant.changeColor(nodeColor);
            ant.moveForward();

            SwingUtilities.invokeLater(this::repaint);

            try {
                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            steps++;
        }
        System.out.println("while loop ready");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                switch (nodeColor[col][row]) {
                    case "white":
                        g2.setColor(Color.white); break;
                    case "black":
                        g2.setColor(Color.black); break;
                    case "gray":
                        g2.setColor(Color.gray); break;
                }

                int x = col * nodeSize;
                int y = row * nodeSize;

                g2.fillRect(x, y, nodeSize, nodeSize);

                g2.setColor(Color.GRAY);
                g2.drawRect(x, y, nodeSize, nodeSize);
            }
        }

        if (ant != null) {
            g2.setColor(Color.RED);
            g2.fillOval(ant.getCol() * nodeSize, ant.getRow() * nodeSize, nodeSize, nodeSize);
        }
    }
}
