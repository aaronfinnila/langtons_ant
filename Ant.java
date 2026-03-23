import java.awt.Color;

public class Ant {
    private int col;
    private int row;
    private String direction;
    private DemoPanel dp;
    public final Color whiteColorTheme[] = {Color.black, Color.white, Color.lightGray, Color.gray, Color.darkGray};
    public final Color orangeColorTheme[] = {Color.black, new Color(255, 145, 0), new Color(255, 165, 0), new Color(255, 185, 0), new Color(255, 205, 0)};
    public final Color greenColorTheme[] = {Color.black, new Color(0, 235, 0), new Color(0, 255, 0), new Color(0, 255, 20), new Color(0, 255, 40)};
    public Color currentColorTheme[] = new Color[5];

    public Ant(int col, int row, DemoPanel dp) {
        this.col = col;
        this.row = row;
        this.dp = dp;
        currentColorTheme = whiteColorTheme;
        direction = "left";
    }

    public void rotate(int currentNodeState, String cycleType) {
        if (cycleType.charAt(currentNodeState) == 'R') {
            turnRight();
        } else {
            turnLeft();
        }
    }

    public void turnLeft() {
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

    public void turnRight() {
        switch (direction) {
                case "right":
                    direction = "down"; break;
                case "up":
                    direction = "right"; break;
                case "left":
                    direction = "up"; break;
                case "down":
                    direction = "left"; break;
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
}
