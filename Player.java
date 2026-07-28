/**
 * Represents the player in the maze.
 * Stores position (x, y) and remaining energy.
 * (0, 0) is the bottom-left corner of the grid.
 */
public class Player {
    private int x;
    private int y;
    private int energy;

    // Player always starts at (0, 0) with a given amount of energy
    public Player(int energy) {
        this.x = 0;
        this.y = 0;
        this.energy = energy;
    }

    // Move up: y increases (towards the top of the grid)
    public void moveUp() {
        y = y + 1;
        energy = energy - 1;
    }

    // Move down: y decreases (towards the bottom of the grid)
    public void moveDown() {
        y = y - 1;
        energy = energy - 1;
    }

    // Move left: x decreases
    public void moveLeft() {
        x = x - 1;
        energy = energy - 1;
    }

    // Move right: x increases
    public void moveRight() {
        x = x + 1;
        energy = energy - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergy() {
        return energy;
    }
}
