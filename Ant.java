import java.awt.Color;

public class Ant {
    private int col;
    private int row;
    private String direction;
    private DemoPanel dp;
    public final Color whiteColorTheme[] = {Color.white, Color.lightGray, Color.gray, Color.darkGray, new Color(169, 169, 189)};
    public final Color orangeColorTheme[] = {new Color(255, 145, 0), new Color(255, 165, 0), new Color(255, 185, 0), new Color(255, 205, 0), new Color(255, 205, 0)};
    public final Color greenColorTheme[] = {new Color(0, 235, 0), new Color(0, 255, 0), new Color(0, 255, 20), new Color(0, 255, 40), new Color(0, 255, 60)};
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

    public Color getCurrentColor(int nodeState) {
        if (nodeState == 0) {
            return Color.black;
        } else if (nodeState > 0 && nodeState <= 5) {
            return currentColorTheme[nodeState-1];
        } else {
            int currentNodeState = nodeState;
            if (currentNodeState > 19) {
                currentNodeState = currentNodeState - 15;
            }
            int rgbVar = (currentNodeState-4)*10;
            if (currentColorTheme.equals(whiteColorTheme)) { 
                return (new Color(255-rgbVar, 255-rgbVar, 255-rgbVar));
            } else if (currentColorTheme.equals(orangeColorTheme)) {
                return (new Color(255-rgbVar, 165-rgbVar, 0+rgbVar));
            } else {
                return (new Color(0+rgbVar, 255-rgbVar, 0+rgbVar));
            }
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
                } else {
                    if (dp.endAtEdge == true) {
                        dp.animationEnded = true;
                    }
                } break;
            case "down":
                if (dp.maxRow > row+1) {
                    row++;
                } else {
                    if (dp.endAtEdge == true) {
                        dp.animationEnded = true;
                    }
                } break;
            case "left":
                if (col-1 >= 0) {
                    col--;
                } else {
                    if (dp.endAtEdge == true) {
                        dp.animationEnded = true;
                    }
                } break;
            case "up":
                if (row-1 >= 0) {
                    row--;
                } else {
                    if (dp.endAtEdge == true) {
                        dp.animationEnded = true;
                    }
                } break;
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
