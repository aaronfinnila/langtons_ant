# Langton's Ant (Java Swing)

A desktop visualization of Langton's Ant built with Java Swing.

This implementation runs the ant on a finite 176x120 grid and supports configurable turning cycles (`R`/`L` patterns with 1-5 states), animation speed, color themes, and maximum simulation steps.

## Features

- Real-time Java Swing visualization
- Keyboard-driven menu and settings
- Configurable animation delay (1ms, 3ms, 5ms)
- Configurable max steps (15000, 30000, 60000)
- Custom cycle types (1-5 character `R`/`L` patterns)
- Three color themes (White, Orange, Green)
- Reset to main menu after simulation ends

## Running

### Compile from source

Requires Java 21+.

```bash
javac Main.java DemoPanel.java Ant.java KeyHandler.java UI.java
java Main
```

To build a runnable JAR:

```bash
javac Main.java DemoPanel.java Ant.java KeyHandler.java UI.java
jar cfe langtons_ant.jar Main *.class
java -jar langtons_ant.jar
```

## Controls

- `W` / `S` - navigate up/down in menu
- `A` / `D` - navigate left/right between setting values
- `Enter` or `Space` - confirm/select
- `Esc` - exit application

### Settings

1. **Animation delay per step (ms)** - `1`, `3`, or `5`
2. **Max steps** - `15000`, `30000`, or `60000`
3. **Cycle type** - Enter a 1-5 character string using `R` (right) and `L` (left)
   - Default: `LR` (classic Langton's Ant)
   - Examples: `RL`, `RLR`, `LLRR`, `LRRRR`
4. **Color theme** - `White`, `Orange`, or `Green`