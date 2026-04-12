import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class KeyHandler implements KeyListener {
    private DemoPanel dp;
    public int menuCol = 0;
    public int menuRow = 0;
    public boolean settingsView = false;

    public KeyHandler(DemoPanel dp) {
        this.dp = dp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            if (dp.animationStarted == false) {
                if (settingsView == false) {
                    if (menuRow == 0) {
                        dp.animationStarted = true;
                        dp.startDpThread();
                    }
                    
                    if (menuRow == 1) {
                        settingsView = true;
                        menuCol = 0;
                        menuRow = 5;
                    }
                    
                    if (menuRow == 2) {
                        System.exit(0);
                    }
                } else {
                    if (menuRow == 0) {
                        switch (menuCol) {
                            case 0:
                                dp.animationDelay = 1; break;
                            case 1:
                                dp.animationDelay = 3; break;
                            case 2:
                                dp.animationDelay = 5; break;
                        }
                    }
                    if (menuRow == 1) {
                        String input = JOptionPane.showInputDialog(dp, "Enter max steps:", dp.maxSteps);
                        if (isValidSteps(input)) {
                            dp.maxSteps = Integer.parseInt(input);
                        } else if (input != null) {
                            JOptionPane.showMessageDialog(dp, 
                            "Invalid input, please enter valid max steps",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (menuRow == 2) {
                        String input = JOptionPane.showInputDialog(dp, "Enter cycle type:", dp.cycleType);
                        if (isValidCycle(input)) {
                            dp.cycleType = input;
                        } else if (input != null) {
                            JOptionPane.showMessageDialog(dp, 
                            "Invalid input, please enter valid cycle type",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (menuRow == 3) {
                        switch (menuCol) {
                            case 0:
                                dp.ant.currentColorTheme = dp.ant.whiteColorTheme; break;
                            case 1:
                                dp.ant.currentColorTheme = dp.ant.orangeColorTheme; break;
                            case 2:
                                dp.ant.currentColorTheme = dp.ant.greenColorTheme; break;
                        }
                    }
                    if (menuRow == 4) {
                        switch (menuCol) {
                            case 0:
                                dp.endAtEdge = true; break;
                            case 1:
                                dp.endAtEdge = false; break;
                        }
                    }
                    if (menuRow == 5) {
                        settingsView = false;
                        menuRow = 1;
                    }
                    SwingUtilities.invokeLater(dp::repaint);
                }
                SwingUtilities.invokeLater(dp::repaint);
            } else {
                if (dp.animationEnded == true) {
                    dp.saveImage = true;
                    dp.dpThread.interrupt();
                    dp.resetSimulation();
                }
            }
        }

        if (code == KeyEvent.VK_W) {
            if (dp.animationStarted == false) {
                int upperLimit = 2;
                if (settingsView == true) {
                    upperLimit = 5;
                }
                if (menuRow > 0) {
                    menuRow--;
                } else if (menuRow <= 0) {
                    menuRow = upperLimit;
                }
                menuCol = 0;
                SwingUtilities.invokeLater(dp::repaint);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (dp.animationStarted == false) {
                int upperLimit = 2;
                if (settingsView == true) {
                    upperLimit = 5;
                }
                if (menuRow < upperLimit) {
                    menuRow++;
                } else if (menuRow >= upperLimit) {
                    menuRow = 0;
                }
                menuCol = 0;
                SwingUtilities.invokeLater(dp::repaint);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (dp.animationStarted == false && settingsView == true) {
                if (menuCol == 0) {
                    menuCol = 1;
                } else if (menuCol == 1) {
                    menuCol = 2;
                } else {
                    menuCol = 2;
                }
                SwingUtilities.invokeLater(dp::repaint);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (dp.animationStarted == false && settingsView == true) {
                if (menuCol == 2) {
                    menuCol = 1;
                } else if (menuCol == 1) {
                    menuCol = 0;
                } else {
                    menuCol = 0;
                }
                SwingUtilities.invokeLater(dp::repaint);
            }
        }
    }

    @Override 
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean isValidCycle(String input) {
        if (input != null && !input.isEmpty()) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) != 'R' && input.charAt(i) != 'L') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isValidSteps(String input) {
        if (input != null && !input.isEmpty()) {
            for (int i = 0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i)) == false) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
}