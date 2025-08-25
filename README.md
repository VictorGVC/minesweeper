# Minesweeper

A simple Minesweeper game implementation in Java with multiple difficulty levels, flagging functionality, and comprehensive test coverage.

## Features

### Core Gameplay
- **Multiple Difficulty Levels**: Easy (12% mines, 20x10), Normal (15% mines, 30x15), Hard (20% mines, 40x17)
- **Cell Revealing**: Enter coordinates to reveal cells and discover adjacent mine counts
- **Flagging System**: Mark suspected mines with flags to avoid accidental reveals
- **Win/Loss Detection**: Automatic game state management with win condition checking
- **Play Again Option**: Restart functionality after game completion

### User Interface
- **Console Display**: Clean ASCII-based board visualization
- **Color-coded Output**: Red error messages, green win notifications
- **Input Validation**: Robust validation for coordinates and actions

### Technical Features
- **Exception Handling**: Custom `ExplosionException` for mine hits
- **Modular Architecture**: Separation of concerns with domain models and view layer
- **Comprehensive Testing**: JUnit 5 test suite with Instancio for test data generation
- **Code Quality**: Spotless formatting with Palantir Java Format

## Prerequisites

- **Java**: JRE 11+
- **Gradle**: 7.0+

## How to Run

### Using Gradle Wrapper

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd minesweeper
   ```

2. **Run the game**:
   ```bash
   ./gradlew run
   ```

## How to Play

1. **Select Difficulty**: Choose from Easy (1), Normal (2), or Hard (3)
2. **Choose Action**: Select either Reveal (1) or Flag (2) for each turn
3. **Select Position**: Enter row and column coordinates (0-indexed)
4. **Game Objectives**:
   - **Win**: Reveal all non-mine cells
   - **Lose**: Reveal a cell containing a mine
5. **Controls**:
   - Numbers show adjacent mine counts
   - `F` indicates flagged cells
   - `■` represents unrevealed cells
   - `*` shows mines (only visible when game ends)

## Dependencies

- **JUnit 5**: Testing framework
- **Instancio**: Test data generation
- **Spotless**: Code formatting
- **Gradle Application Plugin**: Easy execution and distribution