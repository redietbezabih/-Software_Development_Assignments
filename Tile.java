//Rediet Bezabih

/**
 * Represents a single cell in the maze grid.
 * Each tile can be a normal floor, a wall, or the exit.
 */
public class Tile {
    private boolean isWall;
    private boolean isExit;

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
