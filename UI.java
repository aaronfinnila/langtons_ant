import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    DemoPanel dp;
    public UI(DemoPanel dp) {
        this.dp = dp;
    }

    public void draw(Graphics2D g2) {
        if (dp.animationStarted == false && dp.kh.settingsView == false) {
            drawMainMenu(g2);
        } else if (dp.animationStarted == false && dp.kh.settingsView == true) {
            drawSettingsMenu(g2);
        } else {
            drawAnimationUI(g2);
        }
    }

    public void drawMainMenu(Graphics2D g2) {
        int height;
        int width;
        g2.setColor(Color.white);
        g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 24));
        width = dp.getPreferredSize().width/2-100;
        height = dp.getPreferredSize().height-80;
        g2.drawString("Start animation", width, height);
        height += 30;
        g2.drawString("Settings", width, height);
        height += 30;
        g2.drawString("Exit", width, height);
        width -= 20;
        g2.drawString(">", width, height-60+(30*dp.kh.menuRow));
    }

    public void drawSettingsMenu(Graphics2D g2) {
        int height;
        int width;
        String text;

        g2.setColor(Color.white);
        g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 24));
        width = dp.getPreferredSize().width/2-300;
        height = dp.getPreferredSize().height-175;
        g2.drawString("Animation delay per step (ms)", width, height);
        height += 30;
        g2.drawString("Max steps", width, height);
        height += 30;
        g2.drawString("Cycle type", width, height);
        height += 30;
        g2.drawString("Color theme", width, height);
        height += 30;
        g2.drawString("End at Edge", width, height);
        height += 30;
        g2.drawString("Back", width, height);
        width -= 20;
        g2.drawString(">", width, height-150+(30*dp.kh.menuRow));
        
        width = dp.getPreferredSize().width/2+200;
        height = dp.getPreferredSize().height-80;
        if (dp.kh.menuRow == 0) {
            text = "1";
            setDelayColor(g2, text);
            g2.drawString(text, width, height);
            width += 40;
            text = "3";
            setDelayColor(g2, text);
            g2.drawString(text, width, height);
            width += 40;
            text = "5";
            setDelayColor(g2, text);
            g2.drawString(text, width, height);
            height += 20;
            g2.setColor(Color.white);
            g2.drawString("↑", width-79+(40*dp.kh.menuCol), height+5);
        } else if (dp.kh.menuRow == 1) {
            width -= 85;
            g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 16));
            height -= 30;
            text = "Press space to enter max steps";
            g2.drawString(text, width, height);
            height += 35;
            text = "For example: 30000, 60000";
            g2.drawString(text, width, height);
            height += 35;
            text = "Current max steps:";
            g2.drawString(text, width, height);
            text = String.valueOf(dp.maxSteps);
            width += 155;
            g2.setColor(Color.green);
            g2.drawString(text, width, height);
        } else if (dp.kh.menuRow == 2) {
            width -= 85;
            g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 16));
            height -= 30;
            text = "Press space to enter cycle type";
            g2.drawString(text, width, height);
            height += 35;
            text = "For example: LR, LRRL etc.";
            g2.drawString(text, width, height);
            height += 35;
            text = "Current type:";
            g2.drawString(text, width, height);
            text = dp.cycleType;
            width += 110;
            g2.setColor(Color.green);
            g2.drawString(text, width, height);
        } else if (dp.kh.menuRow == 3) {
            g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 18));
            text = "White";
            setThemeColor(g2, text);
            g2.drawString(text, width, height);
            width += 70;
            text = "Orange";
            setThemeColor(g2, text);
            g2.drawString(text, width, height);
            width += 85;
            text = "Green";
            setThemeColor(g2, text);
            g2.drawString(text, width, height);
            height += 20;
            g2.setColor(Color.white);
            g2.drawString("↑", width-130+(80*dp.kh.menuCol), height+5);
        } else if (dp.kh.menuRow == 4) {
            g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 18));
            text = "True";
            setEdgeColor(g2, text);
            g2.drawString(text, width, height);
            width += 70;
            text = "False";
            setEdgeColor(g2, text);
            g2.drawString(text, width, height);
            width += 85;
            g2.setColor(Color.white);
            g2.drawString("↑", width-140+(77*dp.kh.menuCol), height+19);
        }
    }

    public void setDelayColor(Graphics2D g2, String delayString) {
        long delay = Long.valueOf(delayString);
        if (delay == dp.animationDelay) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.white);
        }
    }

    public void setStepsColor(Graphics2D g2, String stepsString) {
        int steps = Integer.valueOf(stepsString);
        if (steps == dp.maxSteps) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.white);
        }
    }

    public void setCycleColor(Graphics2D g2, String cycle) {
        if (cycle.equals(dp.cycleType)) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.white);
        }
    }
    
    public void setThemeColor(Graphics2D g2, String theme) {
        switch (theme) {
            case "White":
                if (dp.ant.currentColorTheme.equals(dp.ant.whiteColorTheme)) {
                    g2.setColor(Color.green);
                } else {
                    g2.setColor(Color.white);
                } break;
            case "Orange":
                if (dp.ant.currentColorTheme.equals(dp.ant.orangeColorTheme)) {
                    g2.setColor(Color.green);
                } else {
                    g2.setColor(Color.white);
                } break;
            case "Green":
                if (dp.ant.currentColorTheme.equals(dp.ant.greenColorTheme)) {
                    g2.setColor(Color.green);
                } else {
                    g2.setColor(Color.white);
                } break;
        }
    }

    public void setEdgeColor(Graphics2D g2, String endAtEdge) {
        if (endAtEdge.equals("True") && dp.endAtEdge == true) {
            g2.setColor(Color.green);
        } else if (endAtEdge.equals("False") && dp.endAtEdge == false) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.white);
        }
    }

    public void drawAnimationUI(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(new Font(g2.getFont().getFontName(), Font.BOLD, 18));
        setStepsColor(g2, String.valueOf(dp.steps));
        g2.drawString("Steps: " + dp.steps, dp.getPreferredSize().width/2-110, dp.getPreferredSize().height-50);
        g2.setColor(Color.white);
        g2.drawString("Cycle type: " + dp.cycleType, dp.getPreferredSize().width/2-110, dp.getPreferredSize().height-25);

        if (dp.animationEnded == true) {
            g2.setColor(Color.green);
            g2.drawString("Press Space to return to main menu", dp.getPreferredSize().width/2-150, dp.getPreferredSize().height-75);
        }
    }
}
