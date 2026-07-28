/**
 * Represents one cell in the maze grid.
 * A tile can be a wall, the exit, or a normal empty space.
 */
public class Tile {
    private boolean isWall;
    private boolean isExit;

    // Constructor: set whether this tile is a wall and/or the exit
    public Tile(boolean isWall, boolean isExit) {
        this.isWall = isWall;
        this.isExit = isExit;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isExit() {
        return isExit;
    }
}
