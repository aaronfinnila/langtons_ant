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

    public void rotate(String currentNodeColor) {
        if (currentNodeColor.equals("white")) {
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
        } else if (currentNodeColor.equals("black")) {
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
        } else {
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
    }

    public String changeColor(String[][] nodeColor) {
        switch (nodeColor[col][row]) {
            case "white":
                return "black";
            case "black":
                return "gray";
            case "gray":
                return "white";
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
