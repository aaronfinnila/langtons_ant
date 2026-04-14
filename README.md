# Langton's Ant (Java Swing)

A desktop visualization of Langton's Ant built with Java Swing.

This implementation runs the ant on a finite 176x120 grid and supports configurable turning cycles (`R`/`L` patterns of any length), animation speed, color themes, and maximum simulation steps.

## Features

- Real-time Java Swing visualization
- Keyboard-driven menu and settings
- Configurable animation delay (1ms, 3ms, 5ms)
- Customizable max steps (any positive integer)
- Custom cycle types (any length `R`/`L` pattern)
- Three color themes (White, Orange, Green)
- End at edge toggle (stop when reaching grid boundary)
- Export to PNG after simulation ends
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
2. **Max steps** - Enter any positive integer (default: 15000)
3. **Cycle type** - Enter any length string using `R` (right) and `L` (left)
   - Default: `LR` (classic Langton's Ant)
   - Examples: `RL`, `RLR`, `LLRR`, `LRRRR`, `LLRRRRLLR`
4. **Color theme** - `White`, `Orange`, or `Green`
5. **End at edge** - `On` (stop at boundary) or `Off` (wrap around)
6. **Back** - return to main menu

### Post-Simulation

After simulation ends:
- `W` / `S` - select option
- `Enter` / `Space` - confirm
  - **Save & Reset** - exports the current grid as `drawing.png` and returns to main menu
  - **Reset** - returns to main menu without exporting