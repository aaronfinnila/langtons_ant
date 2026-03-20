public class Ant {
    private int col;
    private int row;
    private String direction;
    private DemoPanel dp;

    public Ant(int col, int row, DemoPanel dp) {
        this.col = col;
        this.row = row;
        this.dp = dp;
        direction = "left";
    }

    public void rotate(String currentNodeColor, String cycleType) {
        switch (currentNodeColor) {
            case "black":
                if (cycleType.charAt(0) == 'R') {
                    turnRight();
                } else {
                    turnLeft();
                } break;
            case "darkGray":
                if (cycleType.charAt(1) == 'R') {
                    turnRight();
                } else {
                    turnLeft();
                } break;
            case "gray":
                if (cycleType.charAt(2) == 'R') {
                    turnRight();
                } else {
                    turnLeft();
                } break;
            case "lightGray":
                if (cycleType.charAt(3) == 'R') {
                    turnRight();
                } else {
                    turnLeft();
                } break;
            case "white":
                if (cycleType.charAt(4) == 'R') {
                    turnRight();
                } else {
                    turnLeft();
                } break;
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

    public String changeColor(String[][] nodeColor) {
        switch (nodeColor[col][row]) {
            case "black":
                return "darkGray";
            case "darkGray":
                return "gray";
            case "gray":
                return "lightGray";
            case "lightGray":
                return "white";
            case "white":
                return "black";
        }
        return null;
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
