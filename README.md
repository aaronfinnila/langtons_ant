# Langton's Ant (Java Swing)

A desktop visualization of Langton's Ant built with Java Swing.

This implementation runs the ant on a finite grid and supports a configurable 5-state turning cycle (`R`/`L` pattern), animation speed, and maximum simulation steps.

## Features

- Real-time Java Swing visualization
- Keyboard-driven menu and settings
- Configurable animation delay
- Configurable max steps
- Custom cycle type
- Reset to main menu after simulation ends

## Requirements

- Java 21
- Any OS with Java and a graphical desktop environment

### Compile and run from source

```bash
javac Main.java DemoPanel.java Ant.java KeyHandler.java UI.java
java Main
```

### Optional: Build a local runnable JAR

```bash
javac Main.java DemoPanel.java Ant.java KeyHandler.java UI.java
jar cfe langtons_ant.jar Main *.class
java -jar langtons_ant.jar
```

## Controls

- `W` / `S` - move up/down in menu
- `A` / `D` - move left/right between setting values
- `Enter` or `Space` - confirm/select
- `Esc` - exit application

### Settings

1. **Animation delay per step (ms)**
   - Select one of: `1`, `3`, `5`
2. **Max steps**
   - Select one of: `15000`, `30000`, `60000`
3. **Cycle type**
   - Press `Space`/`Enter` and input a 5-character string using only `R` and `L`
   - Examples: `RRRRL`, `RLLRL`, `LRRLR`