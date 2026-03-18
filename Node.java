import java.awt.Color;
import javax.swing.JButton;

public class Node extends JButton {
    private int col;
    private int row;
    private String color;
    private String direction;
    private DemoPanel dp;

    public Node(int col, int row, DemoPanel dp) {
        this.col = col;
        this.row = row;
        this.dp = dp;
        color = "white";
        direction = "right";

        setBackground(Color.white);
        setForeground(Color.black);
    }

    public void rotate() {
        if (color.equals("white")) {
            switch (direction) {
                case "right":
                    direction = "down"; break;
                case "down":
                    direction = "left"; break;
                case "left":
                    direction = "up"; break;
                case "up":
                    direction = "right"; break;
            }
        } else {
            switch (direction) {
                case "right":
                    direction = "up"; break;
                case "up":
                    direction = "left"; break;
                case "left":
                    direction = "down"; break;
                case "down":
                    direction = "right"; break;
            }
        }
    }

    public void switchColor() {
        if (color.equals("white")) {
            color = "black";
            setBackground(Color.black);
        } else {
            color = "white";
            setBackground(Color.white);
        }
    }

    public void moveForward() {
        switch (direction) {
            case "right":
                if (dp.maxCol > col+1) {
                    col++;
                } break;
            case "down":
                if (dp.maxRow > row+1) {
                    row++;
                } break;
            case "left":
                if (col-1 >= 0) {
                    col--;
                } break;
            case "up":
                if (row-1 >= 0) {
                    row--;
                }
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

/*     public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    } */
}
