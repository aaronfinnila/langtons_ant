import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DemoPanel extends JPanel {

    Graphics2D graphics;
    BufferedImage screen;
    
    // SCREEN SETTINGS
    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    // NODE 
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    boolean goalReached = false;
    int steps = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        screen = new BufferedImage(maxCol*nodeSize, maxRow*nodeSize, BufferedImage.TYPE_INT_ARGB);

        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {

            node[col][row] = new Node(col, row, this);
            this.add(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    public void start() {
        Node antNode = new Node(5, 10, this);

        while (steps < 300) {

            int col = antNode.getCol();
            int row = antNode.getRow();

            draw();
            graphics.setColor(Color.RED);
            graphics.fillOval(col*nodeSize, row*nodeSize, nodeSize, nodeSize);
            antNode.rotate();
            antNode.switchColor();
            antNode.moveForward();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            steps++;
        }

    }

    public void draw() {
        graphics = (Graphics2D)getGraphics();
        
        graphics.drawImage(screen, 0, 0, Main.window.getWidth(), Main.window.getHeight(), null);

    }
}
