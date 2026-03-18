import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DemoPanel extends JPanel implements Runnable {

    Thread dpThread;
    Ant ant;

    final int maxCol = 105;
    final int maxRow = 74;
    final int nodeSize = 10;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    boolean[][] blackNode = new boolean[maxCol][maxRow];

    int steps = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(1050, 700));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        dpThread = new Thread(this);
    }

    public void startDpThread() {
        dpThread.start();
    }

    public void run() {
        ant = new Ant(50, 37, this);

        while (steps < 3500) {

            int col = ant.getCol();
            int row = ant.getRow();

            String currentColor = blackNode[col][row] ? "black" : "white";
            ant.rotate(currentColor);
            blackNode[col][row] = !blackNode[col][row];
            ant.moveForward();

            SwingUtilities.invokeLater(this::repaint);

            try {
                Thread.sleep(10);
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
                g2.setColor(blackNode[col][row] ? Color.BLACK : Color.WHITE);

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
